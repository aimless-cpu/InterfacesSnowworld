package at.grabher.snake.actors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class PrintString implements Actor {
    private float x = 400.0f;
    private float y = 230.0f;
    private String string;

    //private String collisionSelf  = "Collision with yourself!";

    public PrintString() {
        this.x = x;
        this.y = y;
        this.string = string;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        graphics.drawString(this.string, this.x, this.y);
    }

    public void setString(String string) {
        this.string = string;
    }
}
