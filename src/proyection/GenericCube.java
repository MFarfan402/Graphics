package proyection;

import utilities.PointXYZ;

import java.util.ArrayList;

public class GenericCube {
    public ArrayList<PointXYZ> pointsXYZ;
    public GenericCube()
    {
        pointsXYZ = new ArrayList<>();
        pointsXYZ.add(new PointXYZ(50, 150, 150)); // A
        pointsXYZ.add(new PointXYZ(150, 150, 150)); // B
        pointsXYZ.add(new PointXYZ(50, 250, 150)); // C
        pointsXYZ.add(new PointXYZ(150, 250, 150)); // D

        pointsXYZ.add(new PointXYZ(50, 150, 250)); // E
        pointsXYZ.add(new PointXYZ(150, 150, 250)); // F
        pointsXYZ.add(new PointXYZ(50, 250, 250)); // G
        pointsXYZ.add(new PointXYZ(150, 250, 250)); // H
    }
}
