package com.xz.collider;

import com.xz.AbstractGameObject;
import com.xz.BaseTank;
import com.xz.Bullet;

/**
 * @Package: com.xz.collider
 * @ClassName: BulletTankCollider
 * @Author: xz
 * @Date: 2020/6/29 14:16
 * @Version: 1.0
 */
public class BulletTankCollider implements Collider{
    @Override
    public void collide(AbstractGameObject o1, AbstractGameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof BaseTank){
            Bullet bullet = (Bullet) o1;
            BaseTank baseTank = (BaseTank) o2;
            bullet.collidesWithTank(baseTank);
        }else if(o2 instanceof Bullet && o1 instanceof BaseTank){
            collide(o2,o1);
        }
    }
}
