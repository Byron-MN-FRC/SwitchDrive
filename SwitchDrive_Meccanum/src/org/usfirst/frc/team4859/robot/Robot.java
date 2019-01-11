/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4859.robot.subsystems.DriveTrain;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	public static DriveTrain driveTrainSubSys = new DriveTrain();
	public static OI operatorInterface;
	
	public static UsbCamera cameraBackward = CameraServer.getInstance().startAutomaticCapture("Backward", 1);
	public static UsbCamera cameraForward = CameraServer.getInstance().startAutomaticCapture("Forward", 0);
	
	//creates the chooser put on smartdashboard looking for the variable type String
	SendableChooser<String> driveSelect = new SendableChooser<String>();
	
	@Override
	public void robotPeriodic() { 
		UpdateStatus();
	}


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		operatorInterface = new OI();
		
		SmartDashboard.putData(Scheduler.getInstance());
		
		//Adds all drive modes to the smartdashboard from the array in robotmap so I don't have to manually add them each time
		//it adds the name that appears onto the smartdashboard for each option, then adds that again as a string and attatches
		//it to the option on the dashboard because the name is the string it looks for to switch drive modes
		for(int i = 1; i<RobotMap.possibleDrive.length-1; i++) {
			driveSelect.addObject(RobotMap.possibleDrive[i], RobotMap.possibleDrive[i]);
		}
		
		//Puts the selector onto the smartdashboard
		SmartDashboard.putData("Drive Mode Selection", driveSelect);
		
		//Limits camera output to meet bandwith limit
		cameraBackward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		cameraForward.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 10);
		cameraBackward.setExposureManual(70);
		cameraForward.setExposureManual(70);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		//gets the drive mode selected on the smartdashboard and then puts which one is selected out for troubleshooting
		RobotMap.driveMode = driveSelect.getSelected();
		SmartDashboard.putString("DrMode", RobotMap.driveMode);
		
		//Sets starting power of motors for DDR pad mode to 0.5 so they dont have to spam to get to a reasonable speed
		if(RobotMap.driveMode=="DDR pad") {
			RobotMap.drivePower=0.5;
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		//re-gets the drive mode, then pushes if it is in precision (slow) mode and what drive mode is selected
		RobotMap.driveMode = driveSelect.getSelected();
		SmartDashboard.putBoolean("Pmode?", RobotMap.pmode);
		SmartDashboard.putString("DrMode", RobotMap.driveMode);
	}
	

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
	
	private void UpdateStatus() {
	}
}
