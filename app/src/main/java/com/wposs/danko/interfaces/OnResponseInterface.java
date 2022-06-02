package com.wposs.danko.interfaces;


import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.model.UserModel;

public interface OnResponseInterface {

    boolean finish_consumer_services(JsonResponse jsonResponse);
    boolean finish_fail_consumer_services();


    JsonResponse jsonResponse = new JsonResponse();


}
