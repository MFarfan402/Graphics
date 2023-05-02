package shapes;

import shapes.lines.Line;
import utilities.PointXY;

import java.awt.*;
import java.util.ArrayList;

public class Polygon {

    public Polygon(){}

    public static ArrayList<PointXY> PolygonToPointsXY(ArrayList<PointXY> points)
    {
        ArrayList<PointXY> newPoints = new ArrayList<PointXY>();
        for (int i = 0; i < points.size(); i++)
        {
            Line line = (i == points.size() - 1) ?
                    new Line(points.get(i), points.get(0)):
                    new Line(points.get(i), points.get(i+1));
            newPoints.addAll(line.LineToPointsXY());
        }
        return newPoints;
    }
}