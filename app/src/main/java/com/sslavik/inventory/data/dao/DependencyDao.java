package com.sslavik.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sslavik.inventory.data.model.Dependency;

import java.util.List;

@Dao
public interface DependencyDao {
    @Query ("DELETE FROM dependency")
    void deleteAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Dependency dependency);

    @Delete
    void delete(Dependency dependency);

    @Update
    void update (Dependency dependency);

    @Query("SELECT * FROM dependency ORDER BY shortName")
    List<Dependency> getAll();

    @Query("SELECT * FROM dependency WHERE shortName = :shortName")
    Dependency find(String shortName);
}