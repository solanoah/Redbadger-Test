package main;

import main.enums.Direction;
import main.enums.RobotState;
import main.enums.TurnType;

import java.awt.*;

/**
 * Created by solanoah on 05/11/2016.
 */
public class CommandCentre {

    private final GridModel gridModel;

    /**
     * @param gridModel
     */
    public CommandCentre(GridModel gridModel){
        this.gridModel = gridModel;
    }

    /**
     * @param robotName
     * @param robot
     */
    public void sendRobotToGrid(String robotName, Robot robot) {
        gridModel.getRobots().put(robotName, robot);
    }

    /**
     * @param robotName
     * @param instruction
     */
    public void sendInstructionToRobot(String robotName, String instruction){

        // get the robot to operate on
        Robot robot = gridModel.getRobots().get(robotName);

        // split the instruction into appropriate format
        char[] charArray = instruction.toCharArray();

        // Validation
        if (charArray.length > 100)
        {
            robot.setRobotState(RobotState.InvalidInstruction);
            return;
        }

        for (char commandChar : charArray) {

            if (robot.getRobotState() != RobotState.Alive){
                break;
            }

            switch(commandChar){
                case 'R':
                    changeDirection(TurnType.R, robot);
                    break;
                case 'L':
                    changeDirection(TurnType.L, robot);
                    break;
                case 'F':
                    changeCoordinate(robot);
                    break;
            }
        }
    }

    /**
     * @param turntype
     * @param robot
     */
    private void changeDirection(TurnType turntype, Robot robot){

        switch(robot.getCurrentPosition().getDirection()){
            case N:
                robot.getCurrentPosition().setDirection(turntype == TurnType.R ? Direction.E : Direction.W);
                break;
            case E:
                robot.getCurrentPosition().setDirection(turntype == TurnType.R ? Direction.S : Direction.N);
                break;
            case S:
                robot.getCurrentPosition().setDirection(turntype == TurnType.R ? Direction.W : Direction.E);
                break;
            case W:
                robot.getCurrentPosition().setDirection(turntype == TurnType.R ? Direction.N : Direction.S);
                break;
        }
    }

    /**
     * @param robot
     */
    private void changeCoordinate(Robot robot) {

        // Do we have any robot in a "scented" state that has the same position as this robot
        boolean anyScented = gridModel.getRobots().values().stream().anyMatch(r -> r.getRobotState() == RobotState.Scent
                && r.getCurrentPosition().equals(robot.getCurrentPosition()));

        if (anyScented) {
            return;
        }

        // set default next coordinate
        Point nextCoordinate = new Point(robot.getCurrentPosition().getCoordinate());

        // move to the next coordinate
        switch (robot.getCurrentPosition().getDirection()) {
            case N:
                nextCoordinate.y++;
                break;
            case E:
                nextCoordinate.x++;
                break;
            case S:
                nextCoordinate.y--;
                break;
            case W:
                nextCoordinate.x--;
                break;
        }

        // Validation
        if (nextCoordinate.x > 50 || nextCoordinate.y > 50)
        {
            robot.setRobotState(RobotState.ExceedMaxCoordinate);
            return;
        }

        // Check if the next coordinate will lead to the robot being lost (scent) from the grid
        // Update state to "scent" if it will be lost
        if (nextCoordinate.x > gridModel.getUpperRightPoint().x || nextCoordinate.y > gridModel.getUpperRightPoint().y ||
                nextCoordinate.x < gridModel.getLowerLeftPoint().x || nextCoordinate.y < gridModel.getLowerLeftPoint().y)
        {
            robot.setRobotState(RobotState.Scent);
        }

        // If the next coordinate results to Alive state, update the current coordinate to next
        if (robot.getRobotState() == RobotState.Alive) {
            robot.getCurrentPosition().setCoordinate(new Point(nextCoordinate));
        }
    }
}
