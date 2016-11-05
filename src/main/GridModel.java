package main;

/**
 * Created by solanoah on 05/11/2016.
 */

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class GridModel {

    private final Point upperRightPoint;
    private final Point lowerLeftPoint;
    private Map<String, Robot> robots = new LinkedHashMap<>();

    /**
     * @param upperRightPoint
     * @param lowerLeftPoint
     */
    public GridModel(Point upperRightPoint, Point lowerLeftPoint){
        this.upperRightPoint = upperRightPoint;
        this.lowerLeftPoint = lowerLeftPoint;
    }

    public Map<String, Robot> getRobots() {
        return robots;
    }

    /**
     * @return
     */
    Point getUpperRightPoint() {
        return upperRightPoint;
    }

    /**
     * @return
     */
    Point getLowerLeftPoint() {
        return lowerLeftPoint;
    }
}
