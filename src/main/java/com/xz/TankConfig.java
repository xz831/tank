package com.xz;

/**
 * @Package: com.xz
 * @ClassName: TankConfig
 * @Author: xz
 * @Date: 2020/6/27 16:17
 * @Version: 1.0
 */
public class TankConfig {
    private int speed = 5;
    private int fireSpeed = 100;

    public TankConfig(int speed, int fireSpeed) {
        this.speed = speed;
        this.fireSpeed = fireSpeed;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(int fireSpeed) {
        this.fireSpeed = fireSpeed;
    }
}
