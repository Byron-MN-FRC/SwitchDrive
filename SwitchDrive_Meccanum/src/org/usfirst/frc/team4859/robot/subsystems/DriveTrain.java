package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

	public static Spark motorChassisFrontLeft = new Spark(RobotMap.talonIDChassisFrontLeft);
	public static Spark motorChassisFrontRight = new Spark(RobotMap.talonIDChassisFrontRight);
	
	public static Spark motorChassisBackLeft = new Spark(RobotMap.talonIDChassisBackLeft);
	public static Spark motorChassisBackRight = new Spark(RobotMap.talonIDChassisBackRight);

	public static MecanumDrive chassisDrive = new MecanumDrive(motorChassisFrontLeft, motorChassisBackLeft, motorChassisFrontRight, motorChassisBackRight);
	
	//Creates the final variables for storing and applying the power values
	private double drivePower;
	private double driveTurn;
	private double driveSide;
	public void initDefaultCommand () {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void driveWithJoystick(Joystick joystick) {
		
		//Creates variables for the different power value inputs for all drive modes because a switch statement isn't nice
		//and you need to create them outside the switch if you are going to use them again or it's funky
		double joystickPower;
		double joystickForward;
		double joystickTwist;
		double joystickReverse;
		double turnStick;
		double sideDrive;
		int povStick;
		
		//this changes which axis' on the controller it looks for to get the different power values based on the smartdashboard
		switch (RobotMap.driveMode) {
			case "Joystick":
				//Gets the joystick values
				joystickForward=-joystick.getY();
				turnStick=joystick.getTwist();
				sideDrive=joystick.getX();
				
				//Attatches the joystick values to power values and scales them based on the arrays in throttlelookup
				//Throttlelookup makes the joysticks less touchy 
				driveSide = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", sideDrive):ThrottleLookup.calcJoystickCorrection("NormY", sideDrive);
				drivePower = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", joystickForward):ThrottleLookup.calcJoystickCorrection("NormY", joystickForward);
				driveTurn = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick);
				break;
			
			case "DDR pad":
				//Gets the POV value from the joystick (-1 to 7) because thats how the ddr pad inputs the 4 main buttons
				povStick=joystick.getPOV();
				
				//limits value of drivepower to positive to prevent wonkiness when driving with ddr pad
				RobotMap.variableBounds(RobotMap.drivePower, 1, 0);
				
				//changes the drive powers applied to correctly drive the robot in the vector indicated by the ddr pad
				switch(povStick) {
					default:
						drivePower=0;
						driveSide=0;
						break;
					case -1:
						drivePower=0;
						driveSide=0;
						break;
					case 0:
						drivePower=RobotMap.drivePower;
						driveSide=0;
						break;
					case 1:
						drivePower=RobotMap.drivePower;
						driveSide=RobotMap.drivePower;
						break;
					case 2:
						drivePower=0;
						driveSide=RobotMap.drivePower;
						break;
					case 3:
						drivePower=-RobotMap.drivePower;
						driveSide=RobotMap.drivePower;
						break;
					case 4:
						drivePower=-RobotMap.drivePower;
						driveSide=0;
						break;
					case 5:
						drivePower=-RobotMap.drivePower;
						driveSide=-RobotMap.drivePower;
						break;
					case 6:
						drivePower=0;
						driveSide=-RobotMap.drivePower;
						break;
					case 7:
						drivePower=RobotMap.drivePower;
						driveSide=-RobotMap.drivePower;
						break;
				}
				//controlls direction of the turn, based on the same power as everything else
				driveTurn=RobotMap.driveTwist*RobotMap.drivePower;
				break;
		
			case "Wheel":
				turnStick=joystick.getRawAxis(0);
				joystickForward = joystick.getRawAxis(4);
				joystickReverse = joystick.getRawAxis(1);
		
				//combines joystick power vectors (back/forward)
				joystickPower=joystickForward-joystickReverse;
				
				//changes twist to negative if going backwards
				joystickTwist=(joystickPower/Math.abs(joystickPower));
				
				drivePower=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", joystickPower):ThrottleLookup.calcJoystickCorrection("NormY", joystickPower);
				driveTurn=joystickTwist*((RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick));
				driveSide=0;
				break;
			
			case "WheelThrottle":
				turnStick=joystick.getRawAxis(0);
				joystickForward = joystick.getRawAxis(4);
				joystickReverse = joystick.getRawAxis(1);
		
				//combines joystick power vectors (left/right)
				joystickPower=joystickForward-joystickReverse;
				
				driveTurn=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", joystickPower):ThrottleLookup.calcJoystickCorrection("NormT", joystickPower);
				drivePower = (RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", turnStick):ThrottleLookup.calcJoystickCorrection("NormY", turnStick);
				driveSide=0;
				break;
		
			case "Xbox":
				turnStick = joystick.getRawAxis(4);
				joystickForward = joystick.getRawAxis(1);
				sideDrive = joystick.getRawAxis(0);
		
				drivePower=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", joystickForward):ThrottleLookup.calcJoystickCorrection("NormY", joystickForward);
				driveSide=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", sideDrive):ThrottleLookup.calcJoystickCorrection("NormY", sideDrive);
				driveTurn=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick);;
				break;
			default:
				//the same as the joystick code, so if invalid input to the switch statement it will run the default joystick code
				joystickForward=-joystick.getY();
				turnStick=joystick.getTwist();
				sideDrive=joystick.getX();
				
				driveSide = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", sideDrive):ThrottleLookup.calcJoystickCorrection("NormY", sideDrive);
				drivePower = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", joystickForward):ThrottleLookup.calcJoystickCorrection("NormY", joystickForward);
				driveTurn = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick);
			break;
		}
		//limits values to values between 1 and -1 to prevent potential errors
		drivePower=RobotMap.variableBounds(drivePower, 1, -1);
		driveTurn=RobotMap.variableBounds(driveTurn, 1, -1);
		driveSide=RobotMap.variableBounds(driveSide, 1, -1);
		
		//Sends values to motor
		chassisDrive.driveCartesian(drivePower, driveSide, driveTurn);
	}
	
	public void updateStatus() {
		SmartDashboard.putNumber("Joystick-Y", drivePower);
		SmartDashboard.putNumber("Joystick-Twist", drivePower);
	}
	
	public void stop() {
		chassisDrive.driveCartesian(0, 0, 0);
	}
}

