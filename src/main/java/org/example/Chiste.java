package org.example;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Chiste {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private boolean error;

    @Enumerated(EnumType.STRING)
    private Categoria category;
    private Type type;
    private Map<Bandera, Boolean> flags;
    private boolean safe;
    private String lang;

    public Chiste() {
    }

    public Chiste(boolean error, Categoria category, Type type, Map<Bandera, Boolean> flags, boolean safe, int id, String lang) {
        this.error = error;
        this.category = category;
        this.type = type;
        this.flags = flags;
        this.safe = safe;
        this.id = id;
        this.lang = lang;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Map<Bandera, Boolean> getFlags() {
        return flags;
    }

    public void setFlags(Map<Bandera, Boolean> flags) {
        this.flags = flags;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chiste chiste = (Chiste) o;
        return id == chiste.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Bandera, Boolean> b: flags.entrySet()) {
            sb.append(b).append(System.lineSeparator());
        }
        return "Chiste: " +
                "\nerror: " + error +
                "\ncategory: " + category +
                "\ntype: " + type +
                "\nflags: " + sb.toString() +
                "\nsafe: " + safe +
                "\nid: " + id +
                "\nlang: " + lang;
    }
}
