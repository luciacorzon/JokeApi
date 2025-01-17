package org.example;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ChisteTypeAdapter extends TypeAdapter<Chiste> {

    @Override
    public void write(JsonWriter jsonWriter, Chiste chiste) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("id").value(chiste.getId());
        jsonWriter.name("category").value(chiste.getCategoriaString());
        jsonWriter.name("type").value(chiste.getTipoString());
        if (chiste.getTipo() == TipoChiste.SINGLE) {
            jsonWriter.name("joke").value(chiste.getChiste());
        } else {
            jsonWriter.name("setup").value(chiste.getChiste());
            jsonWriter.name("delivery").value(chiste.getRespuesta());
        }
        jsonWriter.name("flags");
        jsonWriter.beginObject();
        // Recorremos todas las banderas y asignamos el valor verdadero o falso si el chiste la contiene o no, respectivamente.
        // Puede hacerse por medio del método containsFlag() de la clase Chiste o recoger las banderas
        // del chiste e invocar el método contains() de la clase List.
        for (Flag flag : Flag.values()) {
            jsonWriter.name(flag.getNombre().toLowerCase()).value(chiste.containsFlag(flag));
        }
        jsonWriter.endObject();
        jsonWriter.name("lang").value(chiste.getLenguajeString());
        jsonWriter.endObject();

    }

    /**
     * Método que deserializa un objeto Chiste a partir de un JsonReader.
     *
     * @param reader JsonReader que contiene el objeto Chiste
     * @return Objeto Chiste
     * @throws IOException Si hay un error de E/S
     * @see com.google.gson.stream.JsonReader
     * @see com.google.gson.stream.JsonToken
     */
    @Override
    public Chiste read(JsonReader reader) throws IOException {
        if(reader.peek()== JsonToken.NULL || reader.peek()!= JsonToken.BEGIN_OBJECT){
            // reader.nextNull();
            return null;
        }
        reader.beginObject();
        Chiste chiste = new Chiste();
        while (reader.peek() != JsonToken.END_OBJECT) {
            String name = reader.nextName();
            switch (name) {
                case "id" -> chiste.setId(reader.nextInt());
                case "category" -> chiste.setCategoria(Categoria.getCategoria(reader.nextString()));
                case "type" -> chiste.setTipo(TipoChiste.getTipoChiste(reader.nextString()));
                case "joke", "setup" -> chiste.setChiste(reader.nextString());
                case "delivery" -> chiste.setRespuesta(reader.nextString());
                case "flags" -> // Para hacerlo más modular he puesto el código en un método aparte.
                        readFlags(reader, chiste);
                case "lang" -> chiste.setLenguaje(reader.nextString());
                default -> reader.skipValue();
            }
        }
        reader.endObject();

        return chiste;
    }

    private void readFlags(JsonReader reader, Chiste chiste) throws IOException {
        reader.beginObject();
        while (reader.peek() != JsonToken.END_OBJECT) {
            String flagName = reader.nextName();
            switch (flagName) {
                case "nsfw" -> {
                    if (reader.nextBoolean()) chiste.addFlag(Flag.NSFW);
                }
                case "religious" -> {
                    if (reader.nextBoolean()) chiste.addFlag(Flag.RELIGION);
                }
                case "political" -> {
                    if (reader.nextBoolean())
                        chiste.addFlag(Flag.POLITICAL);
                }
                case "racist" -> {
                    if (reader.nextBoolean())
                        chiste.addFlag(Flag.RACIST);
                }
                case "sexist" -> {
                    if (reader.nextBoolean())
                        chiste.addFlag(Flag.SEXIST);
                }
                case "explicit" -> {
                    if (reader.nextBoolean())
                        chiste.addFlag(Flag.EXPLICIT);
                }
                default -> reader.skipValue();
            }
        }
        reader.endObject();
    }
}