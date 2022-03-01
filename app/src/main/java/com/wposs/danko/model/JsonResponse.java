package com.wposs.danko.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonResponse {
    private JsonObject jsonObjetResponse;
    private JsonArray jsonResponseData;

    public JsonObject getJsonObjetResponse() {
        return jsonObjetResponse;
    }

    public void setJsonObjetResponse(JsonObject jsonObjetResponse) {
        this.jsonObjetResponse = jsonObjetResponse;
    }

    public JsonArray getJsonResponseData() {
        return jsonResponseData;
    }

    public void setJsonResponseData(JsonArray jsonResponseData) {
        this.jsonResponseData = jsonResponseData;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "jsonObjetResponse=" + jsonObjetResponse +
                ", jsonResponseData=" + jsonResponseData +
                '}';
    }
}
