package com.wposs.danko.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonResponse {
    private JsonObject jsonObjetResponse;
    private JsonArray jsonResponseModel;

    public JsonObject getJsonObjetResponse() {
        return jsonObjetResponse;
    }

    public void setJsonObjetResponse(JsonObject jsonObjetResponse) {
        this.jsonObjetResponse = jsonObjetResponse;
    }

    public JsonArray getJsonResponseData() {
        return jsonResponseModel;
    }

    public void setJsonResponseModel(JsonArray jsonResponseModel) {
        this.jsonResponseModel = jsonResponseModel;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "jsonObjetResponse=" + jsonObjetResponse +
                ", jsonResponseModel=" + jsonResponseModel +
                '}';
    }
}
