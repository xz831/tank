package com.xz.strategy.fire;

import com.xz.*;

import static com.xz.Config.playerTankFireSpeedPerSecond;
import static com.xz.ImageResource.goodTankU;

/**
 * @Package: com.xz.strategy.fire
 * @ClassName: AllDirsFireStrategy
 * @Author: xz
 * @Date: 2020/6/29 11:28
 * @Version: 1.0
 */
public class AllDirsFireStrategy implements FireStrategy {
    @Override
    public void fire(Player player) {
        if (player.getLastFireTimeStamp() == null || player.getLastFireTimeStamp() + 60 * 1000 / playerTankFireSpeedPerSecond <= System.currentTimeMillis()) {
            new Bullet(player.getX() - 20, player.getY() + 15, Dir.L, player.getGroup());
            new Bullet(player.getX() + 15, player.getY() + 55, Dir.D, player.getGroup());
            new Bullet(player.getX() + 50, player.getY() + 15, Dir.R, player.getGroup());
            new Bullet(player.getX() + 15, player.getY() - 20, Dir.U, player.getGroup());
            new Bullet(player.getX() + (goodTankU.getWidth() / 2) - (ImageResource.bulletU.getWidth() / 2), player.getY() + (goodTankU.getHeight() / 2) - (ImageResource.bulletU.getHeight() / 2), Dir.LD, player.getGroup());
            new Bullet(player.getX() + (goodTankU.getWidth() / 2) - (ImageResource.bulletU.getWidth() / 2), player.getY() + (goodTankU.getHeight() / 2) - (ImageResource.bulletU.getHeight() / 2), Dir.RD, player.getGroup());
            new Bullet(player.getX() + (goodTankU.getWidth() / 2) - (ImageResource.bulletU.getWidth() / 2), player.getY() + (goodTankU.getHeight() / 2) - (ImageResource.bulletU.getHeight() / 2), Dir.RU, player.getGroup());
            new Bullet(player.getX() + (goodTankU.getWidth() / 2) - (ImageResource.bulletU.getWidth() / 2), player.getY() + (goodTankU.getHeight() / 2) - (ImageResource.bulletU.getHeight() / 2), Dir.LU, player.getGroup());
            player.setLastFireTimeStamp(System.currentTimeMillis());
            Sound.fireSound();
        }
    }
}
