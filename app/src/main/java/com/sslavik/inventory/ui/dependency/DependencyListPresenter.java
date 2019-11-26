package com.sslavik.inventory.ui.dependency;

import com.sslavik.inventory.data.model.Dependency;

public class DependencyListPresenter implements DependencyListContract.Presenter {

    // DELEGADO
    DependencyListContract.View view;

    public DependencyListPresenter (DependencyListContract.View view){
        this.view = view;
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void load() {

    }
}
