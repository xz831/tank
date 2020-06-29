package com.xz.collider;

import com.xz.AbstractGameObject;
import com.xz.BaseTank;

/**
 * @Package: com.xz.collider
 * @ClassName: TankTankCollider
 * @Author: xz
 * @Date: 2020/6/29 17:23
 * @Version: 1.0
 */
public class TankTankCollider implements Collider{
    @Override
    public void collide(AbstractGameObject o1, AbstractGameObject o2) {
        if(o1 instanceof BaseTank && o2 instanceof BaseTank){
            if(ColliderUtil.collideCheck(o1,o2)){
                ((BaseTank) o1).back();
                ((BaseTank) o2).back();
            }
        }
    }
}
