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
import org.usfirst.frc.team4859.robot.commands.ShiftDown;
import org.usfirst.frc.team4859.robot.commands.ShiftUp;

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
	
	//declares button(s)
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
		switch (RobotMap.driveMode) {	
			case "Joystick":
				SmartDashboard.putString("Mode", "Joystick");
				five.whenPressed(new ShiftUp());
				three.whenPressed(new ShiftDown());
				four.whenPressed(new Pmode());
				six.whenPressed(new Pmode2());
				break;
			
			case "Wheel":
				SmartDashboard.putString("Mode", "Wheel");
				six.whenPressed(new ShiftUp());
				five.whenPressed(new ShiftDown());
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				break;
			
			case "WheelThrottle":
				SmartDashboard.putString("Mode", "WheelThrottle");
				six.whenPressed(new ShiftUp());
				five.whenPressed(new ShiftDown());
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				break;
		
			case "Xbox":
				SmartDashboard.putString("Mode", "Xbox");
				six.whenPressed(new ShiftUp());
				five.whenPressed(new ShiftDown());
				seven.whenPressed(new Pmode());
				eight.whenPressed(new Pmode2());
				break;
				
			case "DDR pad":
				SmartDashboard.putString("Mode", "DDR pad");
				one.whenPressed(new DdrDrive((RobotMap.pmode)? 0.005:0.05));
				two.whenPressed(new DdrDrive((RobotMap.pmode)? -0.005:-0.05));
				three.whenPressed(new Pmode());
				four.whenPressed(new Pmode2());
				break;
				
			default:
				SmartDashboard.putString("Mode", "Default (Joystick)");
				five.whenPressed(new ShiftUp());
				three.whenPressed(new ShiftDown());
				four.whenPressed(new Pmode());
				six.whenPressed(new Pmode2());
				break;
		}
	}
}
