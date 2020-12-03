public class MatrixConverter {
    private final static int MATRIXSIZE = 4;

    public static int[][] convertToInt(String[][] matrix) throws MySizeArrayException, MyArrayDataException{
        if (matrix.length != MATRIXSIZE) {
            throw new MySizeArrayException(String.format("Incorrect matrix size: rows %d, required %d", matrix.length, MATRIXSIZE));
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != MATRIXSIZE) {
                throw new MySizeArrayException(String.format("Incorrect matrix size in line %d: length %d, required %d", i+1, matrix[i].length, MATRIXSIZE));
            }
        }

        int[][] intMatrix = new int[MATRIXSIZE][MATRIXSIZE];

        int i =0, j = 0;
        try {
            for (i = 0; i < matrix.length; i++) {
                for (j = 0; j < matrix[i].length; j++) {
                    intMatrix[i][j] = Integer.parseInt(matrix[i][j]);
                }
            }
        } catch (NumberFormatException e) {
            throw new MyArrayDataException(String.format("Incorrect integer in row %d, column %d: %s", i+1, j+1, matrix[i][j]));
        }

        return intMatrix;
    }
}
