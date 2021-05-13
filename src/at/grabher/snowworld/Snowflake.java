package at.grabher.snowworld;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.util.Random;
import org.newdawn.slick.GameContainer;

public class Snowflake implements Actor{
    private float x,y;
    private int speed;
    private int size;
    public enum SIZE {
        BIG,
        MEDIUM,
        SMALL}
    private Random random;


    public Snowflake(SIZE size) {
        if (size==SIZE.BIG) {
            this.size = 12;
            this.speed = 2;
        }
        if (size==SIZE.MEDIUM) {
            this.size = 8;
            this.speed = 5;
        }
        if (size==SIZE.SMALL) {
            this.size = 4;
            this.speed = 10;
        }

        this.random = new Random();
        setRandomPosition();
    }

    private void setRandomPosition() {
        this.x = this.random.nextInt(800);
        this.y = this.random.nextInt(600) - 600;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.y += (float) delta / this.speed;
        if (this.y > 600) {
            this.y = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.fillOval(this.x, this.y, this.size, this.size);
    }
}
