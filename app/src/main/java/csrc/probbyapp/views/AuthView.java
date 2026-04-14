package csrc.probbyapp.views;

import android.view.View;

public interface AuthView {
    default void navigateTo(){}
    default void showMessage(String s, View v){}
}
