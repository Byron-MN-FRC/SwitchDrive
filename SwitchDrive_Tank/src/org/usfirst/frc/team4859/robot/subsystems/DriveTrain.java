package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.robot.RobotMap;
import org.usfirst.frc.team4859.robot.ThrottleLookup.ThrottleLookup;
import org.usfirst.frc.team4859.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team4859.robot.commands.ShiftDown;
import org.usfirst.frc.team4859.robot.commands.ShiftUp;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_TalonSRX motorLeftMaster = new WPI_TalonSRX(RobotMap.talonIDLeftMaster);
	public static WPI_TalonSRX motorLeftFollower = new WPI_TalonSRX(RobotMap.talonIDLeftFollower);
	
	public static WPI_TalonSRX motorRightMaster = new WPI_TalonSRX(RobotMap.talonIDRightMaster);
	public static WPI_TalonSRX motorRightFollower = new WPI_TalonSRX(RobotMap.talonIDRightFollower);
	
	public static SpeedControllerGroup drivetrainLeft = new SpeedControllerGroup(motorLeftMaster, motorLeftFollower);
	public static SpeedControllerGroup drivetrainRight = new SpeedControllerGroup(motorRightMaster, motorRightFollower);
	
	public static DifferentialDrive drivetrain = new DifferentialDrive(drivetrainLeft, drivetrainRight);
	
	private double drivePower;
	private double driveTurn;

	public void initDefaultCommand () {
		setDefaultCommand(new DriveWithJoystick());
	}

	public void driveWithJoystick(Joystick joystick) {
		double joystickPower;
		double joystickForward;
		double joystickTwist;
		double joystickReverse;
		double turnStick;
		int povStick;
		switch (RobotMap.driveMode) {
			case "Joystick":
				joystickForward=-joystick.getY();
				turnStick=joystick.getTwist();
				
				drivePower = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", joystickForward):ThrottleLookup.calcJoystickCorrection("NormY", joystickForward);
				driveTurn = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick);
				break;
			
			case "DDR pad":
				povStick=joystick.getPOV();
				switch(povStick) {
					case -1:
						drivePower=0;
						driveTurn=0;
						break;
					case 0:
						drivePower=RobotMap.drivePower;
						driveTurn=0;
						break;
					case 1:
						drivePower=RobotMap.drivePower;
						driveTurn=RobotMap.drivePower;
						break;
					case 2:
						drivePower=0;
						driveTurn=RobotMap.drivePower;
						break;
					case 3:
						drivePower=-RobotMap.drivePower;
						driveTurn=RobotMap.drivePower;
						break;
					case 4:
						drivePower=-RobotMap.drivePower;
						driveTurn=0;
						break;
					case 5:
						drivePower=-RobotMap.drivePower;
						driveTurn=-RobotMap.drivePower;
						break;
					case 6:
						drivePower=0;
						driveTurn=-RobotMap.drivePower;
						break;
					case 7:
						drivePower=RobotMap.drivePower;
						driveTurn=-RobotMap.drivePower;
						break;
				}
				break;
		
			case "Wheel":
				turnStick=joystick.getRawAxis(0);
				joystickForward = joystick.getRawAxis(4);
				joystickReverse = joystick.getRawAxis(1);
			
				//combines joystick power vectors
				joystickPower=joystickForward-joystickReverse;
				
				//changes twist to negative if going backwards, makes turning make more sense
				joystickTwist=	joystickPower/Math.abs(joystickPower);
				
				drivePower=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", joystickPower):ThrottleLookup.calcJoystickCorrection("NormY", joystickPower);
				driveTurn=joystickTwist*((RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick));
				break;
			
			case "WheelThrottle":
				turnStick=joystick.getRawAxis(0);
				joystickForward = joystick.getRawAxis(4);
				joystickReverse = joystick.getRawAxis(1);
		
				//combines joystick power vectors
				joystickPower=joystickForward-joystickReverse;
				
				driveTurn=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", joystickPower):ThrottleLookup.calcJoystickCorrection("NormY", joystickPower);
				drivePower=((RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick));
				break;
		
			case "Xbox":
				turnStick = joystick.getRawAxis(0);
				joystickForward = joystick.getRawAxis(3);
				joystickReverse = joystick.getRawAxis(2);
		
				//combines joystick power vectors
				joystickPower=joystickForward-joystickReverse;
				
				//changes twist to negative if going backwards, makes turning make more sense
				joystickTwist=	joystickPower/Math.abs(joystickPower);
				
				drivePower=(RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowY", joystickPower):ThrottleLookup.calcJoystickCorrection("NormY", joystickPower);
				driveTurn=joystickTwist*((RobotMap.pmode)?ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick));
				break;
			default:
				joystickForward=-joystick.getY();
				turnStick=joystick.getTwist();
				
				drivePower = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowY", joystickForward):ThrottleLookup.calcJoystickCorrection("NormY", joystickForward);
				driveTurn = (RobotMap.pmode) ? ThrottleLookup.calcJoystickCorrection("SlowT", turnStick):ThrottleLookup.calcJoystickCorrection("NormT", turnStick);	
				break;
		}
		//limits values to values between 1 and -1 as a just-in-case for potato code
		drivePower=RobotMap.variableBounds(drivePower, 1, -1);
		driveTurn=RobotMap.variableBounds(driveTurn, 1, -1);
		
		drivetrain.arcadeDrive(drivePower, driveTurn);
	}
	
	public void updateStatus() {
		SmartDashboard.putNumber("Joystick-Y", drivePower);
		SmartDashboard.putNumber("Joystick-Twist", drivePower);
	}
	
	public void stop() {
		drivetrain.arcadeDrive(0, 0);
	}
}

