package com.wposs.danko.interfaces;


import com.wposs.danko.model.JsonResponse;

public interface OnResponseInterface {

    void finish_consumer_services();
    void finish_fail_consumer_services();


    JsonResponse jsonResponse = new JsonResponse();

}
