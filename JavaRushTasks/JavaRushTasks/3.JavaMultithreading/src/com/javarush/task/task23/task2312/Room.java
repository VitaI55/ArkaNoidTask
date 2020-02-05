package com.javarush.task.task23.task2312;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * The main class of the program
 */
public class Room {
    private int width;
    private int height;
    private Snake snake;
    private Mouse mouse;

    public Room(int width, int height, Snake snake) {
        this.width = width;
        this.height = height;
        this.snake = snake;
        game = this;
    }

    public Snake getSnake() {
        return snake;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setMouse(Mouse mouse) {
        this.mouse = mouse;
    }

    /**
     * The main cycle of the program
     * All important actions take place here
     */
    public void run() {
        // Create an object "keyboardObserver" and starts him
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        // While the snake is Alive
        while (snake.isAlive()) {
            // Check whether the "Observer" contain key press events
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();
                // If equal to the character 'q' - exit from the game
                if (event.getKeyChar() == 'q') return;

                // If the "Page left" - move the figure to the left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    snake.setDirection(SnakeDirection.LEFT);
                    // If the "Page right" - move the figure to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    snake.setDirection(SnakeDirection.RIGHT);
                    // If the "Page up" - move the figure up
                else if (event.getKeyCode() == KeyEvent.VK_UP)
                    snake.setDirection(SnakeDirection.UP);
                    // If the "Page down" - move the figure down
                else if (event.getKeyCode() == KeyEvent.VK_DOWN)
                    snake.setDirection(SnakeDirection.DOWN);
            }

            snake.move();   // moves the snake
            print();        // display the current state of the game
            sleep();        // pause between moves
        }

        // Displaying the message "Game Over"
        System.out.println("Game Over!");
    }

    /**
     * Displaying the current state of the game
     */
    public void print() {
        // Create an array where we will "draw" the current state of the game
        int[][] matrix = new int[height][width];

        // Draw all the snake pieces
        ArrayList<SnakeSection> sections = new ArrayList<SnakeSection>(snake.getSections());
        for (SnakeSection snakeSection : sections) {
            matrix[snakeSection.getY()][snakeSection.getX()] = 1;
        }

        // Draw the head of the snake (4 - if the snake is dead)
        matrix[snake.getY()][snake.getX()] = snake.isAlive() ? 2 : 4;

        // Draw the mouse
        matrix[mouse.getY()][mouse.getX()] = 3;

        //Displaying all this on the screen.
        String[] symbols = {" . ", " x ", " X ", "^_^", "RIP"};
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(symbols[matrix[y][x]]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    /**
     * Method calls when the mouse is eaten
     */
    public void eatMouse() {
        createMouse();
    }

    /**
     * Creates a new mouse
     */
    public void createMouse() {
        int x = (int) (Math.random() * width);
        int y = (int) (Math.random() * height);

        mouse = new Mouse(x, y);
    }


    public static Room game;

    public static void main(String[] args) {
        game = new Room(20, 20, new Snake(10, 10));
        game.snake.setDirection(SnakeDirection.DOWN);
        game.createMouse();
        game.run();
    }

    private int initialDelay = 520;
    private int delayStep = 20;

    /**
     * The program pauses, the length of which depends on the length of the snake
     */
    public void sleep() {
        try {
            int level = snake.getSections().size();
            int delay = level < 15 ? (initialDelay - delayStep * level) : 200;
            Thread.sleep(delay);
        } catch (InterruptedException e) {
        }
    }
}
