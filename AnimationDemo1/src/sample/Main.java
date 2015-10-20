package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private static final double ANIM_TIME = 4;

    public void init(Stage stage) {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 500, 300);
        stage.setScene(scene);

        ImageView imgRocket = new ImageView("rocket.png");
        imgRocket.setX(0);
        imgRocket.setY(200);
        imgRocket.setRotate(-45);

        RotateTransition a1 = new RotateTransition(Duration.seconds(ANIM_TIME), imgRocket);
        a1.setByAngle(180);

        TranslateTransition a2 = new TranslateTransition(Duration.seconds(ANIM_TIME), imgRocket);
        a2.setToX(400);

        TranslateTransition a3 = new TranslateTransition(Duration.seconds(ANIM_TIME/2), imgRocket);
        a3.setByY(-200);
        a3.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition a4 = new TranslateTransition(Duration.seconds(ANIM_TIME/2), imgRocket);
        a4.setByY(200);
        a4.setInterpolator(Interpolator.EASE_IN);

        SequentialTransition s1 = new SequentialTransition(a3, a4);

        Circle rect = new Circle(20, 20, 30);
        rect.setCenterX(200);
        rect.setCenterY(200);
        rect.setFill(Color.GREEN);

        FillTransition a5 = new FillTransition(Duration.seconds(ANIM_TIME), rect);
        a5.setToValue(Color.RED);
        a5.play();

        ParallelTransition parallelTransition = new ParallelTransition(a1, a2, s1, a5);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.setDelay(Duration.millis(1000));
        parallelTransition.setAutoReverse(true);
        parallelTransition.play();

        pane.getChildren().add(imgRocket);
        pane.getChildren().add(rect);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.setTitle("AnimationDemo1");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
