package at.grabher.snowworld;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Snowworld extends BasicGame {
    //instance
    List<Actor> snowflakes;
    private Rocket rocket;

    public Snowworld(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.snowflakes = new ArrayList<>();

        Rocket rocket = new Rocket();
        this.rocket = rocket;
        this.snowflakes.add(rocket);

        for (int i = 0; i < 25; i++) {
            Snowflake snowflakeSmall = new Snowflake(Snowflake.SIZE.SMALL);
            this.snowflakes.add(snowflakeSmall);
            Snowflake snowflakeMedium = new Snowflake(Snowflake.SIZE.MEDIUM);
            this.snowflakes.add(snowflakeMedium);
            Snowflake snowflakeBig = new Snowflake(Snowflake.SIZE.BIG);
            this.snowflakes.add(snowflakeBig);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        for (Actor actor : this.snowflakes) {
            actor.update(gameContainer, delta);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (Actor actor : this.snowflakes) {
            actor.render(graphics);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_SPACE) {
            CannonBall cb = new CannonBall(this.rocket.getX(), this.rocket.getY());
            this.snowflakes.add(cb);

        }


    }




    public static void main(String[] argv) {

        try {
            AppGameContainer container = new AppGameContainer(new Snowworld("Let it snow ..."));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
