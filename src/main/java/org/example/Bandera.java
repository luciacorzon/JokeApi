package org.example;

public enum Bandera {
    NSFW("nsfw"), RELIGIOUS("religious"), POLITICAL("political"), RACIST("racist"),
    SEXIST("sexist"), EXPLICIT("explicit");

    private String description;

    Bandera(String description) {
        this.description = description;
    }
}
