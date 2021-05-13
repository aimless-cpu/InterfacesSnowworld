package at.grabher.snowworld;

import org.newdawn.slick.*;
import org.newdawn.slick.GameContainer;

public class Rocket implements Actor{
    private Image myRocket;
    private float x,y;


    public Rocket() throws SlickException {
        Image tmp = new Image("testdata/myRocket.png");
        this.myRocket = tmp.getScaledCopy(50, 50);
        this.myRocket.rotate(-45);
        this.x = 100;
        this.y = 100;
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) {
            this.x++;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) {
            this.x--;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_UP)) {
            this.y--;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)) {
            this.y++;
        }

        //test  for git
    }

    @Override
    public void render(Graphics graphics) {
        myRocket.draw(this.x, this.y);


    }

    public float getX() {
        return x + 20;
    }

    public float getY() {
        return y;
    }
}
