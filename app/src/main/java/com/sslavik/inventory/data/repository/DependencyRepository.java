package com.sslavik.inventory.data.repository;

import android.os.AsyncTask;

import com.sslavik.inventory.InventoryApplication;
import com.sslavik.inventory.R;
import com.sslavik.inventory.data.InventoryDatabase;
import com.sslavik.inventory.data.dao.DependencyDao;
import com.sslavik.inventory.data.model.Dependency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class DependencyRepository {
    // CAMPOS
    private List<Dependency> listDependency;
    // INICIALIZAMOS EL SINGLETONE
    private volatile static DependencyRepository repository;
    // DAO
    DependencyDao dependencyDao;

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
        // INICIAMOS EL DAO
        dependencyDao = InventoryDatabase.getDatabase().dependencyDao();
        listDependency = new ArrayList<>();
        initialiceListOfDependency();

    }

    /**
     * Este método instancia el ArrayList de Dependencias
     */
    private void initialiceListOfDependency() {
        // Añadimos dependencias a la lista
        add(new Dependency("2º Ciclo Formativo Grado Superior", "2CFGS", "Aula informática","","https://images.com/achis.png"));
        add(new Dependency("1º Ciclo Formativo Grado Superior", "1CFGS", "Aula informática","", "https://images.com/achis.png"));
        add(new Dependency("2º Ciclo Formativo Grado Medio", "2CFGM", "Aula informática","", "https://images.com/achis.png"));
    }

    public List<Dependency> getList() throws ExecutionException, InterruptedException {

        return new AsyncTask<Void, Void, List<Dependency>>() {
            @Override
            protected List<Dependency> doInBackground(Void... voids) {
                return dependencyDao.getAll();
            }
        }.doInBackground();
    }

    public void add(Dependency dependency){
        // DATABASE
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.insert(dependency));

        if(!listDependency.contains(dependency)) {
            listDependency.add(dependency);
        }
    }

    public boolean edit(Dependency dependency){
        // DATABASE
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.update(dependency));

        for(Dependency d : listDependency){
            if(d.equals(dependency)){
                d.setName(dependency.getName());
                d.setDescription(dependency.getDescription());
                d.setInventory(dependency.getInventory());
                return true;
            }
        }
        return false;
    }

    public boolean delete(Dependency dependency){
        // DATABASE
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.delete(dependency));


        for(int i = 0; i < listDependency.size(); i-=-1){
            if(listDependency.get(i).equals(dependency)){
                listDependency.remove(i);
                return true;
            }
        }
        return false;
    }

    private class QueryAsyncClass extends AsyncTask<Void,Void,List<Dependency>>{

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return dependencyDao.getAll();
        }
    }
}
