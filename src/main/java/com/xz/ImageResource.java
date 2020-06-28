package com.xz;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Package: com.xz
 * @ClassName: ImageUtils
 * @Author: xz
 * @Date: 2020/6/26 14:54
 * @Version: 1.0
 */
public class ImageResource {

    public static BufferedImage goodTankL, goodTankU, goodTankD, goodTankR, badTankL, badTankR, badTankU, badTankD, bulletU, bulletD, bulletL, bulletR;

    public static BufferedImage[] bombImg = new BufferedImage[16];

    static {
        try {
            goodTankU = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/GoodTank1.png"));
            badTankU = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/BadTank1.png"));
            bulletU = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/bulletU.png"));
            goodTankL = rotateImage(goodTankU, -90);
            goodTankR = rotateImage(goodTankU, 90);
            goodTankD = rotateImage(goodTankU, 180);
            badTankL = rotateImage(badTankU, -90);
            badTankR = rotateImage(badTankU, 90);
            badTankD = rotateImage(badTankU, 180);
            bulletL = rotateImage(bulletU, -90);
            bulletR = rotateImage(bulletU, 90);
            bulletD = rotateImage(bulletU, 180);
            for (int i = 0; i < 16; i++) {
                bombImg[i] = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 旋转图片为指定角度
     *
     * @param bufferedimage 目标图像
     * @param degree        旋转角度
     * @return
     */
    public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(h, w, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2 + (w > h ? (w - h) / 2 : (h - w) / 2));
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}
