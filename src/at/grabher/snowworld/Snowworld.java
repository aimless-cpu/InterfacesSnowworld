package at.grabher.snowworld;

import at.grabher.snowworld.actors.Actor;
import at.grabher.snowworld.actors.CannonBall;
import at.grabher.snowworld.actors.Rocket;
import at.grabher.snowworld.actors.Snowflake;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

public class Snowworld extends BasicGame {
    //instance
    private List<Actor> actors;
    private Rocket rocket;

    public Snowworld(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.actors = new ArrayList<>(); //actors

        Rocket rocket = new Rocket();
        this.rocket = rocket;
        this.actors.add(rocket);

        for (int i = 0; i < 5; i++) {
            Snowflake snowflakeSmall = new Snowflake(Snowflake.SIZE.SMALL);
            this.actors.add(snowflakeSmall);
            this.rocket.addCollisionPartner(snowflakeSmall.getCollisionShape());
            //this.rocket.addCollisionPartner(snowflakeSmall);

           // Snowflake snowflakeMedium = new Snowflake(Snowflake.SIZE.MEDIUM);
           // this.actors.add(snowflakeMedium);
            //this.rocket.addCollisionPartner(snowflakeMedium);

           // Snowflake snowflakeBig = new Snowflake(Snowflake.SIZE.BIG);
           // this.actors.add(snowflakeBig);
            //this.rocket.addCollisionPartner(circle);
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        for (Actor actor : this.actors) {
            actor.update(gameContainer, delta);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (Actor actor : this.actors) {
            actor.render(graphics);
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_SPACE) {
            CannonBall cb = new CannonBall(this.rocket.getX(), this.rocket.getY());
            this.actors.add(cb);

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
