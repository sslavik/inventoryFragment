package com.sslavik.inventory.data.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Dependency.class,
                parentColumns = "shortName",
                childColumns = "dependency",
                onDelete = CASCADE
        )
})
public class Section implements Serializable {

    /*
    * Nombre
    * Nombre Corto
    * Dependencia
    * Descripcion
    * Imagen Seccion*/
    // CAMPOS
    @NonNull
    private String name;
    @PrimaryKey
    @NonNull
    private String shortName;
    @NonNull
    private String dependency;
    @NonNull
    private String description;
    @NonNull
    private int imageSection;

    // CONSTRUCTOR

    public Section(String name, String shortName, String dependency, String description, int imageSection) {
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

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
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

    // OVERRIDE

    @Override
    public boolean equals(@Nullable Object obj) {
        return this.getShortName().equals(((Section) obj).getShortName());
    }
}
