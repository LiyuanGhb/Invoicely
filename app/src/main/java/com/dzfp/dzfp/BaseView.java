package com.dzfp.dzfp;

import android.content.Context;

public interface BaseView<T> {
    //void setPresenter(T presenter);

    void hintMessage(String msg);

    Context getViewContext();
}
