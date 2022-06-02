package com.wposs.danko.business.presenter;

import android.content.Context;

import com.wposs.danko.business.ActivityBusiness;
import com.wposs.danko.business.interfaces.Business_adaper;


public class Business_presenter implements Business_adaper.Presenter {

    Business_adaper.View view = new ActivityBusiness();

    @Override
    public void setResponse(String url, String name, Context context) {
        view.showResponse(url, name, context);
    }
}
