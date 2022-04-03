package com.wposs.danko.interfaces;


import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.model.UserModel;

public interface OnResponseInterface {

    void finish_consumer_services(JsonResponse jsonResponse);
    void finish_fail_consumer_services();


    JsonResponse jsonResponse = new JsonResponse();


}
