package com.example.khwajabilal.structural_a8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread() {
            public void run() {
                try {
                    //Toast.makeText(Splash_Activity.this, "splash!", Toast.LENGTH_SHORT).show();
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Toast.makeText(Splash_Activity.this, "Splash statrted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Splash.this, Selcet_Structure_Type.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timerThread.start();
    }
}
