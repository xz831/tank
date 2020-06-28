package com.xz.strategy.fire;

import com.xz.Bullet;
import com.xz.Player;
import com.xz.Sound;
import com.xz.TankFrame;

import static com.xz.Config.playerTankFireSpeedPerSecond;

/**
 * @Package: com.xz.strategy.fire
 * @ClassName: DefaultFireStrategy
 * @Author: xz
 * @Date: 2020/6/28 16:08
 * @Version: 1.0
 */
public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Player player) {
        if (player.getLastFireTimeStamp() == null || player.getLastFireTimeStamp() + 60 * 1000 / playerTankFireSpeedPerSecond <= System.currentTimeMillis()) {
            Bullet bullet;
            switch (player.getDir()) {
                case L:
                    bullet = new Bullet(player.getX() - 20, player.getY() + 15, player.getDir(), player.getGroup());
                    break;
                case D:
                    bullet = new Bullet(player.getX() + 15, player.getY() + 55, player.getDir(), player.getGroup());
                    break;
                case R:
                    bullet = new Bullet(player.getX() + 50, player.getY() + 15, player.getDir(), player.getGroup());
                    break;
                case U:
                    bullet = new Bullet(player.getX() + 15, player.getY() - 20, player.getDir(), player.getGroup());
                    break;
                default:
                    return;
            }
            TankFrame.INSTANCE.addBullet(bullet);
            player.setLastFireTimeStamp(System.currentTimeMillis());
            Sound.fireSound();
        }
    }
}
