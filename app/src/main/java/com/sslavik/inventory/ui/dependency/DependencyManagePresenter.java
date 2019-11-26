package com.sslavik.inventory.ui.dependency;

import com.sslavik.inventory.data.model.Dependency;

public class DependencyManagePresenter implements DependencyManageContract.Presenter {

    private DependencyManageContract.View view;

    public DependencyManagePresenter(DependencyManageContract.View view) {
        this.view = view;
    }

    /**
     * Valida RN2,RN3, RN4.
     *
     * @param dependency
     */

    @Override
    public void validateDependency(Dependency dependency) {
        view.onSuccessValidate();
    }

    @Override
    public void add(Dependency dependency) {

    }

    @Override
    public void edit(Dependency dependency) {

    }
}
