package com.wposs.danko.splash.view;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wposs.danko.R;
import com.wposs.danko.login.view.ActivityLogin;
import com.wposs.danko.test.view.TestView;
import com.wposs.danko.utils.UtilsClass;

public class ActivitySplash  extends AppCompatActivity {

    private AnimationDrawable animation;
    private Animation transition;
    private ImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
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
                new TestView().echoTest(ActivitySplash.this);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                nextActivity();
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

    private void nextActivity ( ){
        animation.stop();
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        this.finish();
    }
}