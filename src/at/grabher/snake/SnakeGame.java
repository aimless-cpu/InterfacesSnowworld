package at.grabher.snake;


import at.grabher.snake.actors.Border;
import at.grabher.snake.actors.Element;
import at.grabher.snake.actors.PrintString;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends BasicGame {
    //instance
    public static final int GRID_SIZE = 40;
    public static final int CLOCK = 500; //ms
    private List<Element> actors;
    private Element tail, head, elementDefault, element;
    private Shape headCollision;
    private int elapsedTime = 0; //ms
    private boolean collisionSelf = false;
    private boolean collisionBorder = false;
    private Element mouth;


    Border border = new Border();
    PrintString printString = new PrintString();



    //constructor
    public SnakeGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {


        this.actors = new LinkedList<>();

        for (int i = 0; i < 3; i++) {
            elementDefault = new Element(i + 3, 3);
            this.actors.add(0 + i, elementDefault);
        }

        this.tail = this.actors.get(0);
        this.head = this.actors.get(this.actors.size() - 1);
        //this.head.setHead(true);

        mouth = new Element(this.head.getX() * GRID_SIZE, this.head.getY() * GRID_SIZE);
        mouth.setHead(true);

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

        gameContainer.getInput().disableKeyRepeat();
        if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) {
            if (directionX == -1) {
            } else {
                directionX = 1;
                directionY = 0;
            }
        }

        gameContainer.getInput().disableKeyRepeat();
        if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) {
            if (directionX == 1) {
            } else {
                directionX = -1;
                directionY = 0;
            }
        }

        gameContainer.getInput().disableKeyRepeat();
        if (gameContainer.getInput().isKeyDown(Input.KEY_UP)) {
            if (directionY == -1) {
            } else {
                directionX = 0;
                directionY = -1;
            }
        }

        gameContainer.getInput().disableKeyRepeat();
        if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)) {
            if (directionY == -1) {
            } else {
                directionX = 0;
                directionY = 1;
            }
        }

        if (this.elapsedTime > CLOCK) {

            System.out.println(this.actors.size());

            if (this.head.getCollisionShape().intersects(this.border.getCollisionBorderWest()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderEast()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderNorth()) ||
                    this.head.getCollisionShape().intersects(this.border.getCollisionBorderSouth())) {
                System.out.println("collision border ...");
                printString.setString("You collided with the border!");
                collisionBorder = true;
            }

            this.headCollision = new Circle((this.head.getX() + directionX) * SnakeGame.GRID_SIZE+20, (this.head.getY() + directionY) * SnakeGame.GRID_SIZE+20, SnakeGame.GRID_SIZE/2);

            for (int i = 0; i < this.actors.size() - 2; i++) {
                if (this.headCollision.intersects(this.actors.get(i).getCollisionShape())) {
                    System.out.println("collision self");
                    printString.setString("You collided with yourself!");
                    collisionSelf = true;
                }
            }

            if (this.headCollision.intersects(this.element.getCollisionShape())) {
                System.out.println("collision");

                try
                {
                    Thread.sleep(CLOCK - 100);
                }
                catch(InterruptedException ex)
                {
                    Thread.currentThread().interrupt();
                }

                this.actors.remove(this.element);
                Random random = new Random();

                int randomX, randomY;
                randomX = random.nextInt(16) + 2;
                randomY = random.nextInt(11) + 2;

                for (int i = 0; i < this.actors.size() - 1; i++) {
                    if (randomX != this.actors.get(i).getX() || randomY != this.actors.get(i).getY()) {
                        Element element = new Element(randomX, randomY);
                        this.actors.add(element);
                        this.element = element;
                        break;
                    }
                }

                int newX = this.head.getX() + directionX;
                int newY = this.head.getY() + directionY;

                Element tmp = new Element(newX, newY);
                this.actors.add(tmp);
                this.head.setNext(tmp);
                this.head = tmp;

            } else {
                moveForward(0);
            }
        }
    }

    private void moveForward(int time) {
        Element tmp = this.tail;
        this.tail = tmp.getNext();
        tmp.setNext(null);
        this.head.setNext(tmp);

        int newX = this.head.getX() + directionX;
        int newY = this.head.getY() + directionY;

        tmp.setX(newX);
        tmp.setY(newY);
        this.head = tmp;
        this.elapsedTime = time;
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {

        border.render(gameContainer, graphics);

        if (collisionSelf || collisionBorder) {
            printString.render(gameContainer, graphics);
        }

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
