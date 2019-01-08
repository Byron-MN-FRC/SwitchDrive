package org.usfirst.frc.team4859.robot;

public class RobotMap {
	// Motor IDs
	public static int talonIDRightMaster = 1;
	public static int talonIDRightFollower = 2;
	public static int talonIDLeftMaster = 4;
	public static int talonIDLeftFollower = 5;
	
	//possible drive modes
	public static String driveMode = "Joystick";
	public static String[] possibleDrive = {"Joystick", "Wheel", "Xbox", "WheelThrottle", "DDR pad", "Keyboard"};
	
	//public power vars
	public static double drivePower = 0;
	public static double driveTwist = 0;
	public static boolean pmode = false;
	
	//function to prevent variables from going too high or low
	public static double variableBounds (double variable, double uppval, double downval) {
		if(variable > uppval) {
			variable= uppval;
		}else if (variable<downval) {
			variable = downval;
		}
		return variable;
	}
}