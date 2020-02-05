package com.javarush.task.task24.task2413;

/**
 * Stand which helps us to reflect the ball
 */
public class Stand extends BaseObject {
    // the picture for rendering
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    // speed
    private double speed = 1;
    //direction (-1 to the left, +1 to the right)
    private double direction = 0;

    public Stand(double x, double y) {
        super(x, y, 3);
    }

    /**
     * The method moves the stand according to the current direction value
     */
    void move() {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, Arkanoid.game.getWidth() - radius + 1, 1, Arkanoid.game.getHeight() + 1);
    }

    /**
     * direction set equals to -1
     */
    void moveLeft() {
        direction = -1;
    }

    /**
     * direction set equal to +1
     */
    void moveRight() {
        direction = 1;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    /**
     * Draw ourselves on the canvas
     */
    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
