package com.cards.cards.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractJsonUtil {

    public String toString() {
        Gson g = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting().create();
        return g.toJson(this);
    }
}
