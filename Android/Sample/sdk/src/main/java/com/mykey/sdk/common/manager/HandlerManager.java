package com.mykey.sdk.common.manager;

import android.os.Handler;
import android.os.HandlerThread;

/**
 * Created by zero on 2019/5/27.
 */

public class HandlerManager {
    private static HandlerManager handlerManager = new HandlerManager();
    private Handler mainHandler = new Handler();
    private Handler slowHandler;
    private Handler collectHandler;

    private HandlerManager() {
        HandlerThread slowThread = new HandlerThread("slow");
        slowThread.start();
        slowHandler = new Handler(slowThread.getLooper());
        HandlerThread collectThread = new HandlerThread("collect");
        collectThread.start();
        collectHandler = new Handler(collectThread.getLooper());
    }

    public static HandlerManager getInstance() {
        return handlerManager;
    }

    public void init() {

    }

    public void postMain(Runnable runnable) {
        mainHandler.post(runnable);
    }

    public void postMainDelay(Runnable runnable, int delayTime) {
        mainHandler.postDelayed(runnable, delayTime);
    }

    public void removeMain(Runnable runnable) {
        mainHandler.removeCallbacks(runnable);
    }

    public void postSlow(Runnable runnable) {
        slowHandler.post(runnable);
    }

    public void postSlowDelay(Runnable runnable, int delayTime) {
        slowHandler.postDelayed(runnable, delayTime);
    }

    public void removeSlow(Runnable runnable) {
        slowHandler.removeCallbacks(runnable);
    }

    public void postCollect(Runnable runnable) {
        collectHandler.post(runnable);
    }

    public void postCollectDelay(Runnable runnable, int delayTime) {
        collectHandler.postDelayed(runnable, delayTime);
    }

    public void removeColect(Runnable runnable) {
        collectHandler.removeCallbacks(runnable);
    }
}
