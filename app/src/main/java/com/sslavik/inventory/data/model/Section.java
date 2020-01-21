package com.sslavik.inventory.data.model;

import java.io.Serializable;

public class Section implements Serializable {

    /*
    * Nombre
    * Nombre Corto
    * Dependencia
    * Descripcion
    * Imagen Seccion*/
    // CAMPOS
    private String name;
    private String shortName;
    private Dependency dependency;
    private String description;
    private int imageSection;

    // CONSTRUCTOR

    public Section(String name, String shortName, Dependency dependency, String description, int imageSection) {
        this.name = name;
        this.shortName = shortName;
        this.dependency = dependency;
        this.description = description;
        this.imageSection = imageSection;
    }

    // GETTERS AND SETTERS


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

    public Dependency getDependency() {
        return dependency;
    }

    public void setDependency(Dependency dependency) {
        this.dependency = dependency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageSection() {
        return imageSection;
    }

    public void setImageSection(int imageSection) {
        this.imageSection = imageSection;
    }
}
