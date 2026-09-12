package org.firstinspires.ftc.teamcode.mechanisms;

import com.pedropathing.ivy.Command;

import org.firstinspires.ftc.teamcode.data.Config;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;

public class Intake implements Mechanism {
    /**
     * Intake constructor.
     */
    public Intake(){}

    enum IntakeState {
        ON(1),
        OFF(0),
        REVERSE(-1);

        private int power;
        IntakeState(int power){
            this.power = power;
        }

        /**
         * A getter for intake motor power.
         * @return intake motor power
         */
        public int getPower(){
            return power;
        }
    }

    final private NextMotor intakeMotor = new NextMotor(RobotController.controlHub(), Config.intakeMotor);

    /**
     * A method to turn intake on.
     * @return a command that sets intake on
     */
    public Command on() {return instant(() -> intakeMotor.setThrottle(IntakeState.ON.getPower()));}

    /**
     * A method to turn intake off.
     * @return a command that sets intake off
     */
    public Command off(){return instant(() -> intakeMotor.setThrottle(IntakeState.OFF.getPower()));}

    /**
     * A method to turn intake reverse.
     * @return a command that sets reverse
     */
    public Command reverse(){return instant(() -> intakeMotor.setThrottle(IntakeState.REVERSE.getPower()));}
}

