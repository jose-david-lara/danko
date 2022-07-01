package com.wposs.danko.dto;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonResponse {
    private JsonObject jsonObjetResponse;
    private JsonObject jsonResponseData;
    private JsonArray jsonResponseDataArray;

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

    public JsonArray getJsonResponseDataArray() {
        return jsonResponseDataArray;
    }

    public void setJsonResponseDataArray(JsonArray jsonResponseDataArray) {
        this.jsonResponseDataArray = jsonResponseDataArray;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "jsonObjetResponse=" + jsonObjetResponse +
                ", jsonResponseData=" + jsonResponseData +
                ", jsonResponseDataArray=" + jsonResponseDataArray +
                '}';
    }
}
