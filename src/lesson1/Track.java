package lesson1;

import java.util.Random;

public class Track extends Obstacle{
    private int length;

    public Track() {
        super(ObstacleTypes.TRACK);
        this.length = new Random().nextInt(15)+1;
    }

    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Track{" +
                "length=" + length +
                '}';
    }
}
