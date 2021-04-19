package com.example.talendar;

public interface BaseView<T> {
    void setPresenter(T presenter);
    void showSnackBar(int code, String message);
    void showToast(String message);
}
