package com.lucaskalita.airlines.plane.enums;

import java.util.Arrays;

public enum PlaneModel {
    BOEING_737("BOEING", "737"),
    BOEING_757("BOEING", "757"),
    BOEING_777("BOEING", "777"),
    BOEING_787("BOEING", "787"),
    BOEING_747("BOEING", "747"),
    AIRBUS_A320_NEO("AIRBUS", "A320 NEO"),
    AIRBUS_A300("AIRBUS", "A300"),
    AIRBUS_A320("AIRBUS", "A320"),
    AIRBUS_A350("AIRBUS", "A350"),
    AIRBUS_A380("AIRBUS", "A380"),
    BOMBARDIER_CHALLENGER("BOMBARDIER", "Challenger"),
    BOMBARDIER_CRJ100("BOMBARDIER", "CRJ1000");

    private final String planeBrand;
    private final String planeModel;

    PlaneModel(String planeBrand, String planeModel) {
        this.planeBrand = planeBrand;
        this.planeModel = planeModel;
    }

    public String getBrand() {
        return planeBrand;
    }
    public String getPlaneModel(){return  planeModel; }
    public static PlaneModel getPlaneModel(String planeBrand, String planeModel){
        return Arrays.stream(PlaneModel.values()).filter(x->x.planeBrand.equals(planeBrand) && x.planeModel.equals(planeModel))
                .findFirst().orElseThrow(RuntimeException::new);
    }
}
