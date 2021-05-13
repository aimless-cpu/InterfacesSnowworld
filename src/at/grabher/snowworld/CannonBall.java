package at.grabher.snowworld;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class CannonBall implements Actor {
    private float x,y;

    public CannonBall(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        this.y--;

    }

    @Override
    public void render(Graphics graphics) {
        graphics.fillOval(this.x, this.y, 10, 10 );

    }
}
