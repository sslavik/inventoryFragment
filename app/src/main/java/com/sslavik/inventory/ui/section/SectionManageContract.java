package com.sslavik.inventory.ui.section;

import android.view.View;

import com.sslavik.inventory.data.model.Section;
import com.sslavik.inventory.ui.base.BaseView;

public interface SectionManageContract {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter {
        void add(Section section);
        void manage(Section section);
    }
}
