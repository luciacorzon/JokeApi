package org.example;

import java.util.Map;

public class ChisteSingle extends Chiste{
    private String delivery;

    public ChisteSingle(String delivery) {
        this.delivery = delivery;
    }

    public ChisteSingle(boolean error, Categoria category, Type type, Map<Bandera, Boolean> flags, boolean safe, int id, String lang, String delivery) {
        super(error, category, type, flags, safe, id, lang);
        this.delivery = delivery;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "delivery: " + delivery;
    }
}
