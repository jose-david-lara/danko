package com.wposs.danko.parameters.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wposs.danko.R;
import com.wposs.danko.home.view.ActivityHome;
import com.wposs.danko.interfaces.DialogoInterface;
import com.wposs.danko.interfaces.ModelInterface;
import com.wposs.danko.login.dto.UserDTO;
import com.wposs.danko.login.view.ActivityLogin;
import com.wposs.danko.parameters.service.ParametersService;
import com.wposs.danko.splash.view.ActivitySplash;
import com.wposs.danko.test.view.TestView;
import com.wposs.danko.utils.Defines;
import com.wposs.danko.utils.UtilsClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActivityParameters extends AppCompatActivity {

    private AnimationDrawable animation;
    private Animation transition;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        iniComponents();
        loading = findViewById(R.id.loading);
        loading.setBackgroundResource(R.drawable.loading);
        loading.setVisibility(View.INVISIBLE);
        animation = (AnimationDrawable) loading.getBackground();
        animation.start();

        transition = AnimationUtils.loadAnimation(this,R.anim.transicion);
        loading.startAnimation(transition);
        transition.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                initParameters();
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });



    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void iniComponents(){
    }

    public void initParameters(){

        try {
            Map<String, Object> requestLocationsDefault =  new ParametersService().getUserInfoService(ModelInterface.user.getUser(),ActivityParameters.this);
            new ParametersService().getLocationsDefaultService(ActivityParameters.this, requestLocationsDefault, ActivityParameters.this);
        }catch (Exception e){
            e.printStackTrace();
            new UtilsClass().dialogMessage(this, new DialogoInterface() {
                @Override
                public void accepted() {
                    backActivity();
                }

                @Override
                public void denied() {

                }
            }, "Error inesperado, intente de nuevo por favor", R.drawable.ic_alert);
        }

    }

    public void responseGetLocationsDefault(Map<String, Object> responseService){
        animation.stop();

        if(Objects.requireNonNull(responseService.get("error")).equals(true)){
            new UtilsClass().dialogMessage(this, new DialogoInterface() {
                @Override
                public void accepted() {
                    backActivity();
                }

                @Override
                public void denied() {

                }
            }, "Error en descarga de datos, por favor intente de nuevo", R.drawable.ic_alert);
        }else{
            nextActivity();
        }


    }

    private void nextActivity ( ){
        animation.stop();
        Intent intent = new Intent(this, ActivityHome.class);
        startActivity(intent);
        this.finish();
    }

    private void backActivity(){
        animation.stop();
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        this.finish();
    }
}
