package at.grabher.snake;


import at.grabher.snake.actors.Element;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;
import java.util.List;

public class SnakeGame extends BasicGame {
    //instance
    public static final int GRID_SIZE = 40;
    public static final int CLOCK = 500; //ms
    private List<Element> actors;
    private Element tail, head, test, element;
    private int elapsedTime = 0; //ms
    private Shape collisionShape;


    private Shape collisionEat;

    //constructor
    public SnakeGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.actors = new ArrayList<>();

        Element element0 = new Element(3, 3);
        Element element1 = new Element(4, 3);
        Element element2 = new Element(5, 3);

        Element element3 = new Element(6, 3, this.collisionEat);
        this.collisionEat = new Circle(element3.getX() * SnakeGame.GRID_SIZE+20, element3.getY() * SnakeGame.GRID_SIZE+20, SnakeGame.GRID_SIZE/2);




        element0.setNext(element1);
        element1.setNext(element2);
        element2.setNext(element3);

        this.tail = element0;
        this.head = element3;
        this.test = element3;
        // gefressen dann merke position vom letzen element und warte einen schritt
//        int x = this.tail.getX();
//        int y = this.tail.getY()

        this.actors.add(element0);
        this.actors.add(element1);
        this.actors.add(element2);
        this.actors.add(element3);
        //this.collisionShape = new Circle(element3.getX() * SnakeGame.GRID_SIZE+20, element3.getY() * SnakeGame.GRID_SIZE+20, SnakeGame.GRID_SIZE/2);
//nächster versuch umgekehrt

        Element element = new Element(6, 10);
        this.element = element;
        this.actors.add(this.element);

    }

    int directionX = 1;
    int directionY = 0;

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {

        for (Element element : this.actors) {
            element.update(gameContainer, delta);
        }

        this.elapsedTime += delta;

        int newX = this.head.getX();
        int newY = this.head.getY();

        if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) {
            directionX = 1;
            directionY = 0;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) {
            directionX = -1;
            directionY = 0;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_UP)) {
            directionX = 0;
            directionY = -1;
        }
        if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)) {
            directionX = 0;
            directionY = 1;
        }

        if (this.elapsedTime > CLOCK) {

            if (this.head.getCollisionShape().intersects(this.element.getCollisionShape())) {
                System.out.println("collision");
                this.actors.remove(this.element);
                //this.element.setX(this.element.getX() + directionX);
                //this.element.setY(this.element.getY() + directionY);
            }

            //movement
            Element tmp  = this.tail;
            this.tail = tmp.getNext();
            tmp.setNext(null);
            head.setNext(tmp);

            newX = newX + directionX;
            newY = newY + directionY;

            tmp.setX(newX);
            tmp.getCollisionShape().setCenterX(newX * SnakeGame.GRID_SIZE + 20);

            tmp.setY(newY);
            tmp.getCollisionShape().setCenterY(newY * SnakeGame.GRID_SIZE + 20);

            this.head = tmp;

            this.elapsedTime = 0;


      }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        for (Element element : this.actors) {
            element.render(gameContainer, graphics);
        }
    }

    public static void main(String[] argv) {

        try {
            AppGameContainer container = new AppGameContainer(new SnakeGame("plain snake ..."));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
