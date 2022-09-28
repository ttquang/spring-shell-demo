package com.example.springshelldemo.core.model;

import java.util.HashMap;
import java.util.Map;

public enum PropertyLevel {
    ENVIRONMENT("Environment"),
    TEST_SUITE("TestSuite"),
    TEST_CASE("TestCase"),
    TEST_STEP("TestStep");

    public final String label;
    private static final Map<String, PropertyLevel> BY_LABEL = new HashMap<>();

    static {
        for (PropertyLevel e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    PropertyLevel(String label) {
        this.label = label;
    }

    public static PropertyLevel valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
