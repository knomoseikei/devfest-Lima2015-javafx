package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;

public class Main extends Application {

    private final Animation chispitas = new Animation();

    public void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Workshop GDG Lima 2015: Partículas");
        root.getChildren().add(chispitas);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        init(primaryStage);
        primaryStage.show();
        play();
    }

    private void play() {
        chispitas.start();
    }

    @Override
    public void stop() throws Exception {
        chispitas.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
