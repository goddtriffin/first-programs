public class Arrays {

    public boolean isSymmetric (int[][] matrix) {
        if (matrix == null || !isSquare(matrix)) {
            return false;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                if (matrix[m][n] != matrix[n][m]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isDiagonal (int[][] matrix) {
        if (matrix == null) {
            return false;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                if (m != n && matrix[m][n] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isIdentity (int[][] matrix) {
        if (matrix == null || !isSquare(matrix)) {
            return false;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                if (m == n && matrix[m][n] != 1) {
                    return false;
                } else if (m != n && matrix[m][n] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isUpperTriangular (int[][] matrix) {
        if (matrix == null || !isSquare(matrix)) {
            return false;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                if (m > n && matrix[m][n] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isTridiagonal (int[][] matrix) {
        if (matrix == null || !isSquare(matrix)) {
            return false;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                if (matrix[m][n] != 0 && matrix[m][n] != 1) {
                    return false;
                } else {
                    if (matrix[m][n] == 0 && Math.abs(m-n) <= 1) {
                        return false;
                    }

                    if (matrix[m][n] == 1 && Math.abs(m-n) > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean isSquare (int[][] matrix) {
        if (matrix == null) {
            return false;
        }

        for (int i=0; i<matrix.length; i++) { //if not a square
            if (matrix.length != matrix[i].length) {
                return false;
            }
        }

        return true;
    }

    public void printMatrix (int[][] matrix) {
        if (matrix == null) {
            return;
        }

        for (int m=0; m<matrix.length; m++) {
            for (int n=0; n<matrix[m].length; n++) {
                System.out.print(matrix[m][n] +" ");
            }
            System.out.println();
        }
    }
}
