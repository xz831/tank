package com.xz.collider;

import com.xz.AbstractGameObject;
import com.xz.Bullet;
import com.xz.Wall;

/**
 * @Package: com.xz.collider
 * @ClassName: BulletWallCollider
 * @Author: xz
 * @Date: 2020/6/29 15:29
 * @Version: 1.0
 */
public class BulletWallCollider implements Collider{
    @Override
    public void collide(AbstractGameObject o1, AbstractGameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet) o1;
            if(!bullet.isLiving()){
                return;
            }
            Wall wall = (Wall) o2;
            if(ColliderUtil.collideCheck(bullet,wall)){
                bullet.die();
            }
        }else if(o2 instanceof Bullet && o1 instanceof Wall){
            collide(o2,o1);
        }
    }
}
