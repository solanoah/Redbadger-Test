package main;

import main.enums.Direction;

import java.awt.*;

/**
 * Created by solanoah on 05/11/2016.
 */
public class GridPosition {

    private Point coordinate;
    private Direction direction;

    /**
     * @param coordinate
     * @param direction
     */
    public GridPosition(Direction direction, Point coordinate) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    Point getCoordinate() {
        return coordinate;
    }

    void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    boolean equals(GridPosition position){
        return coordinate.equals(position.getCoordinate()) && direction.equals(position.getDirection());
    }

    @Override
    public String toString(){
        return getCoordinate().x + " " + getCoordinate().y + " " + getDirection().toString();
    }
}
