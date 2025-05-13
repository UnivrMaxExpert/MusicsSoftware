package com.dashapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranoBean implements Serializable
{
    private String titolo;
    private List<String> autori;
    private Genere genere;


    public BranoBean(String titolo, Genere genere, String... autorix) {
        this.titolo = titolo;
        this.genere = genere;
        autori = new ArrayList<>();
        autori.addAll(Arrays.asList(autorix));
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAutori() {
        return String.join(",", autori);
    }
}
