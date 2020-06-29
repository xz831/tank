package com.xz.collider;

import com.xz.AbstractGameObject;

import java.awt.*;

/**
 * @Package: com.xz.collider
 * @ClassName: ColliderUtil
 * @Author: xz
 * @Date: 2020/6/29 15:43
 * @Version: 1.0
 */
public class ColliderUtil {

    public static boolean collideCheck(AbstractGameObject o1,AbstractGameObject o2){
        Rectangle rect1 = new Rectangle(o1.getX(), o1.getY(), o1.getWidth(), o1.getHeight());
        Rectangle rect2 = new Rectangle(o2.getX(), o2.getY(), o2.getWidth(), o2.getHeight());
        return rect1.intersects(rect2);
    }
}
