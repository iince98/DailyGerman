package com.example.iince98.dailygerman;

/**
 * Created by iince98 on 02/12/2017.
 */

public class Terim_class {
    private String terim, anlamı;
    private Integer kategori;

    public Terim_class(int kategori, String terim, String anlamı) {
        this.kategori = kategori;
        this.terim = terim;
        this.anlamı = anlamı;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

    public String getTerim() {
        return terim;
    }

    public void setTerim(String terim) {
        this.terim = terim;
    }

    public String getAnlamı() {
        return anlamı;
    }

    public void setAnlamı(String anlamı) {
        this.anlamı = anlamı;
    }
}
