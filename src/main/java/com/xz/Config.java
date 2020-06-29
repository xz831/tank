package com.xz;

import com.xz.collider.Collider;
import com.xz.strategy.fire.FireStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.xz.Constant.*;

/**
 * @Package: com.xz
 * @ClassName: Config
 * @Author: xz
 * @Date: 2020/6/28 15:09
 * @Version: 1.0
 */
public class Config {

    public static int enemyTankCount;
    public static int gameHeight;
    public static int gameWidth;
    public static String gameName;
    public static int locationX;
    public static int locationY;
    public static int playerX;
    public static int playerY;
    public static int bulletSpeed;
    public static int enemyTankMoveSpeed;
    public static int enemyTankFireSpeedPerSecond;
    public static int playerTankMoveSpeed;
    public static int playerTankFireSpeedPerSecond;
    public static boolean soundOpen;
    public static FireStrategy fireStrategy;
    public static boolean invincible;
    public static List<Collider> colliderList;
    static {
        Properties properties = new java.util.Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        enemyTankCount = Integer.parseInt(properties.getProperty(ENEMY_TANK_COUNT));
        gameHeight = Integer.parseInt(properties.getProperty(GAME_HEIGHT));
        gameWidth = Integer.parseInt(properties.getProperty(GAME_WIDTH));
        gameName = properties.getProperty(GAME_NAME);
        locationX = Integer.parseInt(properties.getProperty(LOCATION_X));
        locationY = Integer.parseInt(properties.getProperty(LOCATION_Y));
        playerX = Integer.parseInt(properties.getProperty(PLAYER_X));
        playerY = Integer.parseInt(properties.getProperty(PLAYER_Y));
        bulletSpeed = Integer.parseInt(properties.getProperty(BULLET_SPEED));
        enemyTankMoveSpeed = Integer.parseInt(properties.getProperty(ENEMY_TANK_MOVE_SPEED));
        enemyTankFireSpeedPerSecond = Integer.parseInt(properties.getProperty(ENEMY_TANK_FIRE_SPEED_PER_SECOND));
        playerTankMoveSpeed = Integer.parseInt(properties.getProperty(PLAYER_TANK_MOVE_SPEED));
        playerTankFireSpeedPerSecond = Integer.parseInt(properties.getProperty(PLAYER_TANK_FIRE_SPEED_PER_SECOND));
        soundOpen = Boolean.parseBoolean(properties.getProperty(SOUND_OPEN));
        invincible = Boolean.parseBoolean(properties.getProperty(INVINCIBLE));

        try {
            String fireStrategyClassName = properties.getProperty(FIRE_STRATEGY);
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass("com.xz.strategy.fire." + fireStrategyClassName);
            fireStrategy = (FireStrategy) clazz.newInstance();
            colliderList = new ArrayList<>();
            String colliderClassName = properties.getProperty(COLLIDER_CHAIN);
            for (String name : colliderClassName.split(",")) {
                Class<?> clazzItem = Thread.currentThread().getContextClassLoader().loadClass("com.xz.collider." + name);
                colliderList.add((Collider) clazzItem.newInstance());
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
