package csrc.probbyapp.views;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public interface AuthView {
    default void navigateTo(){}
    default void showMessage(String s, View v){}
}
