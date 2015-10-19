package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by knomo on 10/18/15.
 */
public class Animation extends Pane {

    private final AnimationTimer timer;
    private final Canvas canvas;
    private final List<Particle> particles = new ArrayList<Particle>();
    private final Paint[] colors;

    private int countDownTillNextFirework = 40;
    private final ImageView background;

    public Animation() {
        colors = new Paint[181];
        colors[0] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.WHITE),
                new Stop(0.2, Color.hsb(59, 0.38, 1)),
                new Stop(0.6, Color.hsb(59, 0.38, 1,0.1)),
                new Stop(1, Color.hsb(59, 0.38, 1,0))
        );
        for (int h=0;h<360;h+=2) {
            colors[1+(h/2)] = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.WHITE),
                    new Stop(0.2, Color.hsb(h, 1, 1)),
                    new Stop(0.6, Color.hsb(h, 1, 1,0.1)),
                    new Stop(1, Color.hsb(h, 1, 1,0))
            );
        }

        canvas = new Canvas(640, 401);
        canvas.setBlendMode(BlendMode.ADD);
        background = new ImageView("plaza.jpg");
        getChildren().addAll(background, canvas);

        timer = new AnimationTimer() {

            @Override
            public void handle(long now) {
                GraphicsContext gc = canvas.getGraphicsContext2D();
                gc.setFill(Color.rgb(0, 0, 0, 0.2)); // clear area with transparent black
                gc.fillRect(0, 0, 1024, 708);

                drawFireworks(gc);  // draw fireworks

                if(countDownTillNextFirework == 0) {
                    countDownTillNextFirework = 10 + (int) (Math.random() * 30);
                    fireParticle();
                }
                countDownTillNextFirework --;
            }
        };
    }

    private void drawFireworks(GraphicsContext gc) {
        Iterator<Particle> iter = particles.iterator();
        List<Particle> newParticles = new ArrayList<Particle>();
        while (iter.hasNext()) {
            Particle firework = iter.next();
            if (firework.update()) {
                iter.remove();

                if(firework.shouldExplodeChildren) {
                    if(firework.radius == 9) {
                        explodeCircle(firework, newParticles);
                    }
                }
            }
            firework.draw(gc);
        }

        particles.addAll(newParticles);
    }


    private void explodeCircle(Particle firework, List<Particle> newParticles) {
        final int count = 20 + (int)(60*Math.random());
        final boolean shouldExplodeChildren = Math.random() > 0.5;
        final double angle = (Math.PI * 2) / count;
        final int color = (int)(Math.random()*colors.length);

        for(int i=count; i>0; i--) {
            double randomVelocity = 4 + Math.random() * 4;
            double particleAngle = i * angle;
            newParticles.add(
                    new Particle(
                            firework.posX, firework.posY,
                            Math.cos(particleAngle) * randomVelocity, Math.sin(particleAngle) * randomVelocity,
                            0, 0,
                            colors[color],
                            8,
                            true, shouldExplodeChildren));
        }
    }

    private void fireParticle() {
        particles.add(new Particle(
                canvas.getWidth() * 0.5, canvas.getHeight() + 10,
                Math.random() * 5 - 2.5, 0,
                0, 150 + Math.random() * 100,
                colors[0], 9,
                false, true));
    }

    @Override
    protected void layoutChildren() {
        final double w = getWidth();
        final double h = getHeight();
        final double scale = Math.min(w / 1024d, h / 708d);
        final int width = (int) (1024 * scale);
        final int height = (int) (708 * scale);
        final int x = (int) ((w - width) / 2);
        final int y = (int) ((h - height) / 2);
        background.relocate(x, y);
        background.setFitWidth(width);
        background.setFitHeight(height);
        canvas.relocate(x, y);
        canvas.setWidth(width);
        canvas.setHeight(height * 0.706);
    }


    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
