package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {
    private IntakeState intakeState;

    public Intake() {
        intakeState = IntakeState.OFF;
    }

    public Command on() {
        return instant(() -> {
            intakeMotor.setThrottle(IntakeState.ON.getPower());
            this.intakeState = IntakeState.ON;
        });
    }

    final private NextMotor intakeMotor = new NextMotor(RobotController.controlHub(), Config.intakeMotor);

    public Command off() {
        return instant(() -> {
            intakeMotor.setThrottle(IntakeState.OFF.getPower());
            this.intakeState = IntakeState.OFF;
        });
    }

    public Command reverse() {
        return instant(() -> {
            intakeMotor.setThrottle(IntakeState.REVERSE.getPower());
            this.intakeState = IntakeState.REVERSE;
        });
    }

    public IntakeState getIntakeState() {
        return intakeState;
    }

    public enum IntakeState {
        ON(1),
        OFF(0),
        REVERSE(-1);

        private int power;
        IntakeState(int power){
            this.power = power;
        }

        public int getPower(){
            return power;
        }
    }
}

