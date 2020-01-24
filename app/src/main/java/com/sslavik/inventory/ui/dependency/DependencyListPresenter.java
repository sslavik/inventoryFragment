package com.sslavik.inventory.ui.dependency;

import android.os.AsyncTask;
import android.widget.Toast;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.repository.DependencyRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DependencyListPresenter implements DependencyListContract.Presenter{

    // INTERFAZ
    public interface onLoadDependencyRepository{
        void onSuccessLoadList(List<Dependency> dependencyList);
    }

    // DELEGADO
    DependencyListContract.View view;
    onLoadDependencyRepository onLoadDependencyRepository;

    public DependencyListPresenter (DependencyListContract.View view){
        this.view = view;
        initDelegates();
    }

    @Override
    public void delete(Dependency dependency) {
        // 1 REALIZAMOS LA OPERACIÓN DEL REPOSITORIO y COMPROBAMSO ELRESULTADO
        DependencyRepository.getInstance().delete(dependency);
        view.onSuccessDeleted();
    }

    @Override
    public void load() {

        // SACAMOS LOS DATOS DEL REPOSITORY
        /*
        new AsyncTask<Void,Void, List<Dependency>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                view.showLoadProgress();
            }

            @Override
            protected List<Dependency> doInBackground (Void...voids){
                try {
                    return DependencyRepository.getInstance().getList();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return new ArrayList<Dependency>();
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(List<Dependency> dependencyList) {
                super.onPostExecute(dependencyList);
                view.hideLoadProgress();
                if(dependencyList.isEmpty())
                    view.showNoData();
                else
                    view.showData(dependencyList);
            }
        }.execute();

         */
        view.showLoadProgress();
        List<Dependency> dependencyList = DependencyRepository.getInstance().getList(onLoadDependencyRepository);
        view.hideLoadProgress();
        if(dependencyList.isEmpty())
            view.showNoData();
        else
            view.showData(dependencyList);

    }

    // ESTE ADD HACE LA FUNCION DE UNDO CUANDO SE BORRA UN ELEMENTO
    @Override
    public void add(Dependency dependency) {
        DependencyRepository.getInstance().add(dependency);

    }

    private class LoadDataTask extends  AsyncTask<Void,Void, List<Dependency>>{

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return null;
        }
    }

    // METODOS PROPIOS


    private void initDelegates() {
        onLoadDependencyRepository = new onLoadDependencyRepository() {

            @Override
            public void onSuccessLoadList(List<Dependency> dependencyList) {
                view.hideLoadProgress();
                if(dependencyList.isEmpty())
                    view.showNoData();
                else
                    view.showData(dependencyList);
            }
        };
    }

}
