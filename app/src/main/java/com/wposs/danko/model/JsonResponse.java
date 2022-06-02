package com.wposs.danko.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonResponse {
    private JsonObject jsonObjetResponse;
    private JsonObject jsonResponseData;

    public JsonObject getJsonObjetResponse() {
        return jsonObjetResponse;
    }

    public void setJsonObjetResponse(JsonObject jsonObjetResponse) {
        this.jsonObjetResponse = jsonObjetResponse;
    }

    public JsonObject getJsonResponseData() {
        return jsonResponseData;
    }

    public void setJsonResponseData(JsonObject jsonResponseData) {
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
