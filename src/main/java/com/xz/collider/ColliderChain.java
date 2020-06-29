package com.xz.collider;

import com.xz.AbstractGameObject;
import com.xz.Config;

import java.util.List;

/**
 * @Package: com.xz.collider
 * @ClassName: ColliderChain
 * @Author: xz
 * @Date: 2020/6/29 14:24
 * @Version: 1.0
 */
public class ColliderChain implements Collider{

    public ColliderChain() {
        colliderList = Config.colliderList;
    }

    private final List<Collider> colliderList;

    private int step = 0;

    @Override
    public void collide(AbstractGameObject o1, AbstractGameObject o2) {
        if (step >= colliderList.size()) {
            step = 0;
        } else {
            if(!o1.isLiving() || !o2.isLiving()){
                step = 0;
                return;
            }
            colliderList.get(step++).collide(o1, o2);
            collide(o1, o2);
        }
    }
}
