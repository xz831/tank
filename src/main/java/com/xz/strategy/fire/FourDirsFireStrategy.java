package com.xz.strategy.fire;

import com.xz.*;

import static com.xz.Config.playerTankFireSpeedPerSecond;

/**
 * @Package: com.xz.strategy.fire
 * @ClassName: FourDirsFireStrategy
 * @Author: xz
 * @Date: 2020/6/28 16:15
 * @Version: 1.0
 */
public class FourDirsFireStrategy implements FireStrategy{
    @Override
    public void fire(Player player) {
        if (player.getLastFireTimeStamp() == null || player.getLastFireTimeStamp() + 60 * 1000 / playerTankFireSpeedPerSecond <= System.currentTimeMillis()) {
            new Bullet(player.getX() - 20, player.getY() + 15, Dir.L, player.getGroup());
            new Bullet(player.getX() + 15, player.getY() + 55, Dir.D, player.getGroup());
            new Bullet(player.getX() + 50, player.getY() + 15, Dir.R, player.getGroup());
            new Bullet(player.getX() + 15, player.getY() - 20, Dir.U, player.getGroup());
            player.setLastFireTimeStamp(System.currentTimeMillis());
            Sound.fireSound();
        }
    }
}
