package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.Robot;
import org.usfirst.frc.team4859.robot.RobotMap;
//import org.usfirst.frc.team4859.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShiftUp extends Command {
	
    public ShiftUp() {
    	requires(Robot.shifters);
    }

    protected void initialize() {
    	Robot.shifters.pneumaticShiftUp();
		System.out.println("ShiftUp command ran");
		SmartDashboard.putBoolean("Shifted Up", true);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.shifters.pneumaticShiftUp();
    }

    protected void interrupted() {
    }
}