package com.xz.strategy.fire;

import com.xz.Player;

import java.io.Serializable;

/**
 * @Package: com.xz.strategy.fire
 * @ClassName: FireStrategy
 * @Author: xz
 * @Date: 2020/6/28 16:07
 * @Version: 1.0
 */
@FunctionalInterface
public interface FireStrategy extends Serializable {
    /**
     * 开火
     * @param player 玩家tank
     */
    void fire(Player player);
}
