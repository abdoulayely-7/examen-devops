package com.example.demo.common.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CorsConfigTest {

    @Test
    void testCorsMapping() {
        CorsConfig config = new CorsConfig();
        CorsRegistry registry = new CorsRegistry();
        config.addCorsMappings(registry);
        assertNotNull(registry);
    }
}
