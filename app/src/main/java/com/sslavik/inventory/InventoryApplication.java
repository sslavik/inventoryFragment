package com.sslavik.inventory;

import android.app.Application;

import com.sslavik.inventory.data.InventoryDatabase;

public class InventoryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // CREAMOS LA BASE DE DATOS PARA EL CONTEXTO DE TODA LA APP
        InventoryDatabase.create(this);
    }
}
