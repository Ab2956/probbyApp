package csrc.probbyapp.utils;

public interface OnGetListener<T> {

    // Listener interface for getting data from the database and making UI updates

    void onSuccess(T data);
    void onFailure(Exception e);
}

