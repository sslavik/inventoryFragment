package com.sslavik.inventory.ui.dependency;

import com.sslavik.inventory.data.model.Dependency;
import com.sslavik.inventory.ui.base.BaseView;

public interface DependencyManageContract {

    interface View extends BaseView<Presenter> {
        void onSuccessValidate();
    }

    interface Presenter {
        void validateDependency(Dependency dependency);

        void add(Dependency dependency);

        void edit(Dependency dependency);
    }
}
