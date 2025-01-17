package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;

public class ChisteDAO implements IChisteDAO {
    private final Gson gson;

    // https://v2.jokeapi.dev/joke/Programming,Miscellaneous?blacklistFlags=nsfw,religious

    private static final String BASE_URL = "https://v2.jokeapi.dev/joke/";
    private static final String ENDPOINT = "?format=json";
    private static final int NO_ID = 0;

    private static final String SINGLE = "single";

    /**
     * Constructor de la clase ChisteDAO.
     * Si deseas emplear las clases ChisteSerializer y ChisteDeserializer, debes comentar la línea con ChisteTypeAdapter
     * y no comentar las de los otros dos adaptadores.
     */
    public ChisteDAO() {
        gson = new GsonBuilder().setPrettyPrinting()
//                .registerTypeAdapter(Chiste.class, new ChisteDeserializer())
//                .registerTypeAdapter(Chiste.class, new ChisteSerializer())
                .registerTypeAdapter(Chiste.class, new ChisteTypeAdapter())
                .create();
    }


    private String getURL(String categoria, String[] tipo, String[] banderas, String idioma, int id) {
        String url = BASE_URL + categoria + ENDPOINT;
        if (tipo != null && tipo.length > 0) {
            // Concateno los elementos no nulos media stream de un array de String. En el caso de que no haya ninguno, devuelvo un Optional vacío.
            String tipos = Arrays.stream(tipo).filter(Objects::nonNull).reduce((s, s2) -> s + "," + s2).orElse(null);
            if(tipos!=null && !tipos.isEmpty()){
                url += "&type=" + tipos;
            }
        }
        if (banderas != null && banderas.length > 0) {
            String flags = Arrays.stream(banderas).filter(Objects::nonNull).reduce((s, s2) -> s + "," + s2).orElse(null);
            if(flags!=null && !flags.isEmpty()){
                url += "&blacklistFlags=" + flags;
            }
        }
        if (idioma != null && !idioma.isEmpty()) {
            url += "&lang=" + idioma;
        }
        if (id > 0) {
            url += "&idRange=" + id;
        }
        System.out.println("url = " + url);
        return url;
    }

    private Chiste getJoke(String url) {
        try (BufferedReader is = new BufferedReader(new InputStreamReader(new URI(url).toURL().openStream()))) {
            return gson.fromJson(is, Chiste.class);
        } catch (MalformedURLException e) {
            System.err.println("Error en la URL: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro E/S: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String getJokeAsString(String url) {
        Chiste chiste = getJoke(url);
        return (chiste!=null) ? chiste.getChiste() + System.lineSeparator() + chiste.getRespuesta() : "";
    }


    @Override
    public String getJokeAsString(String categoria, String[] tipo, String[] banderas) {
        return getJokeAsString(getURL(categoria, tipo, banderas, null, NO_ID));
    }


    @Override
    public Chiste getJoke(String categoria, String[] tipo, String[] banderas) {
        return getJoke(getURL(categoria, tipo, banderas, null, NO_ID));
    }

    @Override
    public String getJokeAsString(String categoria, String[] tipo, String[] banderas, String idioma) {
        return getJokeAsString(getURL(categoria, tipo, banderas, idioma, NO_ID));
    }

    @Override
    public Chiste getJoke(String categoria, String[] tipo, String[] banderas, String idioma) {
        return getJoke(getURL(categoria, tipo, banderas, idioma, NO_ID));
    }

    @Override
    public Chiste getJokeById(int id) {
        return getJoke(getURL("Any", null, null, null, id));
    }

    @Override
    public void saveJokeAsJson(Chiste chiste, Writer writer) {
        gson.toJson(chiste, writer);
    }

    @Override
    public String getRandomJokeAsString() {
        System.out.println(BASE_URL + "Any");
        return getJokeAsString(BASE_URL + "Any");
    }

    @Override
    public Chiste getRandomJoke() {
        return getJoke(BASE_URL + "Any");
    }

}