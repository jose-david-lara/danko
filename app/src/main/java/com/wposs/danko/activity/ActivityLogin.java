package com.wposs.danko.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wposs.danko.R;
import com.wposs.danko.interfaces.DialogoInterface;
import com.wposs.danko.interfaces.OnResponseInterface;
import com.wposs.danko.io.ConsumeServicesExpress;
import com.wposs.danko.model.JsonResponse;
import com.wposs.danko.utils.Defines;
import com.wposs.danko.utils.UtilsClass;

import java.util.HashMap;
import java.util.Map;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEnter;
    private EditText capUser;
    private EditText capPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initComponents();
        //events();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonEnter.getId()) {
            if(validateField()){
                Toast.makeText(this, "Transaccion en proceso", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initComponents(){
        buttonEnter = (Button) findViewById(R.id.buttonEnter);

        capUser = (EditText) findViewById(R.id.capUser);
        capPass = (EditText) findViewById(R.id.capPass);

    }

    private boolean validateField(){

        if(capPass.getText().length() == 0 && capUser.getText().length() == 0){
            new UtilsClass().dialogMessage(this, new DialogoInterface() {
                @Override
                public void accepted() {

                }

                @Override
                public void denied() {

                }
            }, "", R.drawable.error);
        }


        return true;
    }




    public void echoTest() {
        new ConsumeServicesExpress().consume_api(Defines.USER_URL, new OnResponseInterface() {
            @Override
            public void finish_consumer_services(JsonResponse jsonResponse) {
                Toast.makeText(ActivityLogin.this, "Servidor En Linea", Toast.LENGTH_SHORT).show();


            }


            @Override
            public void finish_fail_consumer_services() {
                Toast.makeText(ActivityLogin.this, "Servidor En falla", Toast.LENGTH_SHORT).show();
            }
        });
    }


}