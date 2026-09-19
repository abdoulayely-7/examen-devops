# Pipeline CI/CD — GitHub Actions

Ce répertoire documente la chaîne d'intégration et de déploiement continus (**CI/CD**) automatisée via **GitHub Actions** pour le projet Spring Boot (`examen-devops`).

---

## 1. Vue d'ensemble du Cycle de Vie (Git Flow & Automatisation)

Le cycle de livraison suit un modèle de sécurité strict fondé sur les branches et les revues de code :

```text
 [ Développeur ]
       │
       ▼ (git checkout -b feature/...)
 [ Branche de fonctionnalité ]
       │
       ▼ (git push & Création Pull Request vers 'main')
 ┌─────────────────────────────────────────────────────────────┐
 │ 🧪 CI Pipeline (ci.yml)                                    │
 │  ├── 1. actions/checkout@v4 (fetch-depth: 0)                │
 │  ├── 2. actions/setup-java@v4 (Temurin JDK 21 + cache)      │
 │  ├── 3. ./mvnw clean verify (Tests unitaires avec H2)       │
 │  └── 4. Analyse statique SonarQube & Quality Gate           │
 └─────────────────────────────────────────────────────────────┘
       │
       ▼ (Vérification réussie & Revue de code)
 [ Merge de la Pull Request sur 'main' ]
       │
       ▼
 ┌─────────────────────────────────────────────────────────────┐
 │ 🚀 CD Pipeline (deploy.yml)                                │
 │  ├── 1. Validation préalable (needs: ci-validation)         │
 │  ├── 2. Build de l'image Docker multi-stage                 │
 │  ├── 3. Push Docker Hub (latest + sha-<short-sha>)          │
 │  ├── 4. Déploiement distant sur EC2 via SSH                 │
 │  │    └── Mise à jour APP_TAG & docker compose up -d        │
 │  └── 5. Healthcheck automatisé de production                │
 │       └── https://app-lydevtech.duckdns.org/actuator/health │
 └─────────────────────────────────────────────────────────────┘
```

---

## 2. Détail des Workflows

### 1. Workflow CI — Intégration Continue (`.github/workflows/ci.yml`)
- **Déclencheur** : Tout événement `pull_request` ciblant la branche `main` (et déclenchement manuel `workflow_dispatch`).
- **Objectifs** : Valider la conformité du code, l'intégrité des tests et la qualité logicielle avant toute fusion.
- **Étapes clés** :
  1. **Checkout complet** : `fetch-depth: 0` permet à SonarQube d'analyser l'historique Git et les auteurs des lignes de code.
  2. **JDK 21 Temurin** : Utilise le cache Maven natif de GitHub Actions pour réduire le temps de build de ~3 minutes à ~35 secondes.
  3. **Tests isolés H2** : Le profil de test utilise une base de données en mémoire H2 (`src/test/resources/application.properties`). Les tests unitaires et d'intégration ne dépendent pas d'une connexion Internet ou de la base Neon PostgreSQL externe.
  4. **Scan SonarQube** : Exécute le plugin `sonar-maven-plugin` et transmet les métriques au serveur SonarQube (`https://sonarqube-lydevtech.duckdns.org`).

---

### 2. Workflow CD — Déploiement Continu (`.github/workflows/deploy.yml`)
- **Déclencheur** : Tout événement `push` ou `merge` direct sur la branche `main`.
- **Règle d'or** : **Le déploiement ne s'exécute JAMAIS si les tests échouent.**
- **Architecture à 2 jobs séquentiels** :
  1. **Job `ci-validation`** : Rejoue la compilation et la suite de tests sur `main`.
  2. **Job `build-and-deploy`** : Porte la clause explicite `needs: ci-validation`. Si le job précédent échoue, le job de déploiement est **immédiatement annulé**.
- **Étapes clés du déploiement** :
  1. **Calcul du SHA court** : Extrait les 7 premiers caractères du commit Git (`echo "short_sha=$(echo ${{ github.sha }} | cut -c1-7)"`).
  2. **Buildx & Docker Hub** : Pousse l'image avec un double tag :
     - `abdoulayely777/examen-devops-back:latest`
     - `abdoulayely777/examen-devops-back:sha-xxxxxxx` (garantit la traçabilité et le rollback instantané).
  3. **Action SSH sécurisée (`appleboy/ssh-action@v1.0.3`)** :
     - Connexion à l'EC2 avec la clé privée ED25519.
     - Mise à jour de la variable `APP_TAG` dans `/opt/myapp/.env`.
     - Exécution de `docker compose pull application && docker compose up -d --no-deps application`.
  4. **Smoke Test & Healthcheck** :
     - Attend 20 secondes le démarrage de Tomcat et Spring Boot.
     - Vérifie par requête HTTP sécurisée : `curl -f -k https://app-lydevtech.duckdns.org/actuator/health`.

---

## 3. Matrice des Secrets GitHub Actions

Ces variables secrètes doivent être configurées dans **Settings > Secrets and variables > Actions** sur le repository GitHub applicatif :

| Nom du Secret | Description | Valeur type |
| :--- | :--- | :--- |
| **`DOCKERHUB_USERNAME`** | Compte Docker Hub utilisé pour le push | `abdoulayely777` |
| **`DOCKERHUB_TOKEN`** | Token d'accès personnel (PAT) Docker Hub avec droits Read & Write | `dckr_pat_...` |
| **`EC2_HOST`** | Adresse IPv4 publique actuelle de l'instance AWS EC2 | `184.193.18.121` |
| **`EC2_USER`** | Utilisateur système administrateur de l'instance | `ubuntu` |
| **`EC2_SSH_PRIVATE_KEY`** | Clé privée OpenSSH générée par Terraform (`~/.ssh/devops-prod-key`) | `-----BEGIN OPENSSH PRIVATE KEY...` |
| **`SONAR_HOST_URL`** | URL sécurisée publique du serveur SonarQube | `https://sonarqube-lydevtech.duckdns.org` |
| **`SONAR_TOKEN`** | Token d'analyse global généré dans l'interface SonarQube | `sqa_...` |

---

## 4. Règles de Protection de Branche (`main`)

Pour garantir un niveau de qualité entreprise, la branche `main` est protégée dans GitHub :

1. Aller dans **Settings > Branches > Add branch protection rule**.
2. **Branch name pattern** : `main`.
3. Cocher les options :
   - ✅ **Require a pull request before merging** (interdit les pushs directs sur `main`).
   - ✅ **Require status checks to pass before merging** :
     - Rechercher et sélectionner le check : `Build, Test & SonarQube Analysis`.
   - ✅ **Do not allow bypassing the above settings** (applique les règles même aux administrateurs).
