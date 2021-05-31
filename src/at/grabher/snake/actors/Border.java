package at.grabher.snake.actors;

import at.grabher.snake.SnakeGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Border implements Actor{

    private Shape collisionBorderWest;
    private Shape collisionBorderEast;
    private Shape collisionBorderNorth;
    private Shape collisionBorderSouth;

    public Border() {
        this.collisionBorderWest = new Rectangle (1 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE ,13 * SnakeGame.GRID_SIZE);
        this.collisionBorderEast = new Rectangle (18 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE, 1 * SnakeGame.GRID_SIZE,13 * SnakeGame.GRID_SIZE);
        this.collisionBorderNorth = new Rectangle(1 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE, 18 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE);
        this.collisionBorderSouth = new Rectangle(1 * SnakeGame.GRID_SIZE,13 * SnakeGame.GRID_SIZE, 18 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE);
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) {
        graphics.setColor(new Color(0, 255, 0));
        //graphics.draw(this.collisionBorderWest);
        graphics.drawRect(2 * SnakeGame.GRID_SIZE - 1,1 * SnakeGame.GRID_SIZE - 1, 1,13 * SnakeGame.GRID_SIZE);
        graphics.setColor(new Color(0, 125, 125));
        graphics.drawRect(18 * SnakeGame.GRID_SIZE,1 * SnakeGame.GRID_SIZE - 1, 1,13 * SnakeGame.GRID_SIZE);
        //graphics.draw(this.collisionBorderEast);
        graphics.setColor(new Color(0, 0, 255));
        graphics.drawRect(1 * SnakeGame.GRID_SIZE,2 * SnakeGame.GRID_SIZE - 1, 18 * SnakeGame.GRID_SIZE,1);
        //graphics.draw(this.collisionBorderNorth);
        graphics.setColor(new Color(125, 0, 125));
        graphics.drawRect(1 * SnakeGame.GRID_SIZE,13 * SnakeGame.GRID_SIZE, 18 * SnakeGame.GRID_SIZE,1);
        //graphics.draw(this.collisionBorderSouth);
        graphics.setColor(Color.white);
    }

    public Shape getCollisionBorderWest() {
        return collisionBorderWest;
    }

    public Shape getCollisionBorderEast() {
        return collisionBorderEast;
    }

    public Shape getCollisionBorderNorth() {
        return collisionBorderNorth;
    }

    public Shape getCollisionBorderSouth() {
        return collisionBorderSouth;
    }
}
