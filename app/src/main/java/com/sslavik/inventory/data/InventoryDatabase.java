package com.sslavik.inventory.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.sslavik.inventory.data.dao.DependencyDao;
import com.sslavik.inventory.data.model.Dependency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dependency.class}, version = 3)
public abstract class InventoryDatabase extends RoomDatabase {

    //DAOS
    // DECLARAMOS UN METODO QUE NOS DEVUELVE EL OBJETO DAO QUE ES ABSTRACTO /RARO PERO FUNCIONAL/
    public abstract DependencyDao dependencyDao();

    // CAMPOS DE BASE DE DATOS
    private static volatile InventoryDatabase INSTANCE;
    // GESTIONAMOS LOS HILOS QUE USA NUESTRA BASE DE DATOS PARA LAS CONSULTAS AQUÍ
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // CON NUESTRA ARQUITECTURA NECESITAREMOS CREAR LA BASE DE DATOS EN EL CONTEXTO DE NUESTRA VISTA.
    // PORQUE NO TENEMOS ACCESO DESDE EL REPOSITORY AL CONTEXTO DE LA VISTA
    public static void create(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "inventory")
                            .fallbackToDestructiveMigration() // SIRVE PARA REHACER LA MIGRACION CUANDO SE MODIFIQUE EL MODELO Y LA VERSION
                            .build();
                }
            }
        }
    }
    // CON ESTE METODO SE ACCEDERÍA DESDE EL REPOSITORIO A NUESTRO inventory_DB
    public static InventoryDatabase getDatabase() {
        return INSTANCE;
    }
}
