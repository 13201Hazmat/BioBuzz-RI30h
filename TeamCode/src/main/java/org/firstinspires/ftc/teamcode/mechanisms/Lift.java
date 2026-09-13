package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.Degrees;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.Angle;

public class Lift implements Mechanism {

    private static final double kP = 0.005;
    private static final double kD = 0;

    private static final double LAUNCH_TICKS = 200;
    private static final double LOW_TICKS = 1000;
    private static final double HOME_TICKS = 100;

    private static final double TICKS_PER_ROTATION = 384.5; //435 motor

    Angle anglePerCount = Degrees.of(360.0 / TICKS_PER_ROTATION);
    public final NextMotor l = new NextMotor(RobotController.controlHub(), Config.leftLiftMotor, anglePerCount);
    public final NextMotor r = new NextMotor(RobotController.expansionHub(), Config.rightLiftMotor, anglePerCount);

    public enum LiftState {
        LAUNCH,
        HOME
    }


    public Lift() {
        l.setDirection(NextMotor.Direction.REVERSE);
        r.follow(l, NextMotor.Direction.FORWARD);

        l.getPositionConstants().setKP(kP);
        l.getPositionConstants().setKD(kD);

        r.getPositionConstants().setKP(kP);
        r.getPositionConstants().setKD(kD);

        setPosition(LiftState.HOME);
    }

    private void setPos(double goalTicks) {
        Angle setpointAngle = Degrees.of(360.0 * goalTicks / TICKS_PER_ROTATION);
        l.setPositionSetpoint(setpointAngle);

    }

    public Command setPosition(LiftState state) {
        return Commands.instant(() -> {
            switch (state) {
                case LAUNCH:
                    setPos(LAUNCH_TICKS);
                    break;
                case HOME:
                default:
                    setPos(HOME_TICKS);
                    break;
            }
        }).requiring(this);
    }
    public Command deltaUp(){
        return Commands.instant(()-> setPos(l.getEncoderPosition().getMagnitude() + 50));
    }

    public Command deltaDown() {
        return Commands.instant(() -> setPos(l.getEncoderPosition().getMagnitude() - 50));
    }


    public String getPos() {
        return "Left Encoder:" + l.getEncoderPosition().getMagnitude() + " Right Encoder:" + r.getEncoderPosition().getMagnitude();
    }
}