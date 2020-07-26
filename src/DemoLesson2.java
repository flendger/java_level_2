public class DemoLesson2 {
    public static void main(String[] args) {
        doDemoSizeException();
        doDemoDataException();
        doDemoCorrect();
    }

    public static void doDemo(String[][] strMatrix){
        try {
            int[][] intMatrix = MatrixConverter.convertToInt(strMatrix);
            int sum = 0;
            for (int i = 0; i < intMatrix.length; i++) {
                for (int j = 0; j < intMatrix[i].length; j++) {
                    sum += intMatrix[i][j];
                }
            }

            System.out.println("Sum: " + sum);
        } catch (MySizeArrayException | MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    public static void doDemoSizeException() {
        String[][] strMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11"},
                {"13", "14", "test", "16"}
        };

        doDemo(strMatrix);
    }

    public static void doDemoDataException() {
        String[][] strMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "test", "16"}
        };
        doDemo(strMatrix);
    }

    public static void doDemoCorrect() {
        String[][] strMatrix = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };
        doDemo(strMatrix);
    }

}
