package com.xz;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.xz.Config.soundOpen;

/**
 * @Package: com.xz
 * @ClassName: Sound
 * @Author: xz
 * @Date: 2020/6/28 12:23
 * @Version: 1.0
 */
public class Sound {


    private Sound() {
    }

    public static void explodeSound() {
        if (soundOpen) {
            new Thread(() -> new Audio("audio/explode.wav").play()).start();
        }
    }

    public static void fireSound() {
        if (soundOpen) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
            new SynchronousQueue<>(), (r) -> new Thread("moveSound"), new ThreadPoolExecutor.DiscardPolicy());

    public static void moveSound() {
        if (soundOpen) {
            THREAD_POOL_EXECUTOR.execute(() -> new Audio("audio/tank_move.wav").play());
//            new Thread().start();
        }
    }

    public static void backgroundSound() {
        if (soundOpen) {
            new Thread(() -> new Audio("audio/war1.wav").loop()).start();
        }
    }
}
