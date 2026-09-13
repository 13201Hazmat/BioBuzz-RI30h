package org.firstinspires.ftc.teamcode.opmodes.teleop;


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
        Scheduler.reset();
        this.hazmatRobot = hazmatRobot;
    }

    @Override
    public void start() {
        Trigger.Companion.getDefaultEventLoop().clear();

        CommandGamepad gp1 = new CommandGamepad(gamepad1);

        hazmatRobot.startDrive(gamepad1);

        gp1.leftBumper().whileTrue(hazmatRobot.getIntake().on());
        gp1.leftBumper().whileTrue(hazmatRobot.getIntake().reverse());

        gp1.circle().onTrue(hazmatRobot.getLauncher().setPowerThingy());

        gp1.dpadLeft().onTrue(hazmatRobot.getLauncher().zeroLauncherGate());
        gp1.dpadUp().onTrue(hazmatRobot.getLauncher().incrementLauncherGate());
        gp1.dpadDown().onTrue(hazmatRobot.getLauncher().decrementLauncherGate());

        gp1.cross().onTrue(hazmatRobot.getLauncher().incrementPower());
        gp1.triangle().onTrue(hazmatRobot.getLauncher().decrementPower());
    }

    @Override
    public void periodic() {
        telemetry.addData("Launcher Gate Servo Position", hazmatRobot.getLauncher().getServoPos());
        telemetry.addData("Launcher Motor Power", hazmatRobot.getLauncher().getMotorSpeed());
        telemetry.addData("Intake state", hazmatRobot.getIntake().getIntakeState());
        telemetry.addData("Color Sensor Result", hazmatRobot.getTransfer().getResult());

        telemetry.update();
    }

}
