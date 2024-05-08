package com.lucaskalita.airlines.plane.enums;

public enum PlaneBrand {
    BOEING("Boeing"),
    AIRBUS("Airbus"),
    BOMBARDIER("Bombardier");
    private final String name;

    PlaneBrand(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

