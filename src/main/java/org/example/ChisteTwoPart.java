package org.example;

import java.util.Map;

public class ChisteTwoPart extends Chiste{
    private String setup;
    private String delivery;
    public ChisteTwoPart() {
    }

    public ChisteTwoPart(boolean error, Categoria category, Type type, Map<Bandera, Boolean> flags, boolean safe, int id, String lang) {
        super(error, category, type, flags, safe, id, lang);
    }

    public ChisteTwoPart(boolean error, Categoria category, Type type, Map<Bandera, Boolean> flags, boolean safe, int id, String lang, String setup, String delivery) {
        super(error, category, type, flags, safe, id, lang);
        this.setup = setup;
        this.delivery = delivery;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return  "\nsetup: " + setup +
                "\ndelivery: " + delivery;
    }
}
