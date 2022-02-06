package com.wposs.danko.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.wposs.danko.R;

public class ActivitySplash  extends AppCompatActivity {

    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        animation = AnimationUtils.loadAnimation(this, R.anim.transicion);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                nextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        nextActivity();

    }

    private void nextActivity ( ){
        Intent intent = new Intent(this, ActivityLogin.class);
        startActivity(intent);
        this.finish();
    }
}