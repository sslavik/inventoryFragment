package com.sslavik.inventory.data.model;

public class Section {

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

    //
}
