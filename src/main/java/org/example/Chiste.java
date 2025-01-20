package org.example;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
public class Chiste implements Serializable {
    @Id
    @Column(name = "idChiste")
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // Non se indica o valor do id porque os chistes xa traen, e non fai falta autogeneralos
    private int id;
    private Categoria categoria;
    private TipoChiste tipo;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "FlagsChiste", joinColumns = @JoinColumn(name = "idChiste"))
    private final List<Flag> banderas;
    private String chiste;
    private String respuesta;

    private Lenguaje lenguaje;

    /**
     * Constructor de la clase Chiste.
     * @param id Identificador del chiste
     * @param categoria Categoría del chiste
     * @param idioma Idioma del chiste
     * @param tipo Tipo del chiste
     * @param chiste Chiste
     * @param respuesta Respuesta del chiste
     */
    public Chiste(int id, Categoria categoria, String idioma, TipoChiste tipo, String chiste, String respuesta) {
        this.id = id;
        this.categoria = categoria;
        this.tipo = tipo;
        this.chiste = chiste;
        this.respuesta = respuesta;
        this.banderas = new ArrayList<>();
        this.lenguaje = Lenguaje.getLenguaje(idioma);
    }

    /**
     * Constructor por defecto de la clase Chiste.
     *
     */
    public Chiste() {
//        this.id = 0;
        this.categoria = Categoria.ANY;
        this.lenguaje = Lenguaje.EN;
        this.tipo = TipoChiste.SINGLE;
        this.chiste = "";
        this.respuesta = "";
        this.banderas = new ArrayList<>();
    }

    /**
     * Devuelve el identificador del chiste.
     * @return Identificador del chiste
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del chiste.
     * @param id Identificador del chiste
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la categoría del chiste.
     * @return Categoría del chiste
     */
    public Categoria getCategoria() {
        return categoria;
    }

    public String getCategoriaString() {
        return categoria.getNombre();
    }

    /**
     * Establece la categoría del chiste.
     * @param categoria Categoría del chiste
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = Categoria.getCategoria(categoria);
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public String getLenguajeString() {
        return lenguaje.getLenguaje();
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = Lenguaje.getLenguaje(lenguaje);
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    /**
     * Devuelve el tipo del chiste.
     * @return Tipo del chiste
     */
    public TipoChiste getTipo() {
        return tipo;
    }

    public String getTipoString() {
        return tipo.getNombre();
    }

    /**
     * Establece el tipo del chiste.
     * @param tipo Tipo del chiste
     */
    public void setTipo(TipoChiste tipo) {
        this.tipo = tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = TipoChiste.getTipoChiste(tipo);
    }

    /**
     * Devuelve las banderas del chiste.
     * @return Banderas del chiste
     */
    public List<Flag> getBanderas() {
        return banderas;
    }

    /**
     * Añade una bandera al chiste.
     * @param flag Bandera a añadir
     */
    public void addFlag(Flag flag) {
        banderas.add(flag);
    }

    public boolean removeFlag(Flag bandera) {
        return banderas.remove(bandera);
    }

    /**
     * Si el chiste tiene esa bandera, devuelve true.
     * @param bandera Bandera a comprobar
     * @return  true si el chiste tiene esa bandera, false en caso contrario
     */
    public boolean containsFlag(Flag bandera) {
        return banderas.contains(bandera);
    }

    /**
     * Devuelve el chiste como cadena de caracteres.
     * @return Chiste como String
     */
    public String getChiste() {
        return chiste;
    }

    /**
     * Establece el chiste.
     * @param chiste Chiste
     */
    public void setChiste(String chiste) {
        this.chiste = chiste;
    }

    /**
     * Devuelve la respuesta del chiste.
     * @return Respuesta del chiste
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta del chiste.
     * @param respuesta Respuesta del chiste
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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

    /**
     * Sobrescritura del método toString() para que devuelva el chiste.
     * Lo devuelve empleando un StringBuilder y por medio del método forEach() para recorrer la lista de banderas.
     * @return Chiste como String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chiste: ").append(chiste).append(System.lineSeparator());
        sb.append("Respuesta: ").append(respuesta).append(System.lineSeparator());
        sb.append("Categoría: ").append(categoria).append(System.lineSeparator());
        sb.append("Idioma: ").append(lenguaje).append(System.lineSeparator());
        sb.append("Tipo: ").append(tipo).append(System.lineSeparator());
        sb.append("Banderas: ");
        banderas.forEach(b -> sb.append(b).append(" "));
        sb.append(System.lineSeparator());
        return sb.toString();
    }

}