package org.example;

public enum TipoChiste {
    SINGLE("single"),
    TWOPART("twopart");

    private final String nombre;

    TipoChiste(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el tipo de chiste a partir de su nombre.
     * @param nombre Nombre del tipo de chiste
     * @return Tipo de chiste
     */
    public static TipoChiste getTipoChiste(String nombre) {
        for (TipoChiste tc : TipoChiste.values()) {
            if (tc.getNombre().equals(nombre)) {
                return tc;
            }
        }
        return null;
    }

    /**
     * Sobreescribe el método toString() para que devuelva el nombre del tipo de chiste.
     * @return Nombre del tipo de chiste
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return nombre;
    }
}
