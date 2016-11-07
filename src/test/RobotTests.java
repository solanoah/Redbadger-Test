package test;

import main.CommandCentre;
import main.GridModel;
import main.GridPosition;
import main.Robot;
import main.enums.Direction;

import java.awt.*;
import java.io.Console;
import java.io.IOException;

/**
 * Created by solanoah on 05/11/2016.
 */
public class RobotTests {

    public static void main(String[] args) throws IOException {
        Console console = System.console();

        System.out.print("Enter Mars Upper right coordinate in format XY => 11:  ");
        String upperRightCoordinate = console.readLine();

        // Model planet Mars using the Grid
        GridModel planetMars = createMars(upperRightCoordinate);

        // Tell command centre to initialise operation on planetMars
        CommandCentre planetEarth = new CommandCentre(planetMars);

        System.out.print("Send a robot to Mars, Y/N: ");
        String sendfirst = console.readLine();
        if (sendfirst.toLowerCase().equals("n") ){
            System.exit(0);
        }

        Integer counter = 1;

        while (true) {

            String robotName = "robot" + counter;
            System.out.print("Enter Robot location in format XYD (X and Y axis, D Direction) => 50N: ");
            String location =  console.readLine();
            createRobot(planetEarth, robotName, location);

            System.out.print("Enter Robot instruction: ");
            String instruction =  console.readLine();
            planetEarth.sendInstructionToRobot(robotName, instruction);

            System.out.print("Send another robot to Mars, Y/N: ");
            String tryAgain = console.readLine().toLowerCase().trim();
            if (tryAgain.equals("n") ){
                break;
            }

            counter++;
        }

        // Print the final position of robots on planet Mars
        System.out.print("\n Position of all robot(s) is... \n");
        System.out.print("________________________________________\n");
        planetMars.getRobots().values().forEach((v) -> System.out.println(v.toString()));

        System.exit(0);
    }

    /**
     * @param upperRightCoordinate
     * @return
     */
    public static GridModel createMars(String upperRightCoordinate)
    {
        char[] charLocationArray = upperRightCoordinate.toCharArray();
        Integer x = Integer.parseInt(String.valueOf(charLocationArray[0]));
        Integer y = Integer.parseInt(String.valueOf(charLocationArray[1]));

        return new GridModel(new Point(x,y), new Point(0,0));
    }

    /**
     * @param planetEarth
     * @param robotName
     * @param location
     */
    public static void createRobot(CommandCentre planetEarth, String robotName, String location)
    {
        char[] charLocationArray = location.toCharArray();
        Integer x = Integer.parseInt(String.valueOf(charLocationArray[0]));
        Integer y = Integer.parseInt(String.valueOf(charLocationArray[1]));

        Direction direction;

        switch(String.valueOf(charLocationArray[2]).toUpperCase()){
            case "E":
                direction = Direction.E;
                break;
            case "S":
                direction = Direction.S;
                break;
            case "W":
                direction = Direction.W;
                break;
            default:
                direction = Direction.N;
        }

        planetEarth.sendRobotToGrid(robotName, new Robot( new GridPosition(direction,new Point(x,y))));
    }
}
