package com.wposs.danko.test.view;

import android.content.Context;

import com.wposs.danko.test.service.TestService;

public class TestView {

    public TestService testService;

    public void echoTest(Context context){
        testService = new TestService();

        testService.getTestService(context);

    }

}
