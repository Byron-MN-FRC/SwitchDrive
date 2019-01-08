package org.usfirst.frc.team4859.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shifters extends Subsystem {
	DoubleSolenoid shifter = new DoubleSolenoid(1, 0);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {}
    
    public void pneumaticShiftUp() {
    	shifter.set(Value.kForward);
    }
    
    public void pneumaticShiftDown() {	
    	shifter.set(Value.kReverse);
    }  
}