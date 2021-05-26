package at.grabher.snowworld.actors;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.Random;

public class Snowflake implements Actor{
    private float x,y;
    private int speed;
    private int size;
    public enum SIZE {
        BIG,
        MEDIUM,
        SMALL}
    private Random random;
    private Shape collisionShape;

    //constructor
    public Snowflake(SIZE size) {
        if (size==SIZE.BIG) {
            this.size = 12;
            this.speed = 2;
            //this.collisionShape = new Circle(this.x, this.y, this.size);
        }
        if (size==SIZE.MEDIUM) {
            this.size = 8;
            this.speed = 5;
            //this.collisionShape = new Circle(this.x, this.y, this.size);
        }
        if (size==SIZE.SMALL) {
            this.size = 4;
            this.speed = 10;
            //this.collisionShape = new Circle(this.x, this.y, this.size);
        }

        this.random = new Random();
        setRandomPosition();
    }

    private void setRandomPosition() {
        this.x = this.random.nextInt(800);
        this.y = this.random.nextInt(600) - 600;
        this.collisionShape = new Circle(this.x, this.y, this.size/2);
        this.collisionShape.setCenterX(this.x + 5);
        this.collisionShape.setCenterY(this.y + 5);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.y += (float) delta / this.speed;
        this.collisionShape.setCenterY(this.y + 5);
        if (this.y > 600) {
            this.y = 0;
            this.collisionShape.setCenterY(this.y + 5);
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.fillOval(this.x, this.y, this.size, this.size);
        graphics.setColor(new Color(255, 0, 0));
        graphics.draw(this.collisionShape);
        graphics.setColor(Color.white);
    }

    //getter setter

    public Shape getCollisionShape() {
        return collisionShape;
    }

}
