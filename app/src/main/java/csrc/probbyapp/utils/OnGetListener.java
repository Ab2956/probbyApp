package csrc.probbyapp.utils;

public interface OnGetListener<T> {
    void onSuccess(T data);
    void onFailure(Exception e);
}

