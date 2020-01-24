package com.sslavik.inventory.ui.dependency;

import com.sslavik.inventory.R;
import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.data.repository.DependencyRepository;


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
        view.onSuccessValidate(dependency);
    }

    @Override
    public void add(Dependency dependency) {
        if(DependencyRepository.getInstance().add(dependency) == -1)
            view.showError(R.string.err_duplicate_dependency);
    }

    @Override
    public void edit(Dependency dependency) {
        if(!DependencyRepository.getInstance().edit(dependency))
            view.showError(R.string.errDependencyNoEdited);
    }
}
