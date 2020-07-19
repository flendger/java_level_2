import lesson1.*;

import java.util.Random;

public class Lesson1Demo {
    public static void main(String[] args) {
        Competitor[] competitors = generateCompetitors(5);

        Obstacle[] obstacles = generateObstacles(5);

        for (Competitor competitor : competitors
        ) {
            for (Obstacle obstacle : obstacles
            ) {
                if (! competitor.overcome(obstacle)){
                    break;
                }
            }
            System.out.println();
        }
    }

    public static Obstacle[] generateObstacles(int count) {
        Obstacle[] obstacles = new Obstacle[count];
        Random random = new Random();

        for (int i = 0; i < obstacles.length; i++) {
            switch (random.nextInt(2)) {
                case 0:
                    obstacles[i] = new Wall();
                    break;
                case 1:
                    obstacles[i] = new Track();
                    break;
            }
        }
        return obstacles;
    }

    public static Competitor[] generateCompetitors(int count) {
        Competitor[] competitors = new Competitor[count];
        Random random = new Random();

        for (int i = 0; i < competitors.length; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    competitors[i] = new Human();
                    break;
                case 1:
                    competitors[i] = new Cat();
                    break;
                case 2:
                    competitors[i] = new Robot();
                    break;
            }
        }
        return competitors;
    }
}
