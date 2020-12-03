package lesson1;

import java.util.Random;

public class Human implements Competitor{
    private int maxLength;
    private int maxHeight;

    public Human() {
        this.maxLength = new Random().nextInt(10)+1;
        this.maxHeight = new Random().nextInt(3)+1;
    }

    @Override
    public boolean run(Obstacle obstacle) {
        if (maxLength >= ((Track) obstacle).getLength()) {
            System.out.printf("%s is running over: %s... Success!\n", this, obstacle);
            return true;
        } else {
            System.out.printf("%s is running over: %s... Fail!\n", this, obstacle);
            return false;
        }
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        if (maxHeight >= ((Wall) obstacle).getHeight()) {
            System.out.printf("%s is jumping over: %s... Success!\n", this, obstacle);
            return true;
        } else {
            System.out.printf("%s is jumping over: %s... Fail!\n", this, obstacle);
            return false;
        }
    }

    @Override
    public boolean overcome(Obstacle obstacle) {
        boolean res = false;
        switch (obstacle.getObstacleType()) {
            case WALL:
                res = jump(obstacle);
                break;
            case TRACK:
                res = run(obstacle);
                break;
        }
        return res;
    }

    @Override
    public String toString() {
        return "Human{" +
                "maxLength=" + maxLength +
                ", maxHeight=" + maxHeight +
                '}';
    }
}
