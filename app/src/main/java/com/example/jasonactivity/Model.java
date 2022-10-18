package com.example.jasonactivity;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("name")
    private String name;
    @SerializedName("favoritePokemon")
    private String favoritePokemon;
    @SerializedName("recentConsole")
    private String description;

    public Model(final String name, final String favoritePokemon, final String description) {
        this.name = name;
        this.favoritePokemon = favoritePokemon;
        this.description = description;
    }

    public String getName() {return name;}

    public String getFavoritePokemon() {return favoritePokemon;}

    public String getDescription() {return description;}


    public void setName(final String name) {this.name = name;}

    public void setFavoritePokemon(final String favoritePokemon) {this.favoritePokemon = favoritePokemon;}

    public void setDescription(final String description) {this.description = description;}

}
