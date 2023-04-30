package utilities;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HelperFuncs {

    public static float calculateSlope(PointXY first, PointXY second)
    {
        return (float) (second.y - first.y) / (second.x - second.y);
    }

    public static double calculateSlope(int x1, int y1, int x2, int y2)
    {
        return (double) (y2 - y1)/ (x2 - x1);
    }

    public static double CalculateRad(int degree)
    {
        return Math.PI / 180 * degree;
    }

    public static ArrayList<PointXY> Translation(int dx, int dy, ArrayList<PointXY> points)
    {
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        int[][] matrix = {{1, 0, dx}, {0, 1, dy}, {0, 0, 1}};

        for (int i = 0; i < size; i++)
        {
            inputMatrix[0][i] = points.get(i).x;
            inputMatrix[1][i] = points.get(i).y;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new PointXY(result[0][i], result[1][i]));
        return resultList;
    }

    public static ArrayList<PointXY> Escalation(float sx, float sy, ArrayList<PointXY> points)
    {
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double[][] matrix = {{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++)
        {
            inputMatrix[0][i] = points.get(i).x;
            inputMatrix[1][i] = points.get(i).y;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new PointXY(result[0][i], result[1][i]));
        return resultList;
    }

    public static ArrayList<PointXY> Rotation(int theta, ArrayList<PointXY> points)
    {
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double radian = CalculateRad(theta);
        double[][] matrix = {{Math.cos(radian), - Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++)
        {
            inputMatrix[0][i] = points.get(i).x;
            inputMatrix[1][i] = points.get(i).y;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new PointXY(result[0][i], result[1][i]));
        return resultList;
    }

    public static ArrayList<PointXY> CenterRotation(int theta, ArrayList<PointXY> points, PointXY center)
    {
        ArrayList<PointXY> resultList = new ArrayList<PointXY>();
        int size = points.size();
        int[][] inputMatrix = new int[3][size];
        double radian = CalculateRad(theta);
        double[][] matrix = {{Math.cos(radian), - Math.sin(radian), 0}, {Math.sin(radian), Math.cos(radian), 0}, {0, 0, 1}};

        for (int i = 0; i < size; i++)
        {
            inputMatrix[0][i] = points.get(i).x - center.x;
            inputMatrix[1][i] = points.get(i).y - center.y;
            inputMatrix[2][i] = 1;
        }
        int[][] result = multiplyMatrices(matrix, inputMatrix);
        for(int i = 0; i < size; i++)
            resultList.add(
                    new PointXY(result[0][i] + center.x, result[1][i] + center.y));
        return resultList;
    }

    public static int[][] multiplyMatrices(int[][] matrix, int[][] inputMatrix) {
        int m1 = matrix.length;
        int n1 = matrix[0].length;
        int n2 = inputMatrix[1].length;
        int[][] result = new int[m1][n2];

        for (int i = 0; i < m1; i++)
        {
            for (int j = 0; j < n2; j++)
            {
                for (int k = 0; k < n1; k++)
                    result[i][j] += matrix[i][k] * inputMatrix[k][j];
            }
        }
        return result;
    }

    public static int[][] multiplyMatrices(double[][] matrix, int[][] inputMatrix) {
        int m1 = matrix.length;
        int n1 = matrix[0].length;
        int n2 = inputMatrix[1].length;
        int[][] result = new int[m1][n2];

        for (int i = 0; i < m1; i++)
        {
            for (int j = 0; j < n2; j++)
            {
                for (int k = 0; k < n1; k++)
                    result[i][j] += matrix[i][k] * inputMatrix[k][j];
            }
        }
        return result;
    }
}
