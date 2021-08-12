package com.yorren.moviecatalogue.data.source.remote.response;

import com.google.gson.annotations.SerializedName;

public class GenresItem {

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private int id;


    public GenresItem(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public int getId() {
        return id;
    }
}