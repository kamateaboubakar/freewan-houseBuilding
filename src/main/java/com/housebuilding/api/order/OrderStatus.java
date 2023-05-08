package com.housebuilding.api.order;

public enum OrderStatus {

    PENDING("pending"), VALIDATED("validated"), CANCELED("canceled");

    private final String label;

    OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        String value;
        if (this.equals(OrderStatus.CANCELED)) {
            value = "Annulé";
        } else if (this.equals(OrderStatus.VALIDATED)) {
            value = "Validé";
        } else {
            value = "En attente";
        }
        return value;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
