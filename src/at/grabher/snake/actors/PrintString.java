package at.grabher.snake.actors;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class PrintString implements Actor {
    private float x,y;
    private String string;

    public PrintString(int x, int y, String string) {
        this.x = x;
        this.y = y;
        this.string = string;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        PrintString collisionSelf = new PrintString(400, 280, "GAME OVER !");
        graphics.drawString(collisionSelf);
    }
}
