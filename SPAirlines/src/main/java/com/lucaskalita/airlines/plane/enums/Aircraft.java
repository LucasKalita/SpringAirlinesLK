package com.lucaskalita.airlines.plane.enums;

import java.util.Arrays;

public enum Aircraft {
    BOEING_737("BOEING", "737", 300, 250 ),
    BOEING_757("BOEING", "757", 300, 250),
    BOEING_777("BOEING", "777", 300, 250),
    BOEING_787("BOEING", "787", 300, 250),
    BOEING_747("BOEING", "747", 300, 250),
    AIRBUS_A320_NEO("AIRBUS", "A320 NEO", 300, 250),
    AIRBUS_A300("AIRBUS", "A300", 300, 250),
    AIRBUS_A320("AIRBUS", "A320", 300, 250),
    AIRBUS_A350("AIRBUS", "A350", 300, 250),
    AIRBUS_A380("AIRBUS", "A380", 300, 250),
    BOMBARDIER_CHALLENGER("BOMBARDIER", "Challenger", 0, 20),
    BOMBARDIER_CRJ100("BOMBARDIER", "CRJ1000", 0, 50);

    private final String planeBrand;
    private final String planeModel;
    private final int regularSeats;
    private final int premiumSeats;

    Aircraft(String planeBrand, String planeModel, int regularSeats, int premiumSeats) {
        this.planeBrand = planeBrand;
        this.planeModel = planeModel;
        this.premiumSeats = premiumSeats;
        this.regularSeats = regularSeats;
    }

    public String getBrand() {
        return planeBrand;
    }
    public String getPlaneModel(){return  planeModel; }
    public int getRegularSeats(){return regularSeats;}
    public int getPremiumSeats(){return  premiumSeats;}
    public static Aircraft getPlaneModel(String planeBrand, String planeModel){
        return Arrays.stream(Aircraft.values())
                .filter(x->x.planeBrand.equals(planeBrand) && x.planeModel.equals(planeModel))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

}
