package org.example;

public enum Flag {
    EXPLICIT("Explicit"),
    NSFW("NSFW"),
    RELIGION("Religion"),
    POLITICAL("Political"),
    RACIST("Racist"),
    SEXIST("Sexist");

    private final String nombre;

    Flag(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la bandera a partir de su nombre.
     * @param nombre Nombre de la bandera
     * @return Bandera
     */
    public static Flag getFlag(String nombre) {
        // Con expresiones lambda:
        return java.util.Arrays.stream(Flag.values()).filter(f -> f.getNombre().equals(nombre)).findFirst()
                .orElse(null);
/*        // Con un bucle for:
//        for (Flag f : Flag.values()) {
//            if (f.getNombre().equals(nombre)) {
//                return f;
//            }
//        }
//        return null;
        */
    }

    /**
     * Sobreescribe el método toString() para que devuelva el nombre de la bandera.
     * @return Nombre de la bandera
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return nombre;
    }
}
