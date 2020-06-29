package com.xz.collider;

import com.xz.AbstractGameObject;

/**
 * @Package: com.xz.collider
 * @ClassName: Collider
 * @Author: xz
 * @Date: 2020/6/29 14:14
 * @Version: 1.0
 */
public interface Collider {
    /**
     * 碰撞
     * @param o1 物体1
     * @param o2 物体2
     */
    void collide(AbstractGameObject o1,AbstractGameObject o2);
}
