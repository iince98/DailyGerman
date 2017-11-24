package com.example.iince98.dailygerman;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by iince98 on 24/11/2017.
 */
public class Splash extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageView imageView= (ImageView)findViewById(R.id.imageView);

        Animation myanimation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.gecis);
        imageView.startAnimation(myanimation);

        Thread zamanlayıcı= new Thread() {

            public void run () {
                try {
                    sleep(5000);
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        zamanlayıcı.start();
    }
}