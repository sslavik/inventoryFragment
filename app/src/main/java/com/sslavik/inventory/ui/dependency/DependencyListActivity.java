package com.sslavik.inventory.ui.dependency;

import androidx.annotation.NonNull;

/**
 * Clase Modelo Dependency
 */
public class DependencyListActivity {
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
}
