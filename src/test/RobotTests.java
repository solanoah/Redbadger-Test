package test;

import main.CommandCentre;
import main.GridModel;
import main.GridPosition;
import main.Robot;
import main.enums.Direction;

import java.awt.*;

/**
 * Created by solanoah on 05/11/2016.
 */
public class RobotTests {

    public static void main(String[] args) {

        // Model planet Mars using the Grid
        GridModel planetMars = new GridModel(new Point(5,3), new Point(0,0));

        // Tell command centre to initialise operation on planetMars
        CommandCentre planetEarth = new CommandCentre(planetMars);

        // Send robot from earth to planet Mars
        planetEarth.sendRobotToGrid("red", new Robot( new GridPosition(Direction.E,new Point(1,1))));
        planetEarth.sendRobotToGrid("blue", new Robot(new GridPosition(Direction.N, new Point(3,2))));
        planetEarth.sendRobotToGrid("green", new Robot(new GridPosition(Direction.W, new Point(0,3))));

        // Send instruction from earth to planet Mars
        planetEarth.sendInstructionToRobot("red", "RFRFRFRF");
        planetEarth.sendInstructionToRobot("blue", "FRRFLLFFRRFLL");
        planetEarth.sendInstructionToRobot("green", "LLFFFLFLFL");

        // Print the final position of robots on planet Mars
        planetMars.getRobots().values().forEach((v) -> System.out.println(v.toString()));

        System.exit(0);
    }
}
