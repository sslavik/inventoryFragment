package com.sslavik.inventory.data.repository;

import com.sslavik.inventory.data.InventoryDatabase;
import com.sslavik.inventory.data.dao.SectionDao;
import com.sslavik.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.crypto.SecretKey;

public class SectionRepository {

    // CAMPOS
    static List<Section> sectionList;
    // DAO
    SectionDao sectionDao;
    private volatile static SectionRepository sectionRepository;

    static {
        sectionRepository = new SectionRepository();
    }

    public static SectionRepository getInstance(){
        return sectionRepository;
    }

    // CONSTRUCTOR INTERNO
    private SectionRepository(){
        sectionDao = InventoryDatabase.getDatabase().sectionDao();
        sectionList = new ArrayList<>();
        initSectionList();
    }
    // METODOS PROPIOS
    private void initSectionList() {
        add(new Section("Section 1", "s1", "2CFGS", "Section 1 Description Example", 0));
        add(new Section("Section 2", "s2", "2CFGS", "Section 2 Description Example", 0));
        add(new Section("Section 3", "s3", "2CFGS", "Section 3 Description Example", 0));
    }

    public Boolean add(Section section){

        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.insert(section));

        if(sectionList.add(section))
            return true;
        return false;
    }

    public Boolean edit(Section section){

        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.update(section));

        for (int i = 0; i < sectionList.size() ; i++) {
            if(sectionList.get(i).equals(section)) {
                sectionList.set(i, section);
                return true;
            }
        }
        return false;
    }

    public Boolean delete(Section section){

        InventoryDatabase.databaseWriteExecutor.execute(() -> sectionDao.delete(section));

        if(sectionList.remove(section))
            return true;
        return false;
    }


    public List<Section> getList() {
        try {
            return InventoryDatabase.databaseWriteExecutor.submit(() -> sectionDao.getAll()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
