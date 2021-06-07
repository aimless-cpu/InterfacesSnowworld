package at.grabher.snake.actors;

import at.grabher.snake.SnakeGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.Random;

public class Element implements Actor {
    private int x,y;
    private Element next;
    private Random random;
    private Shape collisionShape;
    private boolean isHead = false;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
        this.collisionShape = new Circle(this.x * SnakeGame.GRID_SIZE+20, this.y * SnakeGame.GRID_SIZE+20, SnakeGame.GRID_SIZE/2 - 5);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.collisionShape.setCenterX(this.x * SnakeGame.GRID_SIZE + 20);
        this.collisionShape.setCenterY(this.y * SnakeGame.GRID_SIZE + 20);
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        if (isHead) {
            graphics.setColor(new Color(0, 125, 0));
            graphics.fillOval(this.x * SnakeGame.GRID_SIZE, this.y * SnakeGame.GRID_SIZE, SnakeGame.GRID_SIZE - 1, SnakeGame.GRID_SIZE -1);
        } else {
            graphics.fillOval(this.x * SnakeGame.GRID_SIZE, this.y * SnakeGame.GRID_SIZE, SnakeGame.GRID_SIZE - 1, SnakeGame.GRID_SIZE -1);
        }





        graphics.setColor(new Color(125, 0, 0));
        graphics.draw(this.collisionShape);
        graphics.setColor(Color.white);

    }

    //getter setter
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public Shape getCollisionShape() {
        return collisionShape;
    }

    public void setCollisionShape(Shape collisionShape) {
        this.collisionShape = collisionShape;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }
}
