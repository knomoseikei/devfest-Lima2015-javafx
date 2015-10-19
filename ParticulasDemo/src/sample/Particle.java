package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Created by knomo on 10/18/15.
 */
public class Particle {
    private static final double GRAVITY = 0.06;

    double alpha, fade;
    final double easing;
    double posX, posY;
    double velX, velY;
    final double targetX, targetY;
    final Paint color;
    final int radius;
    final boolean usePhysics;
    final boolean shouldExplodeChildren;
    double lastPosX, lastPosY;

    public Particle(double posX, double posY, double velX, double velY, double targetX, double targetY,
                    Paint color, int radius, boolean usePhysics, boolean shouldExplodeChildren) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.targetX = targetX;
        this.targetY = targetY;
        this.color = color;
        this.radius = radius;
        this.usePhysics = usePhysics;
        this.shouldExplodeChildren = shouldExplodeChildren;
        this.alpha = 1;
        this.easing = Math.random() * 0.02;
        this.fade = Math.random() * 0.1;
    }

    public boolean update() {
        lastPosX = posX;
        lastPosY = posY;
        if (this.usePhysics) { // on way down
            velY += GRAVITY;
            posY += velY;
            this.alpha -= this.fade; // fade out particle
        } else { // on way up
            double distance = (targetY - posY);
            posY += distance * (0.03 + easing); // ease the position
            alpha = Math.min(distance * distance * 0.00005, 1);// cap to 1
        }
        posX += velX;
        return alpha < 0.005;
    }

    public void draw(GraphicsContext context) {
        final double x = Math.round(posX);
        final double y = Math.round(posY);
        context.setGlobalAlpha(Math.random() * this.alpha); // set the opacity for all drawing of this particle
        context.setFill(color); // draw particle
        context.fillOval(x - radius, y - radius, radius + radius, radius + radius);
    }
}
