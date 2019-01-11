package org.usfirst.frc.team4859.robot;

public class RobotMap {
	// Motor IDs
	public static int talonIDChassisFrontLeft = 1; 
	public static int talonIDChassisFrontRight = 2;
	public static int talonIDChassisBackLeft = 3;
	public static int talonIDChassisBackRight = 4;
	
	//variable to store the drive mode
	public static String driveMode = "Joystick";
	//declares possible drive modes
	public static String[] possibleDrive = {"Joystick", "Wheel", "Xbox", "WheelThrottle", "DDR pad", "Keyboard"};
	
	//variables to store the powers used for the motors
	public static double drivePower = 0;
	public static double driveTwist = 0;
	public static double driveSide = 0;
	//variable to trigger percision mode (makes the robot drive slower)
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