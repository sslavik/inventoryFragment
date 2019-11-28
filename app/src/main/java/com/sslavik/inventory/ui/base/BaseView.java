package com.sslavik.inventory.ui.base;

/**
 * Interfaz base para todas las vistas del proyecto.
 * @param <T>
 */
public interface BaseView<T> {

        void setPresenter(T presenter);
        void showError(int error);
}
