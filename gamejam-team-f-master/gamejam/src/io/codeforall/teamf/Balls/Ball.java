package io.codeforall.teamf.Balls;

import io.codeforall.teamf.Background;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Ball {

    private Background background;
    private Picture picture;
    private BallType ballType;
    private double hSpeed;
    private double vSpeed;
    private int randBallInitHeight = 20;


    public Ball(Background background, BallType ballType, int x, int y, boolean startsRight, boolean startsDown) {
        this.background = background;
        this.ballType = ballType;
        picture = new Picture(x, y, ballType.getPicOrigin());
        picture.draw();
        hSpeed = startsRight ? ballType.getSpeed() : -ballType.getSpeed();
        vSpeed = startsDown ? ballType.getSpeed() : -ballType.getSpeed();
    }

    public Ball(Background background, BallType ballType) {
        this.background = background;
        this.ballType = ballType;
        picture = new Picture(background.getX(), background.getY() + randBallInitHeight*2, "gamejam/resources/big-ball.png");
        picture.draw();
        randomizeX(ballType.getSpeed());
        vSpeed = ballType.getSpeed();
    }

    private void chooseHorizontal(double speed, boolean startsRight) {
        hSpeed = startsRight ? speed : -speed;
    }

    private void randomizeX(double speed) {
        picture.translate(Math.random() * (background.getMaxX() - picture.getWidth()), 0);
        if (Math.random() < 0.5) {
            hSpeed = speed;
        } else {
            hSpeed = -speed;
        }
    }

    public void move() {

        /*
        this.picture.translate(hSpeed, vSpeed);

        if (picture.getX() < background.getX() || picture.getMaxX() > background.getMaxX()) {
            hSpeed = -hSpeed;
        }

        if (picture.getY() < background.getY() || picture.getMaxY() > background.getMaxY()) {
            vSpeed = -vSpeed;
        }
        */

        picture.translate(hSpeed, vSpeed);

        vSpeed += 0.004; // Adjust this value for the desired trajectory shape

        if (picture.getY() < randBallInitHeight) {
            vSpeed *= 0.8;
        }
        if (picture.getY() < background.getY() || picture.getMaxY() > background.getMaxY()) {
            vSpeed = -vSpeed;
        }
        if (picture.getX() < background.getX() || picture.getMaxX() > background.getMaxX()) {
            hSpeed = -hSpeed;
        }

    }

    public void delete() {
        picture.delete();
    }

    public int getX() {
        return picture.getX();
    }

    public int getMaxX() {
        return picture.getMaxX();
    }

    public int getY() {
        return picture.getY();
    }

    public int getMaxY() {
        return picture.getMaxY();
    }

    public BallType getBallType() {
        return ballType;
    }
}