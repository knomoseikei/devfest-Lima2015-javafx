package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by knomo on 10/12/15.
 */
public class MyImage extends ImageView {

    public MyImage(String url, double x, double y) {
        super(new Image(url));
        this.setX(x);
        this.setY(y);
        this.setPreserveRatio(true);
        this.setSmooth(true);
        this.setCache(true);
    }

    public MyImage(String url, double x, double y, double fitWidth) {
        this(url, x, y);
        this.setFitWidth(fitWidth);
    }

    public MyImage(String url){
        super(url);
    }

}
