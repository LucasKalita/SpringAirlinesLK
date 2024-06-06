package com.lucaskalita.airlines.plane.enums;

import java.util.Arrays;

public enum Aircraft {
    BOEING_737("BOEING", "737", 98, 25 ),
    BOEING_757("BOEING", "757", 110, 58),
    BOEING_777("BOEING", "777", 290, 80),
    BOEING_787("BOEING", "787", 200, 110),
    AIRBUS_A320_NEO("AIRBUS", "A320 NEO", 300, 250),
    AIRBUS_A300("AIRBUS", "A300", 260, 75),
    AIRBUS_A320("AIRBUS", "A320", 180, 0),
    AIRBUS_A350("AIRBUS", "A350", 300, 50),
    AIRBUS_A380("AIRBUS", "A380", 850, 0),
    BOMBARDIER_CHALLENGER("BOMBARDIER", "Challenger", 0, 15),
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
