package at.grabher.snake;


import at.grabher.snake.actors.Border;
import at.grabher.snake.actors.Element;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends BasicGame {
    //instance
    public static final int GRID_SIZE = 40;
    public static final int CLOCK = 500; //ms
    private List<Element> actors;
    private List<Border> borders;
    private Element tail, head, elementDefault, element;
    private int elapsedTime = 0; //ms

    Border border = new Border();

    //constructor
    public SnakeGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


        this.actors = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            elementDefault = new Element (i + 3, 3);
            this.actors.add(0 + i, elementDefault);
        }

        this.tail = this.actors.get(0);
        this.head = this.actors.get(this.actors.size() - 1);

        for (int i = 0; i < this.actors.size() - 1; i++) {
            this.actors.get(i).setNext(this.actors.get(i + 1));
        }

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


            System.out.println(this.actors.size());

            if (this.head.getCollisionShape().intersects(this.border.getCollisionBorderWest()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderEast()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderNorth()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderSouth())) {
                System.out.println("collision border ...");
            }

            if (this.head.getCollisionShape().intersects(this.element.getCollisionShape())) {
                System.out.println("collision");

                for (int i = 0; i < this.actors.size() - 1; i++) {
                    this.actors.get(i).setNext(this.actors.get(i + 1));
                }

                this.tail = this.actors.get(0);
                this.head = this.actors.get(this.actors.size() - 1);

                Random random = new Random();
                Element element = new Element(random.nextInt(16) + 2, random.nextInt(11) + 2);
                this.actors.add(element);
                this.element = this.actors.get(this.actors.size() - 1);

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

            }   else {
                //movement
                Element tmp = this.tail;
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
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        border.render(gameContainer, graphics);
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
