package org.firstinspires.ftc.teamcode.opmodes.teleop;


import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.robot.HazmatRobot;


import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "Teleop")
public class Teleop extends NextOpMode {
    private final HazmatRobot hazmatRobot;

    public Teleop(HazmatRobot hazmatRobot) {
        super(hazmatRobot);
        this.hazmatRobot = hazmatRobot;
    }

    @Override
    public void start() {
        Trigger.Companion.getDefaultEventLoop().clear();

        CommandGamepad gp1 = new CommandGamepad(gamepad1);
        CommandGamepad gp2 = new CommandGamepad(gamepad2);


        hazmatRobot.startDrive(gamepad1);

        gp1.leftBumper().onTrue(instant(()->hazmatRobot.getIntake().cycle()));
        gp1.rightBumper().onTrue(hazmatRobot.launch());

        gp2.dpadUp().onTrue(hazmatRobot.getLauncher().incrementPower());
        gp2.dpadDown().onTrue(hazmatRobot.getLauncher().decrementPower());

        gp2.circle().onTrue(hazmatRobot.getTransfer().open());
        gp2.square().onTrue(hazmatRobot.getTransfer().close());

    }

    @Override
    public void periodic() {
//        telemetry.addData("Launcher Gate Servo Position", hazmatRobot.getLauncher().getServoPos());
        telemetry.addData("Launcher Motor Power", hazmatRobot.getLauncher().getMotorSpeed());
        telemetry.addData("Intake state", hazmatRobot.getIntake().getSpeed());

        telemetry.update();
    }

}
