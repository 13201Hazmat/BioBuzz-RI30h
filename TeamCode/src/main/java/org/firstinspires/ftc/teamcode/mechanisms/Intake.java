package org.firstinspires.ftc.teamcode.mechanisms;
import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;
import static dev.nextftc.units.Units.Inches;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.AngularVelocity;

public class Intake implements Mechanism {
    NextMotor i = new NextMotor(RobotController.controlHub(), Config.intakeMotor);
    private IntakeState intakeState;
    public enum IntakeState {
        FORWARD,
        REVERSE,
        OFF
    }

    private double power;
    private final double forward = 1.0;
    private final double reverse = -1.0;
    private final double off = 0.0;

    private AngularVelocity speed;
    public Intake(){
        intakeState = IntakeState.OFF;
        power = off;

        i.setDirection(NextMotor.Direction.FORWARD);
    }

    private void setState(IntakeState intakeState) {
        this.intakeState = intakeState;
    }

    public Command setSpeed(IntakeState s) {
        return instant(() -> this.setState(s));
    }

    public double getSpeed(){
        return i.getThrottle();
    }

    public void cycle(){
        if (intakeState == IntakeState.FORWARD) intakeState = IntakeState.REVERSE;
        else if (intakeState == IntakeState.REVERSE)  intakeState = IntakeState.OFF;
        else intakeState = IntakeState.FORWARD;
    }


    @Override
    public void periodic() {
        switch (intakeState) {
            case FORWARD:
                i.setThrottle(forward);
                break;
            case REVERSE:
                i.setThrottle(reverse);
                break;
            case OFF:
                i.setThrottle(off);
                break;
        }
    }
}