package com.example.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${app.name:Default App Name}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Value("${app.environment:default}")
    private String environment;

    @Value("${app.debug:false}")
    private boolean debug;

    @Value("${app.database.url:not configured}")
    private String databaseUrl;

    @Value("${app.database.username:}")
    private String databaseUsername;

    @Value("${server.port:8080}")
    private int serverPort;

    @Value("${custom.feature.enable-new-ui:false}")
    private boolean newUiEnabled;

    @GetMapping("/config")
    public Map<String, Object> getAllConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("appName", appName);
        config.put("appVersion", appVersion);
        config.put("environment", environment);
        config.put("debug", debug);
        config.put("databaseUrl", databaseUrl);
        config.put("databaseUsername", databaseUsername);
        config.put("serverPort", serverPort);
        config.put("newUiEnabled", newUiEnabled);
        return config;
    }

    @GetMapping("/config/name")
    public String getAppName() {
        return appName;
    }

    @GetMapping("/config/environment")
    public String getEnvironment() {
        return environment;
    }

    @GetMapping("/config/database")
    public Map<String, String> getDatabaseConfig() {
        Map<String, String> dbConfig = new HashMap<>();
        dbConfig.put("url", databaseUrl);
        dbConfig.put("username", databaseUsername);
        return dbConfig;
    }

    @GetMapping("/config/status")
    public String getStatus() {
        return String.format("Application '%s' is running in '%s' environment on port %d",
                appName, environment, serverPort);
    }
}
