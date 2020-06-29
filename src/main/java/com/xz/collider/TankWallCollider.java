package com.xz.collider;

import com.xz.AbstractGameObject;
import com.xz.BaseTank;
import com.xz.Enemy;
import com.xz.Wall;

/**
 * @Package: com.xz.collider
 * @ClassName: TankWallCollider
 * @Author: xz
 * @Date: 2020/6/29 16:58
 * @Version: 1.0
 */
public class TankWallCollider implements Collider{
    @Override
    public void collide(AbstractGameObject o1, AbstractGameObject o2) {
        if(o1 instanceof BaseTank && o2 instanceof Wall){
            if(ColliderUtil.collideCheck(o1,o2)){
                ((BaseTank)o1).back();
            }
        }else if(o2 instanceof BaseTank && o1 instanceof Wall){
            collide(o2,o1);
        }
    }
}
