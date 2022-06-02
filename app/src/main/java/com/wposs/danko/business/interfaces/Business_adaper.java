package com.wposs.danko.business.interfaces;

import android.content.Context;

public interface Business_adaper {

    interface View {
        void showResponse(String url, String name, Context context);
    }

    interface Presenter {
        void setResponse(String url, String name, Context context);
    }

}
