package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DdrDrive extends Command {
double sped;
    public DdrDrive(double speedUp) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	sped=speedUp;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.drivePower=RobotMap.drivePower+sped;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
