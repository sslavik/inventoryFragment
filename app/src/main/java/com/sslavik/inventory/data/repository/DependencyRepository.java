package com.sslavik.inventory.data.repository;

import com.sslavik.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;

public class DependencyRepository {
    // CAMPOS
    private List<Dependency> listDependency;
    // INICIALIZAMOS EL SINGLETONE
    private volatile static DependencyRepository repository;

    public static DependencyRepository getInstance() {
        return repository;
    }
    /*
    SE inicializaen el siguiente bloque to0das las porpiedades estaticas de la clase sin tener que realizarlo en un método Estático
    */
    static { // SE REALIZA ESTA PARTE DEL CODIGO ANTES DEL CONSTRUCTOR.
        repository = new DependencyRepository();
    }

    // CONSTRUCTOR
    private DependencyRepository(){
        listDependency = new ArrayList<>();
        initialiceListOfDependency();

    }

    /**
     * Este método instancia el ArrayList de Dependencias
     */
    private void initialiceListOfDependency() {
        // Añadimos dependencias a la lista
        listDependency.add(new Dependency("2º Ciclo Formativo Grado Superior", "2CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Superior", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("2º Ciclo Formativo Grado Medio", "2CFGM", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
        listDependency.add(new Dependency("1º Ciclo Formativo Grado Medio", "1CFGS", "Aula informática", "https://images.com/achis.png"));
    }

    public List<Dependency> getList() {
        return listDependency;
    }
}
