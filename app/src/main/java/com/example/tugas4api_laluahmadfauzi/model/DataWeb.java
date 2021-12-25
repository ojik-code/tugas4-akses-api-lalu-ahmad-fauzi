package com.example.tugas4api_laluahmadfauzi.model;
import java.io.Serializable;
public class    DataWeb implements Serializable {
    String nameWeb,  baca_lebih_lanjut,  description, logo;
    public DataWeb(String nameWeb,  String baca_lebih_lanjut, String description, String logo) {
        this.nameWeb = nameWeb;
        this.baca_lebih_lanjut = baca_lebih_lanjut;
        this.description = description;
        this.logo = logo;
    }

    public String getNameWeb() {
        return nameWeb;
    }

    public void setNameWeb(String nameWeb) {
        this.nameWeb= nameWeb;
    }

    public String getBaca_lebih_lanjut() {
        return baca_lebih_lanjut;
    }

    public void setBaca_lebih_lanjut(String baca_lebih_lanjut) {
        this.baca_lebih_lanjut = baca_lebih_lanjut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
