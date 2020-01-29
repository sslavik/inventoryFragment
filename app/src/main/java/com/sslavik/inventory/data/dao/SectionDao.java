package com.sslavik.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.sslavik.inventory.data.model.Section;

import java.util.List;

@Dao
public interface SectionDao {

    @Query( "DELETE FROM section")
    void deleteAll();
    @Delete
    void delete(Section section);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Section section);
    @Update
    void update(Section section);
    @Query("SELECT * FROM section")
    List<Section> getAll();
    @Query("SELECT * FROM section WHERE shortName = :shortName")
    Section find(String shortName);
}
