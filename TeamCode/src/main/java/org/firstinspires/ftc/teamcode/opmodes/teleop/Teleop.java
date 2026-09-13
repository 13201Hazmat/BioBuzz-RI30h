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

        hazmatRobot.startDrive(gamepad1);

        gp1.leftBumper().onTrue(instant(()->hazmatRobot.getIntake().cycle()));

        gp1.circle().onTrue(hazmatRobot.getLauncher().setPowerThingy());

        gp1.dpadLeft()
                .toggleOnTrue(hazmatRobot.getTransfer().open())
                .toggleOnFalse(hazmatRobot.getTransfer().close());
//        gp1.dpadUp().onTrue(hazmatRobot.getLauncher().incrementLauncherGate());
//        gp1.dpadDown().onTrue(hazmatRobot.getLauncher().decrementLauncherGate());

        gp1.dpadUp().onTrue(hazmatRobot.getLauncher().incrementPower());
        gp1.dpadDown().onTrue(hazmatRobot.getLauncher().decrementPower());

        gp1.dpadRight().onTrue(hazmatRobot.getLift().deltaUp());
        gp1.dpadLeft().onTrue(hazmatRobot.getLift().deltaDown());

    }

    @Override
    public void periodic() {
//        telemetry.addData("Launcher Gate Servo Position", hazmatRobot.getLauncher().getServoPos());
        telemetry.addData("Launcher Motor Power", hazmatRobot.getLauncher().getMotorSpeed());
        telemetry.addData("Intake state", hazmatRobot.getIntake().getSpeed());
        telemetry.addData("Color Sensor Result", hazmatRobot.getTransfer().getResult());

        telemetry.update();
    }

}
