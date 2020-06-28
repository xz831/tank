package com.xz;

import java.util.concurrent.TimeUnit;

/**
 * @Package: com.xz
 * @ClassName: Main
 * @Author: xz
 * @Date: 2020/6/25 13:55
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        Sound.backgroundSound();
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }
    }
}
