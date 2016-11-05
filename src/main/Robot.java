package main;

import main.enums.RobotState;

/**
 * Created by solanoah on 05/11/2016.
 */
public class Robot {

    private GridPosition currentPosition;
    private RobotState robotState = RobotState.Alive;

    /**
     * @param currentPosition
     */
    public Robot(GridPosition currentPosition){
        this.setCurrentPosition(currentPosition);
    }

    /**
     * @return
     */
    RobotState getRobotState() {
        return this.robotState;
    }

    /**
     * @param robotState
     */
    void setRobotState(RobotState robotState) {
        this.robotState = robotState;
    }

    /**
     * @return
     */
    GridPosition getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @param currentPosition
     */
    private void setCurrentPosition(GridPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * @return
     */
    @Override
    public String toString(){

        if (this.robotState == RobotState.InvalidInstruction )
        {
            return "Invalid instruction sent to Robot";
        }

        if (this.robotState == RobotState.ExceedMaxCoordinate )
        {
            return "Max coordinate exceeded";
        }

        return this.robotState == RobotState.Alive ? currentPosition.toString() : (currentPosition.toString() + " LOST");
    }
}
