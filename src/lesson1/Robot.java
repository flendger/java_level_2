package lesson1;

public class Robot implements Competitor{
    @Override
    public boolean run(Obstacle obstacle) {
        System.out.printf("%s is running over: %s... Success!\n", this, obstacle);
        return true;
    }

    @Override
    public boolean jump(Obstacle obstacle) {
        System.out.printf("%s is jumping over: %s... Success!\n", this, obstacle);
        return true;
    }

    @Override
    public boolean overcome(Obstacle obstacle) {
        switch (obstacle.getObstacleType()) {
            case WALL:
                jump(obstacle);
                break;
            case TRACK:
                run(obstacle);
                break;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Robot{}";
    }
}
