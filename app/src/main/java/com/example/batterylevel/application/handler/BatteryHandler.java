package com.example.batterylevel.application.handler;

import android.os.Handler;
import android.os.Looper;

public class BatteryHandler {

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static void updateBatteryStatus(Runnable runnable) {
        handler.post(runnable);
    }

}
