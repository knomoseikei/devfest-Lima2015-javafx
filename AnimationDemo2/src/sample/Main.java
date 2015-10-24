package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private static final int CLOUD_LOOP_SECONDS = 40_000;//40_000;

    private ParallelTransition parallelTransition;

    private void init(Stage stage) {
        System.out.println("Howl's Moving Castle Animation");

        Pane gRoot = new Pane();
        gRoot.setStyle("-fx-background-color: #2294b3;");

        Pane gCastle = new Pane();
        gCastle.setScaleX(0.5);

        stage.setResizable(true);
        stage.setScene(new Scene(gRoot, 600, 600));
        //stage.setScene(new Scene(gRoot, 1_000, 800));

        // BACKGROUND
        ImageView imgBackground = new MyImage("background.jpg", 0, 320, 600);
        gRoot.getChildren().add(imgBackground);
        // END OF BACKGROUND

        // CLOUDS
        ImageView imgCloud1 = new MyImage("cloud-bg.png", 0, 320, 600);
        gRoot.getChildren().add(imgCloud1);

        ImageView imgCloud2 = new MyImage("cloud-bg.png", 0, 320, 600);
        gRoot.getChildren().add(imgCloud2);

        anim_clouds(imgCloud1, imgCloud2);
        // END OF CLOUDS


        { // CASTLE

            {
                Pane gBRleg = new Pane();
                gBRleg.setLayoutX(400);
                gBRleg.setLayoutY(625);

                ImageView imgBRbottom = new MyImage("brbottom.png", 0, 0);
                gBRleg.getChildren().add(imgBRbottom);

                ImageView imgBRfoot = new MyImage("brfoot.png", -18, 82);
                gBRleg.getChildren().add(imgBRfoot);

                gCastle.getChildren().add(gBRleg);

                movePivot(gBRleg, 10, -10);
                movePivotToPercent(imgBRfoot, 56, 44);
                anim_brleg(gBRleg);
                anim_brfoot(imgBRfoot);

            }

            {
                Pane gFRleg = new Pane();
                gFRleg.setLayoutX(240);
                gFRleg.setLayoutY(653);

                ImageView imgFRbottom = new MyImage("frbottom.png", 0, 0);
                gFRleg.getChildren().add(imgFRbottom);

                ImageView imgFRfoot = new MyImage("frfoot.png", -18, 51);
                gFRleg.getChildren().add(imgFRfoot);

                gCastle.getChildren().add(gFRleg);
            }

            ImageView imgChimney3 = new MyImage("chimney3.png", 400, 30);
            gCastle.getChildren().add(imgChimney3);
            ImageView imgTreehouse = new MyImage("treehouse.png", 220, 10);
            System.out.println("Treehouse");
            //movePivotToPercent(imgTreehouse, 50, 150);
            gCastle.getChildren().add(imgTreehouse);

            {
                Pane gHousesGroup = new Pane();
                gHousesGroup.setLayoutX(305);
                gHousesGroup.setLayoutY(130);

                ImageView imgPoint6 = new MyImage("point6.png", 84, 19);
                gHousesGroup.getChildren().add(imgPoint6);
                ImageView imgPoint5 = new MyImage("point5.png", 70, -23);
                gHousesGroup.getChildren().add(imgPoint5);
                ImageView imgPoint4 = new MyImage("point4.png", 40, -17);
                gHousesGroup.getChildren().add(imgPoint4);
                ImageView imgHouses = new MyImage("houses.png", 0, 0);
                gHousesGroup.getChildren().add(imgHouses);

                //movePivot(gHousesGroup, -50, 300);
                anim_poing6(imgPoint6);
                anim_point5(imgPoint5);
                anim_point4(imgPoint4);
                anim_housesgroup(gHousesGroup);

                gCastle.getChildren().add(gHousesGroup);
            }
            ImageView imgChimney2 = new MyImage("chimney2.png", 430, 120);
            gCastle.getChildren().add(imgChimney2);
            ImageView imgChimney1 = new MyImage("chimney1.png", 420, 90);
            gCastle.getChildren().add(imgChimney1);
            ImageView imgWing = new MyImage("wing.png", 420, 370);
            gCastle.getChildren().add(imgWing);

            anim_chimney(imgChimney3, 1100);
            anim_chimney(imgChimney2, 500);
            anim_chimney(imgChimney1, 0);
            anim_treehouse(imgTreehouse);

    //.wing { left: 420px; top: 370px; transform-origin: 0% 50%; transform: rotateZ(0deg); }
            //movePivotToPercent(imgWing, 0, 0);
            anim_wing(imgWing);


            {
                Pane gMoundGroup = new Pane();
                gMoundGroup.setLayoutX(115);
                gMoundGroup.setLayoutY(110);

                ImageView imgAntenna = new MyImage("antenna.png", -100, 90);
                gMoundGroup.getChildren().add(imgAntenna);
                ImageView imgPoint3 = new MyImage("point3.png", 125, -13);
                gMoundGroup.getChildren().add(imgPoint3);
                ImageView imgPoint2 = new MyImage("point2.png", 50, -22);
                gMoundGroup.getChildren().add(imgPoint2);
                ImageView imgPoint1 = new MyImage("point1.png", 4, 55);
                gMoundGroup.getChildren().add(imgPoint1);
                ImageView imgMound = new MyImage("mound.png", 0, 0);
                gMoundGroup.getChildren().add(imgMound);

                movePivotToPercent(imgAntenna, 100, 65);
                anim_antenna(imgAntenna);

                movePivot(gMoundGroup, 110, 220);
                anim_mountgroup(gMoundGroup);

                gCastle.getChildren().add(gMoundGroup);
            }

            ImageView imgWind = new MyImage("wind.png", 400, 290);
            gCastle.getChildren().add(imgWind);
            ImageView imgCannon = new MyImage("cannon.png", 30, 460);
            gCastle.getChildren().add(imgCannon);
            ImageView imgMain = new MyImage("main.png", 80, 230);
            gCastle.getChildren().add(imgMain);

            movePivotToPercent(imgWind, 0, 90);
            anim_wind(imgWind);
            anim_cannon(imgCannon);

            {
                Pane gBLleg = new Pane();
                gBLleg.setLayoutX(410);
                gBLleg.setLayoutY(615);

                {
                    Pane gBLGroup = new Pane();
                    gBLGroup.setLayoutX(0);
                    gBLGroup.setLayoutY(60);

                    ImageView imgBLfoot = new MyImage("flfoot.png", -19, 68);
                    gBLGroup.getChildren().add(imgBLfoot);
                    ImageView imgBLbottom = new MyImage("flbottom.png", 0 , 0);
                    gBLGroup.getChildren().add(imgBLbottom);

                    gBLleg.getChildren().add(gBLGroup);
                }

                ImageView imgBLtop = new MyImage("fltop.png", 0, 0);
                gBLleg.getChildren().add(imgBLtop);

                gCastle.getChildren().add(gBLleg);
            }

            ImageView imgBLcover = new MyImage("blcover.png", 360, 573);
            gCastle.getChildren().add(imgBLcover);
            ImageView imgKnob = new MyImage("knob.png", 214, 524);
            gCastle.getChildren().add(imgKnob);
            ImageView imgTele = new MyImage("tele.png", 90, 430);
            gCastle.getChildren().add(imgTele);
            ImageView imgTelecover = new MyImage("telecover.png", 161, 399);
            gCastle.getChildren().add(imgTelecover);

            anim_tele(imgTele);
            anim_knob(imgKnob);
            
            {
                Pane gFLleg = new Pane();
                gFLleg.setLayoutX(250);gFLleg.setLayoutY(615);
                //movePivot(gFLleg, 10, 15);
                movePivotToPercent(gFLleg, 100,100);

                {
                    Pane gFLBottomGroup = new Pane();
                    gFLBottomGroup.setLayoutX(0);gFLBottomGroup.setLayoutY(60);
                    movePivot(gFLBottomGroup, 0, -21);

                    ImageView imgFLfoot = new MyImage("flfoot.png", -19, 68);
                    movePivotToPercent(imgFLfoot, 56, 44);
                    gFLBottomGroup.getChildren().add(imgFLfoot);
                    ImageView imgFLbottom = new MyImage("flbottom.png", 0 , 0);
                    gFLBottomGroup.getChildren().add(imgFLbottom);

                    gFLleg.getChildren().add(gFLBottomGroup);

                    anim_flbottomgroup(gFLBottomGroup);
                    anim_flfoot(imgFLfoot);
                }

                ImageView imgFLtop = new MyImage("fltop.png", 0, 0);
                gFLleg.getChildren().add(imgFLtop);

                gCastle.getChildren().add(gFLleg);
                anim_flleg(gFLleg);
            }

            ImageView imgFLcover = new MyImage("flcover.png", 360, 573);
            gCastle.getChildren().add(imgFLcover);

        }

        gCastle.setScaleX(0.4);
        gCastle.setScaleY(0.4);

        gRoot.getChildren().add(gCastle);
        anim_castle(gCastle);

        // FOREGROUND
        ImageView imgForeground = new MyImage("foreground.png", 0,401, 600);
        gRoot.getChildren().add(imgForeground);


        //gCastle.setTranslateY(-100);
        //movePivot(gCastle, );

    }

    private void anim_knob(ImageView imgKnob) {
        System.out.println("imgKnob");
        movePivotToPercent(imgKnob, 30, 63);
        RotateTransition a1 = new RotateTransition(Duration.millis(200));
        a1.setFromAngle(-20);a1.setToAngle(50);
        RotateTransition a2 = new RotateTransition(Duration.millis(200));
        a2.setToAngle(-20); a2.setDelay(Duration.millis(300));
        RotateTransition a3 = new RotateTransition(Duration.millis(200));
        a3.setToAngle(45); a3.setDelay(Duration.millis(700));
        RotateTransition a4 = new RotateTransition(Duration.millis(200));
        a4.setToAngle(-25); a4.setDelay(Duration.millis(1000));
        RotateTransition a5 = new RotateTransition(Duration.millis(200));
        a5.setToAngle(30); a5.setDelay(Duration.millis(1500));
        RotateTransition a6 = new RotateTransition(Duration.millis(200));
        a6.setToAngle(0); a6.setDelay(Duration.millis(1900));
        RotateTransition a7 = new RotateTransition(Duration.millis(200));
        a7.setToAngle(-20); a7.setDelay(Duration.millis(2200));
        RotateTransition a8 = new RotateTransition(Duration.millis(300));
        a8.setToAngle(60); a8.setDelay(Duration.millis(2600));
        RotateTransition a9 = new RotateTransition(Duration.millis(200));
        a9.setToAngle(-10); a9.setDelay(Duration.millis(3000));
        RotateTransition a10 = new RotateTransition(Duration.millis(200));
        a10.setToAngle(40); a10.setDelay(Duration.millis(3400));
        RotateTransition a11 = new RotateTransition(Duration.millis(200));
        a11.setToAngle(-20); a11.setDelay(Duration.millis(3700));
        ParallelTransition anim = new ParallelTransition(imgKnob, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,
                a11);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(650));
        anim.play();
    }

    private void anim_treehouse(ImageView imgTreehouse) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(-5); a1.setToAngle(10);
        RotateTransition a2 = new RotateTransition(Duration.millis(1000));
        a2.setToAngle(-5); a2.setDelay(Duration.millis(1000));
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(10); a3.setDelay(Duration.millis(2000));
        RotateTransition a4 = new RotateTransition(Duration.millis(1000));
        a4.setToAngle(-5);
        a4.setDelay(Duration.millis(3000));

        TranslateTransition a5 = new TranslateTransition(Duration.ZERO);
        a5.setFromY(20); a5.setFromX(4);
        TranslateTransition a6 = new TranslateTransition(Duration.millis(400));
        a6.setToY(-5); a6.setToX(-2); a6.setDelay(Duration.millis(200));
        TranslateTransition a7 = new TranslateTransition(Duration.millis(3200));
        a7.setToY(20); a7.setToX(4); a7.setDelay(Duration.millis(800));

        ParallelTransition anim = new ParallelTransition(imgTreehouse, a1, a2, a3, a4, a5, a6, a7);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(1400));
        anim.play();
    }

    private void anim_point4(ImageView imgPoint4) {
        TranslateTransition a1 = new TranslateTransition(Duration.millis(300));
        a1.setFromY(0); a1.setFromX(0); a1.setToY(3);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(100));
        a2.setToY(-10); a2.setToX(4); a2.setDelay(Duration.millis(700));
        TranslateTransition a3 = new TranslateTransition(Duration.millis(700));
        a3.setToY(0); a3.setToX(0); a3.setDelay(Duration.millis(800));
        ParallelTransition anim = new ParallelTransition(imgPoint4, a1, a2, a3);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(450));
        anim.play();
    }

    private void anim_point5(ImageView imgPoint5) {
        TranslateTransition a1 = new TranslateTransition(Duration.millis(300));
        a1.setFromY(0); a1.setFromX(0); a1.setToY(3);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(100));
        a2.setToY(-7); a2.setToX(3); a2.setDelay(Duration.millis(500));
        TranslateTransition a3 = new TranslateTransition(Duration.millis(700));
        a3.setToY(0); a3.setToX(0); a3.setDelay(Duration.millis(600));
        ParallelTransition anim = new ParallelTransition(imgPoint5, a1, a2, a3);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(450));
        anim.play();
    }

    private void anim_poing6(ImageView imgPoint6) {
        TranslateTransition a1 = new TranslateTransition(Duration.millis(300));
        a1.setFromY(0); a1.setFromX(0); a1.setToY(3);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(100));
        a2.setToY(-10); a2.setToX(4); a2.setDelay(Duration.millis(300));
        TranslateTransition a3 = new TranslateTransition(Duration.millis(900));
        a3.setToY(0); a3.setToX(0); a3.setDelay(Duration.millis(400));
        ParallelTransition anim = new ParallelTransition(imgPoint6, a1, a2, a3);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(450));
        anim.play();
    }

    private void anim_tele(ImageView imgTele) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(0); a1.setToAngle(-3);
        RotateTransition a2 = new RotateTransition(Duration.millis(1000));
        a2.setToAngle(2); a2.setDelay(Duration.millis(1000));
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(-3); a3.setDelay(Duration.millis(2000));
        RotateTransition a4 = new RotateTransition(Duration.millis(1000));
        a4.setToAngle(0); a4.setDelay(Duration.millis(3000));

        TranslateTransition a5 = new TranslateTransition(Duration.ZERO);
        a5.setFromX(0); a5.setFromY(0);
        TranslateTransition a6 = new TranslateTransition(Duration.millis(250));
        a6.setToX(25); a6.setToY(4); a6.setDelay(Duration.millis(600));
        TranslateTransition a7 = new TranslateTransition(Duration.millis(2500));
        a7.setToX(0); a7.setToY(0); a7.setDelay(Duration.millis(900));

        ParallelTransition anim = new ParallelTransition(imgTele, a1, a2, a3, a4, a5, a6, a7);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(650));
        anim.play();
    }

    private void anim_cannon(ImageView imgCannon) {
        RotateTransition a1 = new RotateTransition(Duration.millis(900));
        a1.setToAngle(-7); a1.setDelay(Duration.millis(0));
        RotateTransition a2 = new RotateTransition(Duration.millis(900));
        a2.setToAngle(2); a2.setDelay(Duration.millis(900));
        RotateTransition a3 = new RotateTransition(Duration.millis(1100));
        a3.setToAngle(-5); a3.setDelay(Duration.millis(1800));
        RotateTransition a4 = new RotateTransition(Duration.millis(1100));
        a4.setToAngle(0); a4.setDelay(Duration.millis(2900));
//
//        TweenLite.to(_cannon, 0.25, {x: 30, y: 4, delay: 0.85, force3D: true}),
//        TweenLite.to(_cannon, 2.6, {x: 0, y: 0, delay: 1.4, force3D: true}),
        TranslateTransition a4b = new TranslateTransition(Duration.ZERO);
        a4b.setFromX(0); a4b.setFromY(0);
        TranslateTransition a5 = new TranslateTransition(Duration.millis(250));
        a5.setToX(30); a5.setToY(4); a5.setDelay(Duration.millis(850));
        TranslateTransition a6 = new TranslateTransition(Duration.millis(250));
        a6.setToX(0); a6.setToY(0); a6.setDelay(Duration.millis(1400));

        ParallelTransition anim = new ParallelTransition(imgCannon, a1, a2, a3, a4, a4b,a5, a6);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(650));
        anim.play();
    }

    private void anim_wind(ImageView imgWind) {

        RotateTransition a1 = new RotateTransition(Duration.millis(1100));
        a1.setFromAngle(-10); a1.setToAngle(5);
        RotateTransition a2 = new RotateTransition(Duration.millis(1000));
        a2.setToAngle(-15); a2.setDelay(Duration.millis(1100));
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(10); a3.setDelay(Duration.millis(2100));
        RotateTransition a4 = new RotateTransition(Duration.millis(900));
        a4.setToAngle(-10); a4.setDelay(Duration.millis(3100));

        ParallelTransition anim = new ParallelTransition(imgWind, a1, a2, a3, a4);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(650));
        anim.play();
    }

    private void anim_housesgroup(Pane gHousesGroup) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(2); a1.setToAngle(-1);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(1000));
        a2.setFromY(0);a2.setFromX(-4);a2.setToY(5);a2.setToX(0);
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(2); a3.setDelay(Duration.millis(1000));
        TranslateTransition a4 = new TranslateTransition(Duration.millis(1000));
        a4.setToY(0);a4.setToX(-4);a4.setDelay(Duration.millis(1000));

        ParallelTransition anim = new ParallelTransition(gHousesGroup, a1, a2, a3, a4);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(500));
        anim.play();
    }

    private void anim_chimney(ImageView imgChimney, double delay) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1500));
        a1.setFromAngle(-10); a1.setToAngle(5);
        RotateTransition a2 = new RotateTransition(Duration.millis(1500));
        a2.setToAngle(-10);a2.setDelay(Duration.millis(1500));
        TranslateTransition a2b = new TranslateTransition(Duration.ZERO);
        a2b.setFromX(0); a2b.setFromY(0);
        TranslateTransition a3 = new TranslateTransition(Duration.millis(500));
        a3.setToY(5); a3.setToX(0); a3.setDelay(Duration.millis(100));
        TranslateTransition a4 = new TranslateTransition(Duration.millis(100));
        a4.setToY(-15); a4.setToX(4); a4.setDelay(Duration.millis(600)); a4.setInterpolator(Interpolator.EASE_OUT);
        TranslateTransition a5 = new TranslateTransition(Duration.millis(900));
        a5.setToY(0); a5.setToX(0); a5.setDelay(Duration.millis(700));
        TranslateTransition a6 = new TranslateTransition(Duration.millis(500));
        a6.setToY(5); a6.setToX(0); a6.setDelay(Duration.millis(1600));
        TranslateTransition a7 = new TranslateTransition(Duration.millis(100));
        a7.setToY(-15); a7.setToX(4); a7.setDelay(Duration.millis(2100)); a7.setInterpolator(Interpolator.EASE_OUT);
        TranslateTransition a8 = new TranslateTransition(Duration.millis(500));
        a8.setToY(0); a8.setToX(0); a8.setDelay(Duration.millis(2200));

        ParallelTransition anim = new ParallelTransition(imgChimney, a1, a2,a2b,a3,a4,a5);//,a6,a7,a8);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(delay));
        anim.play();
    }

    private void anim_wing(ImageView imgWing) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(2); a1.setToAngle(-1);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(1000));
        a2.setFromX(0); a2.setToX(-5);
        //TweenLite.to(_wing, 1.0, {rotationZ: 2, x: 0, delay: 1.0, force3D: true}),
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(2); a3.setDelay(Duration.millis(1000));
        TranslateTransition a4 = new TranslateTransition(Duration.millis(1000));
        a4.setToX(0); a4.setDelay(Duration.millis(1000));

        ParallelTransition anim = new ParallelTransition(imgWing, a1, a2, a3, a4);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(800));
        anim.play();

    }

    private void anim_mountgroup(Pane gMoundGroup) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(2); a1.setToAngle(-1);
        RotateTransition a2 = new RotateTransition(Duration.millis(1000));
        a2.setToAngle(2); a2.setDelay(Duration.millis(1000));
        ParallelTransition anim = new ParallelTransition(gMoundGroup, a1, a2);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(200));
        anim.play();
    }

    private void anim_brfoot(ImageView imgBRfoot) {
        RotateTransition a1 = new RotateTransition(Duration.millis(900));
        a1.setFromAngle(-35); a1.setToAngle(35);
        RotateTransition a2 = new RotateTransition(Duration.millis(500));
        a2.setToAngle(-50); a2.setDelay(Duration.millis(900));
        RotateTransition a3 = new RotateTransition(Duration.millis(300));
        a3.setToAngle(-35); a3.setDelay(Duration.millis(1700));

        ParallelTransition anim = new ParallelTransition(imgBRfoot, a1, a2, a3);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(1700));
        anim.play();
    }

    private void anim_brleg(Pane gBRleg) {
        RotateTransition a1 = new RotateTransition(Duration.millis(900));
        a1.setFromAngle(35);a1.setToAngle(-35);
        TranslateTransition a2 = new TranslateTransition(Duration.millis(1200));
        a2.setFromX(-40); a2.setToX(40); a2.setInterpolator(Interpolator.EASE_OUT);
        TranslateTransition a3 = new TranslateTransition(Duration.millis(400));
        a3.setFromY(0); a3.setToY(-15); a3.setInterpolator(Interpolator.EASE_IN);
        TranslateTransition a4 = new TranslateTransition(Duration.millis(400));
        a4.setToY(0); a4.setDelay(Duration.millis(500)); a4.setInterpolator(Interpolator.EASE_OUT);
        RotateTransition a5 = new RotateTransition(Duration.millis(1100));
        a5.setToAngle(35); a5.setDelay(Duration.millis(900));
        TranslateTransition a6 = new TranslateTransition(Duration.millis(600));
        a6.setToX(-50); a6.setDelay(Duration.millis(1200));
        TranslateTransition a7 = new TranslateTransition(Duration.millis(600));
        a7.setToY(-40); a7.setDelay(Duration.millis(900));
        TranslateTransition a8 = new TranslateTransition(Duration.millis(500));
        a8.setToY(0); a8.setDelay(Duration.millis(1500));
        TranslateTransition a9 = new TranslateTransition(Duration.millis(200));
        a9.setToX(-40); a9.setDelay(Duration.millis(1800)); a9.setInterpolator(Interpolator.EASE_IN);
        a9.setToY(0);
//]);
        ParallelTransition anim = new ParallelTransition(gBRleg, a1, a2, a3, a4, a5, a6, a7, a8, a9);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.setDelay(Duration.millis(1700));
        anim.play();
    }

    private void movePivot(Node node, double x, double y){
        node.getTransforms().add(new Translate(-x,-y));
        node.setTranslateX(x); node.setTranslateY(y);
    }

    private void movePivotToPercent(Node node, double xPercent, double yPercent){
        double x = node.getBoundsInLocal().getWidth() * ((xPercent - 50.0) / 100.0);
        double y = node.getBoundsInLocal().getHeight() * ((yPercent - 50.0) / 100.0);
        System.out.println("moveToX: "+x);
        System.out.println("moveToY: "+y);
        movePivot(node, x, y);
    }

    private void anim_clouds(ImageView imgCloud1, ImageView imgCloud2) {
        TranslateTransition animCloud1 = new TranslateTransition(Duration.millis(CLOUD_LOOP_SECONDS), imgCloud1);
        animCloud1.setFromX(-600);
        animCloud1.setToX(600);
        animCloud1.setCycleCount(Animation.INDEFINITE);
        animCloud1.play();

        PauseTransition pause = new PauseTransition(Duration.millis(CLOUD_LOOP_SECONDS / 2));

        TranslateTransition animCloud2 = new TranslateTransition(Duration.millis(CLOUD_LOOP_SECONDS));
        animCloud2.setFromX(-600);
        animCloud2.setToX(600);
        animCloud2.setCycleCount(Animation.INDEFINITE);
        animCloud2.play();
        SequentialTransition seqT = new SequentialTransition (imgCloud2, pause, animCloud2);
        seqT.play();
    }

    private void anim_antenna(ImageView imgAntenna) {
        RotateTransition a1 = new RotateTransition(Duration.millis(1000));
        a1.setFromAngle(10); a1.setToAngle(-5);
        RotateTransition a2 = new RotateTransition(Duration.millis(1000));
        a2.setToAngle(10); a2.setDelay(Duration.millis(1000));
        RotateTransition a3 = new RotateTransition(Duration.millis(1000));
        a3.setToAngle(-10); a3.setDelay(Duration.millis(2000));
        RotateTransition a4 = new RotateTransition(Duration.millis(1000));
        a4.setToAngle(10); a4.setDelay(Duration.millis(3000));
        ParallelTransition af = new ParallelTransition(imgAntenna, a1, a2, a3, a4);
        af.setAutoReverse(true);
        af.setCycleCount(Animation.INDEFINITE);
        af.setDelay(Duration.millis(650));
        af.play();
    }



    private void anim_castle(Pane gCastle){
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(500));
        tt1.setByX(2); tt1.setByY(-4);
        TranslateTransition tt2 = new TranslateTransition(Duration.millis(500));
        tt2.setByX(-4); tt2.setByY(4);
        TranslateTransition tt3 = new TranslateTransition(Duration.millis(500));
        tt3.setByX(4); tt3.setByY(-5);
        TranslateTransition tt4 = new TranslateTransition(Duration.millis(500));
        tt4.setByX(-2); tt4.setByY(5);

        SequentialTransition st1 = new SequentialTransition(tt1, tt2, tt3, tt4);
        st1.setCycleCount(Animation.INDEFINITE);

        RotateTransition rt1 = new RotateTransition(Duration.millis(1000));
        rt1.setFromAngle(12); rt1.setToAngle(7);
        RotateTransition rt2 = new RotateTransition(Duration.millis(1000));
        rt2.setToAngle(12);

        SequentialTransition st2 = new SequentialTransition(rt1, rt2);
        st2.setCycleCount(Animation.INDEFINITE);

        TranslateTransition t1 = new TranslateTransition(Duration.millis(17_000));
        t1.setFromX(450); t1.setFromY(13);
        t1.setToX(-450); t1.setToY(-122);
        t1.setCycleCount(Animation.INDEFINITE);

        ParallelTransition pt1 = new ParallelTransition(gCastle, st1, st2, t1);
        pt1.play();

    }

    private void anim_flbottomgroup(Node gFLGroup) {
        ScaleTransition a1 = new ScaleTransition(Duration.millis(550));
        a1.setFromY(1);a1.setToY(0.8);
        RotateTransition a2 = new RotateTransition(Duration.millis(600));
        a2.setFromAngle(5);a2.setToAngle(20);
        ScaleTransition a3 = new ScaleTransition(Duration.millis(400));
        a3.setToY(0.7);a3.setDelay(Duration.millis(600));
        RotateTransition a4 = new RotateTransition(Duration.millis(400));
        a4.setToAngle(50);a4.setDelay(Duration.millis(600));
        ScaleTransition a5 = new ScaleTransition(Duration.millis(500));
        a5.setToY(0.5);a5.setDelay(Duration.millis(1000));
        RotateTransition a6 = new RotateTransition(Duration.millis(500));
        a6.setToAngle(40);a6.setDelay(Duration.millis(1000));
        ScaleTransition a7 = new ScaleTransition(Duration.millis(500));
        a7.setToY(1);a7.setDelay(Duration.millis(1500));
        RotateTransition a8 = new RotateTransition(Duration.millis(500));
        a8.setToAngle(5);a8.setDelay(Duration.millis(1500));


        ParallelTransition anim = new ParallelTransition(gFLGroup, a1, a2, a3, a4, a5, a6, a7, a8);
        //anim.setAutoReverse(true);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.play();
    }

    private void anim_flfoot(ImageView imgFLfoot) {
        ScaleTransition a1 = new ScaleTransition(Duration.millis(550));
        a1.setFromX(1);a1.setFromY(1);a1.setToY(1.5);
        RotateTransition a2 = new RotateTransition(Duration.millis(600));
        a2.setFromAngle(-50);a2.setToAngle(10); a2.setInterpolator(Interpolator.EASE_IN);
        RotateTransition a3 = new RotateTransition(Duration.millis(200));
        a3.setToAngle(10);a3.setDelay(Duration.millis(600)); a3.setInterpolator(Interpolator.LINEAR);
        RotateTransition a4 = new RotateTransition(Duration.millis(200));
        a4.setToAngle(-10);a4.setDelay(Duration.millis(800));a4.setInterpolator(Interpolator.EASE_OUT);
        ScaleTransition a5 = new ScaleTransition(Duration.millis(400));
        a5.setToY(1.5);a5.setDelay(Duration.millis(600));
        ScaleTransition a6 = new ScaleTransition(Duration.millis(500));
        a6.setToX(1.8); a6.setToY(1.7);a6.setDelay(Duration.millis(1000)); a6.setInterpolator(Interpolator.EASE_IN);
        RotateTransition a7 = new RotateTransition(Duration.millis(500));
        a7.setToAngle(-70);a7.setDelay(Duration.millis(1000));
        RotateTransition a8 = new RotateTransition(Duration.millis(500));
        a8.setToAngle(-50);a8.setDelay(Duration.millis(1500));
        ScaleTransition a9 = new ScaleTransition(Duration.millis(500));
        a9.setToX(1);a9.setToY(1); a9.setDelay(Duration.millis(1500));

        ParallelTransition anim = new ParallelTransition(imgFLfoot, a1, a2, a3 ,a4, a5, a6, a7, a8, a9);
        //anim.setAutoReverse(true);
        anim.setCycleCount(Animation.INDEFINITE);
        anim.play();
    }

    private void anim_flleg(Node node){
            RotateTransition a1 = new RotateTransition(Duration.millis(1000));
            a1.setFromAngle(45);a1.setToAngle(-45); a1.setInterpolator(Interpolator.EASE_OUT);
            TranslateTransition a2 = new TranslateTransition(Duration.millis(200));
            a2.setFromX(-5);a2.setToX(0);
            ScaleTransition a3 = new ScaleTransition(Duration.millis(550));
            a3.setToY(0.8);
            ScaleTransition a4 = new ScaleTransition(Duration.millis(400));
            a4.setToY(1); a4.setDelay(Duration.millis(600));
            TranslateTransition a5 = new TranslateTransition(Duration.millis(600));
            a5.setToX(20);a5.setDelay(Duration.millis(700));
            RotateTransition a6 = new RotateTransition(Duration.millis(500));
            a6.setToAngle(0);a6.setDelay(Duration.millis(1000)); a6.setInterpolator(Interpolator.EASE_IN);
            ScaleTransition a7 = new ScaleTransition(Duration.millis(500));
            a7.setToY(0.8);a7.setDelay(Duration.millis(1000));
            RotateTransition a8 = new RotateTransition(Duration.millis(500));
            a8.setToAngle(45);a8.setDelay(Duration.millis(1500)); a8.setInterpolator(Interpolator.EASE_OUT);
            ScaleTransition a9 = new ScaleTransition(Duration.millis(500));
            a9.setToY(1);a9.setDelay(Duration.millis(1500));
            TranslateTransition a10 = new TranslateTransition(Duration.millis(500));
            a10.setToX(-10);a10.setDelay(Duration.millis(1300));
            TranslateTransition a11 = new TranslateTransition(Duration.millis(200));
            a11.setToX(-5);a11.setDelay(Duration.millis(1800)); a11.setInterpolator(Interpolator.EASE_IN);

            ParallelTransition pt1 = new ParallelTransition(node, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11);
            //pt1.setAutoReverse(true);
            pt1.setCycleCount(Animation.INDEFINITE);
            pt1.play();
    }

    public void play() {
        parallelTransition.play();
    }

    @Override public void stop() {
        parallelTransition.stop();
    }

    public double getSampleWidth() { return 400; }

    public double getSampleHeight() { return 150; }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
        //play();
    }
    public static void main(String[] args) { launch(args); }
}
