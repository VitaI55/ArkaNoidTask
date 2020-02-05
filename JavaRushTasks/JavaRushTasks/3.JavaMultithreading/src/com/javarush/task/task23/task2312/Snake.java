package com.javarush.task.task23.task2312;

import java.util.ArrayList;

/**
 * Snake class
 */
public class Snake {
    // Snake direction
    private SnakeDirection direction;
    // State - whether the snake alive or not
    private boolean isAlive;
    // List of snake pieces
    private ArrayList<SnakeSection> sections;

    public Snake(int x, int y) {
        sections = new ArrayList<SnakeSection>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getX() {
        return sections.get(0).getX();
    }

    public int getY() {
        return sections.get(0).getY();
    }

    public SnakeDirection getDirection() {
        return direction;
    }

    public void setDirection(SnakeDirection direction) {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections() {
        return sections;
    }

    /**
     *  This Method moves the snake for one move.
     * The direction of movement is set by the direction variable.
     */
    public void move() {
        if (!isAlive) return;
        else {
            switch (direction) {
                case UP:
                    move(0, -1);
                case DOWN:
                    move(0, 1);
                case LEFT:
                    move(-1, 0);
                case RIGHT:
                    move(1, 0);
            }
        }

    }

    /**
     *
     * The method moves the snake into an adjacent cage.
     * The cell coordinates are set relative to the current head using variables (dx, dy).
     */
    private void move(int dx, int dy) {
        // Create a new head - " a piece of snake".
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        // Check whether the head crawled out of the room
        checkBorders(head);
        if (!isAlive) return;

        // Checking if the snake crossing itself
        checkBody(head);
        if (!isAlive) return;

        // Checking if the snake has eaten the mouse
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) // mouse was eaten
        {
            sections.add(0, head);                  // Add new head
            Room.game.eatMouse();                   // Don't remove the tail but create a new mouse
        } else // just moving
        {
            sections.add(0, head);                  // Added new head
            sections.remove(sections.size() - 1);   // removed the last element from the tail
        }
    }

    /**
     * Method checking - if the new head is within the room
     */
    private void checkBorders(SnakeSection head) {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight()) {
            isAlive = false;
        }
    }

    /**
     * The method checks whether the head coincides with any part of the body of the snake
     */
    private void checkBody(SnakeSection head) {
        if (sections.contains(head)) {
            isAlive = false;
        }
    }
}
