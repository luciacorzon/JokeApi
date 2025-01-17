package org.example;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ChisteDeserializer implements JsonDeserializer<Chiste> {

    @Override
    public Chiste deserialize(JsonElement elemento, Type type,
                              JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        // Comprobación si es un objeto:
        if (!elemento.isJsonObject())
            return null;

        // Creo un chiste vacío, al que le daré valor a sus atributos:
        Chiste chiste = new Chiste();
        // Recupero el objeto JSON del chiste
        JsonObject jsonChiste = elemento.getAsJsonObject();
        // Comprobación de que no hay error en la petición:
        if (jsonChiste.get("error") != null && jsonChiste.get("error").getAsBoolean()) {
            return null;
        }
        // Compruebo que cada elemento del objeto existe y lo asigno al objeto Chiste:
        // La comprobación se hace con el método get() de la clase JsonObject que devuelve
        // un JsonElement. Si es null, no existe el elemento.
        if (jsonChiste.get("category") != null) {
            chiste.setCategoria(jsonChiste.get("category").getAsString());
        }
        if (jsonChiste.get("type") != null) {
            chiste.setTipo(jsonChiste.get("type").getAsString());
        }
        // En realidad, dependiendo del tipo de chiste, el setup o el delivery pueden no existir.
        // Por lo que podría hacer comprobando el valor de type, pero lo dejo así para que veáis
        // como se puede hacer con el método get() de la clase JsonObject.
        if (jsonChiste.get("setup") != null) {
            chiste.setChiste(jsonChiste.get("setup").getAsString());
            if (jsonChiste.get("delivery") != null) {
                chiste.setRespuesta(jsonChiste.get("delivery").getAsString());
            }
        } else if (jsonChiste.get("joke") != null) {
            chiste.setChiste(jsonChiste.get("joke").getAsString());
        }

        if (jsonChiste.get("lang") != null) {
            chiste.setLenguaje(jsonChiste.get("lang").getAsString());
        }

        if (jsonChiste.get("id") != null) {
            chiste.setId(jsonChiste.get("id").getAsInt());
        }

        if (jsonChiste.get("flags") != null) {
            JsonObject flags = jsonChiste.get("flags").getAsJsonObject();
            if (flags.get("nsfw").getAsBoolean()) {
                chiste.addFlag(Flag.NSFW);
            }
            if (flags.get("religious").getAsBoolean()) {
                chiste.addFlag(Flag.RELIGION);
            }
            if (flags.get("political").getAsBoolean()) {
                chiste.addFlag(Flag.POLITICAL);
            }
            if (flags.get("racist").getAsBoolean()) {
                chiste.addFlag(Flag.RACIST);
            }
            if (flags.get("sexist").getAsBoolean()) {
                chiste.addFlag(Flag.SEXIST);
            }
            if (flags.get("explicit").getAsBoolean()) {
                chiste.addFlag(Flag.EXPLICIT);
            }
        }
        return chiste;
    }
}