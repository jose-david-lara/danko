package com.wposs.alfa_rrhh.services;

import com.wposs.alfa_rrhh.model.JsonResponse;

public interface OnResponseInterface {

    void finish_consumer_services();
    void finish_fail_consumer_services();


    JsonResponse jsonResponse = new JsonResponse();

}
