package com.soulmatexd.alidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MemoryLeakActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);
        //开了一个匿名内部类，sleep1000秒， 因此当反复在Activity1 和Activity2 之间切换时，
        // Activity2 因被该runnable持有，无法被回收。
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();


    }
}
