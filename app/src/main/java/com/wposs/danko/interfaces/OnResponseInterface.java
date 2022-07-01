package com.wposs.danko.interfaces;


import com.wposs.danko.dto.JsonResponse;

public interface OnResponseInterface {

    boolean finish_consumer_services(JsonResponse jsonResponse);
    boolean finish_fail_consumer_services();


    JsonResponse jsonResponse = new JsonResponse();


}
