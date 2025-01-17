package org.example;

public enum Categoria {
    ANY("Any"),
    MISC("Misc"),
    PROGRAMMING("Programming"),
    DARK("Dark"),
    PUN("Pun"),
    SPOOKY("Spooky"),
    CHRISTMAS("Christmas");

    private final String nombre;

    Categoria(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la categoría a partir de su nombre.
     *
     * @param nombre Nombre de la categoría
     * @return Categoría
     */
    public static Categoria getCategoria(String nombre) {
        for (Categoria c : Categoria.values()) {
            if (c.getNombre().equals(nombre)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Sobreescribe el método toString() para que devuelva el nombre de la categoría.
     *
     * @return Nombre de la categoría
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return nombre;
    }

}
