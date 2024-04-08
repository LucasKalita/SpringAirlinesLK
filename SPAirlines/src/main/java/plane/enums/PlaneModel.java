package plane.enums;

public enum PlaneModel {
    BOEING_737(PlaneBrand.BOEING),
    BOEING_757(PlaneBrand.BOEING),
    BOEING_777(PlaneBrand.BOEING),
    BOEING_787(PlaneBrand.BOEING),
    BOEING_747(PlaneBrand.BOEING),
    AIRBUS_A300(PlaneBrand.AIRBUS),
    AIRBUS_A320_NEO(PlaneBrand.AIRBUS),
    AIRBUS_A320(PlaneBrand.AIRBUS),
    AIRBUS_A350(PlaneBrand.AIRBUS),
    AIRBUS_A380(PlaneBrand.AIRBUS),
    BOMBARDIER_CHALLENGER(PlaneBrand.BOMBARDIER),
    BOMBARDIER_CRJ1000(PlaneBrand.BOMBARDIER);

    private final PlaneBrand planeBrand;

    PlaneModel(PlaneBrand planeBrand) {
        this.planeBrand = planeBrand;
    }

    public PlaneBrand getBrand() {
        return planeBrand;
    }
}
