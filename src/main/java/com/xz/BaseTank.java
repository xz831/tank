package com.xz;

/**
 * @Package: com.xz
 * @ClassName: Coordinate
 * @Author: xz
 * @Date: 2020/6/27 15:50
 * @Version: 1.0
 */
public abstract class BaseTank extends AbstractGameObject{

    public abstract void die();

    public abstract Group getGroup();

    public abstract void back();
}
