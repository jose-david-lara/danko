package com.wposs.danko.home.presenter;

import android.content.Context;

import com.wposs.danko.home.view.ActivityHome;
import com.wposs.danko.home.interfaces.Home_interface;
import com.wposs.danko.login.dto.BusinessDTO;
import java.util.ArrayList;

public class HomePresenter implements Home_interface.Presenter {

    private Home_interface.View view = new ActivityHome();


    @Override
    public void setResponse(ArrayList<BusinessDTO> businessDTO, String id_categoria, String name_categoria, Context contex) {
        view.showResponse(businessDTO, id_categoria, name_categoria, contex);
    }
}
