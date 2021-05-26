package at.grabher.snowworld.actors;

import org.newdawn.slick.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

public class Rocket implements Actor{
    private Image myRocket;
    private float x,y;
    private Shape collisionShape;
    private List<Shape> shapes;


    public Rocket() throws SlickException {
        Image tmp = new Image("testdata/myRocket.png");
        this.myRocket = tmp.getScaledCopy(50, 50);
        this.myRocket.rotate(-45);
        this.x = 100;
        this.y = 100;
        this.collisionShape = new Rectangle(this.x, this.y, 30, 60);
        this.shapes = new ArrayList<Shape>();
    }

    @Override
    public void update(GameContainer gameContainer, int delta) {
        for (Shape shape : shapes) {
          if (this.collisionShape.intersects(shape)){
              System.out.println("Collision");
          }

        }

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

        this.collisionShape.setCenterX(this.x + 25);
        this.collisionShape.setCenterY(this.y + 25);

    }

    @Override
    public void render(Graphics graphics) {
        myRocket.draw(this.x, this.y);
        graphics.draw(this.collisionShape);


    }

    public void addCollisionPartner(Shape shape) {
        this.shapes.add(shape);

    }

    public float getX() {
        return x + 20;
    }

    public float getY() {
        return y;
    }
}
