/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot;

import org.usfirst.frc.team4859.robot.commands.DdrDrive;
import org.usfirst.frc.team4859.robot.commands.DdrTurn;
import org.usfirst.frc.team4859.robot.commands.Pmode;
import org.usfirst.frc.team4859.robot.commands.Pmode2;
import org.usfirst.frc.team4859.robot.commands.WheelSideways;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

// OI Stands for Operator Interface
public class OI {
	
	//declares joystick(s)
	private final Joystick joystick = new Joystick(0);
	
	//declares potential button(s)
	Button one = new JoystickButton(joystick,1);
	Button two = new JoystickButton(joystick, 2);
	Button three = new JoystickButton(joystick, 3);
	Button four = new JoystickButton(joystick, 4);
	Button five = new JoystickButton(joystick, 5);
	Button six = new JoystickButton(joystick,6);
	Button seven = new JoystickButton(joystick,7);
	Button eight = new JoystickButton(joystick, 8);
	Button nine = new JoystickButton(joystick, 9);
	Button ten = new JoystickButton(joystick, 10);
	Button eleven = new JoystickButton(joystick, 11);
	Button twelve = new JoystickButton(joystick,12);

	//function that grabs joystick values
	public Joystick getJoyStick() { return joystick; }
	
	public OI () {
		
		//switches which buttons do what for each controller
		//Pmode turns on precision mode
		//Pmode2 turns off precision mode
		//WheelSideways allows the robot to drive sideways when using the racing wheel
		//Ddr turn assigns either a negative or positive one to control which direction the robot is turning
		//Ddr drive increases or decreases the power applied to the motors when using the ddr pad
		switch (RobotMap.driveMode) {	
			case "Joystick":
				SmartDashboard.putString("Mode", "Joystick");
				
				four.whenPressed(new Pmode());
				six.whenPressed(new Pmode2());
				break;
			
			case "Wheel":
				SmartDashboard.putString("Mode", "Wheel");
				
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				
				five.whileHeld(new WheelSideways((RobotMap.pmode)?-0.3:-0.8));
				six.whileHeld(new WheelSideways((RobotMap.pmode)?0.3:0.8));
				break;
			
			case "WheelThrottle":
				SmartDashboard.putString("Mode", "WheelThrottle");
				
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				
				five.whileHeld(new WheelSideways((RobotMap.pmode)?-0.3:-0.8));
				six.whileHeld(new WheelSideways((RobotMap.pmode)?0.3:0.8));
				break;
		
			case "Xbox":
				SmartDashboard.putString("Mode", "Xbox");
				
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				break;
				
			case "DDR pad":
				SmartDashboard.putString("Mode", "DDR pad");
				
				one.whenPressed(new DdrDrive((RobotMap.pmode)? 0.005:0.05));
				two.whenPressed(new DdrDrive((RobotMap.pmode)? -0.005:-0.05));
				three.whileHeld(new DdrTurn(-1));
				four.whileHeld(new DdrTurn(1));
				break;
				
			default:
				SmartDashboard.putString("Mode", "Default (Joystick)");
				
				four.whenPressed(new Pmode());
				six.whenPressed(new Pmode2());
				break;
		}
	}
}
