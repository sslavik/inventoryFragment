package com.sslavik.inventory.data.model;

import androidx.annotation.NonNull;

public class Dependency {
    private String name;
    private String shortName;
    private String description;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "Dependency \n" +
                "Nombre : " + getName()+
                "Nombre Corto : " + getShortName()+
                "Descripcion : " + getDescription()+
                "Imagen : " + getImage() ;
    }
    // CONSTRUCTOR

    public Dependency(String name, String shortName, String description, String image) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.image = image;
    }
}
