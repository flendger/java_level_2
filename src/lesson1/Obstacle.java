package lesson1;

public abstract class Obstacle {
    private ObstacleTypes obstacleType;

    public Obstacle(ObstacleTypes obstacleType) {
        this.obstacleType = obstacleType;
    }

    public ObstacleTypes getObstacleType() {
        return obstacleType;
    }
}
