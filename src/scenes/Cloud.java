package scenes;

import shapes.MidPointCircle;
import utilities.PointXY;

import java.util.ArrayList;

public class Cloud {
    public static ArrayList<PointXY> FirstCloud()
    {
        ArrayList<PointXY> cloud = new ArrayList<PointXY>();
        cloud.add(new PointXY(400, 180));
        cloud.add(new PointXY(380, 180));
        cloud.add(new PointXY(360, 180));
        cloud.add(new PointXY(340, 180));
        cloud.add(new PointXY(390, 160));
        cloud.add(new PointXY(370, 160));
        cloud.add(new PointXY(350, 160));
        return cloud;
    }
    public static ArrayList<PointXY> SecondCloud()
    {
        ArrayList<PointXY> centerCloud2 = new ArrayList<PointXY>();
        centerCloud2.add(new PointXY(140, 250));
        centerCloud2.add(new PointXY(120, 250));
        centerCloud2.add(new PointXY(100, 250));
        centerCloud2.add(new PointXY(80, 250));
        centerCloud2.add(new PointXY(130, 230));
        centerCloud2.add(new PointXY(110, 230));
        centerCloud2.add(new PointXY(90, 230));
        return centerCloud2;
    }
    public static ArrayList<PointXY> ThirdCloud()
    {
        ArrayList<PointXY> cloud = new ArrayList<PointXY>();
        cloud.add(new PointXY(250, 320));
        cloud.add(new PointXY(270, 320));
        cloud.add(new PointXY(290, 320));
        cloud.add(new PointXY(310, 320));
        cloud.add(new PointXY(260, 300));
        cloud.add(new PointXY(280, 300));
        cloud.add(new PointXY(300, 300));
        return cloud;
    }}
