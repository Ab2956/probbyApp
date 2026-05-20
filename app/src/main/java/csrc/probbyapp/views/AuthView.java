package csrc.probbyapp.views;

import android.view.View;

public interface AuthView {

    // interface for the login and register fragments

    default void navigateTo(){}
    default void showMessage(String s, View v){}
}
