package com.xz;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.xz.Config.*;

/**
 * @Package: com.xz
 * @ClassName: TankFrame
 * @Author: xz
 * @Date: 2020/6/25 13:55
 * @Version: 1.0
 */
public class TankFrame extends Frame {

    public static final TankFrame INSTANCE = new TankFrame();

    private Image offScreenImage;

    private GameModel gameModel = new GameModel();

    private TankFrame() throws HeadlessException {
        super(gameName);
        init();
    }

    private void init() {
        setLocation(locationX, locationY);
        setSize(gameWidth, gameHeight);
        setVisible(true);
        initKeyListener();
    }

    private void initKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameModel.getTank().keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameModel.getTank().keyReleased(e);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(gameWidth, gameHeight);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, gameWidth, gameHeight);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gameModel.paint(g);
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }
}
