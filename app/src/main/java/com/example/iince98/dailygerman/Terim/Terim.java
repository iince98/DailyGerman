package com.example.iince98.dailygerman.Terim;

/**
 * Created by iince98 on 16/11/2017.
 */

public class Terim {
    private int kat;
    private String terim;
    private String anlamı;

    public Terim(int kat, String terim, String anlamı) {
        this.kat = kat;
        this.terim = terim;
        this.anlamı = anlamı;
    }

    public int getKat() {
        return kat;
    }

    public void setKat(int kat) {
        this.kat = kat;
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