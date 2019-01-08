package org.usfirst.frc.team4859.robot.commands;

import org.usfirst.frc.team4859.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DdrTurn extends Command {
	
	double sped;
    
	public DdrTurn(double speed) {
    	sped=speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTwist=sped;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTwist=0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	RobotMap.driveTwist=0;
    }
}
