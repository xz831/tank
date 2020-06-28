package com.xz;

/**
 * @Package: com.xz
 * @ClassName: Coordinate
 * @Author: xz
 * @Date: 2020/6/27 15:50
 * @Version: 1.0
 */
public abstract class BaseTank extends AbstractGameObject{

    abstract int getX();

    abstract int getY();

    abstract boolean isLiving();

    abstract void die();
}
