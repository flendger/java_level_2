package lesson1;

import java.util.Random;

public class Wall extends Obstacle{
    private int height;

    public Wall() {
        super(ObstacleTypes.WALL);
        this.height = new Random().nextInt(5)+1;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "height=" + height +
                '}';
    }
}
