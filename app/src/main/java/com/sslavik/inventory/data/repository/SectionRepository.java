package com.sslavik.inventory.data.repository;

import com.sslavik.inventory.data.model.Section;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

public class SectionRepository {

    // CAMPOS
    static List<Section> sectionList;
    private volatile static SectionRepository sectionRepository;

    static {
        sectionRepository = new SectionRepository();
    }

    public static SectionRepository getInstance(){
        return sectionRepository;
    }

    // CONSTRUCTOR INTERNO
    private SectionRepository(){
        sectionList = new ArrayList<>();
        initSectionList();
    }
    // METODOS PROPIOS
    private void initSectionList() {
        sectionList.add(new Section("Section 1", "s1", null, "Section 1 Description Example", 0));
        sectionList.add(new Section("Section 2", "s2", null, "Section 2 Description Example", 0));
        sectionList.add(new Section("Section 3", "s3", null, "Section 3 Description Example", 0));
    }

    public Boolean add(Section section){
        if(sectionList.add(section))
            return true;
        return false;
    }

    public Boolean edit(Section section){
        for (int i = 0; i < sectionList.size() ; i++) {
            if(sectionList.get(i).equals(section)) {
                sectionList.set(i, section);
                return true;
            }
        }
        return false;
    }

    public Boolean delete(Section section){
        if(sectionList.remove(section))
            return true;
        return false;
    }


    public List<Section> getList() {
        return sectionList;
    }
}
