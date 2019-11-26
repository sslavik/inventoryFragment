package com.sslavik.inventory.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Objects;

public class Dependency implements Parcelable {
    private String name;
    private String shortName;
    private String description;
    private String inventory;
    private String image;

    // CONSTRUCTOR

    public Dependency(String name, String shortName, String description, String inventory, String image) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.inventory = inventory;
        this.image = image;
    }


    protected Dependency(Parcel in) {
        name = in.readString();
        shortName = in.readString();
        description = in.readString();
        inventory = in.readString();
        image = in.readString();
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };

    public Dependency() {

    }

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

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dependency that = (Dependency) obj;
        return shortName.equals(that.shortName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortName);
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

    @Override
    public int describeContents() {
        return CONTENTS_FILE_DESCRIPTOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortName);
        dest.writeString(description);
        dest.writeString(inventory);
        dest.writeString(image);
    }
}
