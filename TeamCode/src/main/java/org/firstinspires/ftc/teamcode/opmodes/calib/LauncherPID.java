package org.firstinspires.ftc.teamcode.opmodes.calib;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;

import static dev.nextftc.units.Units.RotationsPerMinute;

import org.firstinspires.ftc.teamcode.mechanisms.Launcher;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "Launcher PIDFF")
public class LauncherPID extends NextOpMode {
    public static double targetVelocity = 10000 ;
    public static double kS = 0;
    private final HazmatRobot robot;
    public LauncherPID(HazmatRobot robot) {
        super(robot);
        this.robot = robot;

        Trigger.Companion.getDefaultEventLoop().clear();
    }

    @Override
    public void start() {
        schedule(instant(() -> robot.getLauncher().setTargetVelocity(targetVelocity)));

        CommandGamepad gp1 = new CommandGamepad(gamepad1);

        gp1.dpadRight().onTrue(instant(() -> kS+=0.01));
        gp1.dpadLeft().onTrue(instant(() -> kS-=0.01));
    }

    @Override
    public void periodic() {

        // Update PID / FF constants

        robot.getLauncher().getLauncherMotor().getVelocityConstants().setKS(kS);
        // Telemetry
        telemetry.addData("Target Velocity", targetVelocity);
        telemetry.addData("Measured Velocity", robot.getLauncher().getLauncherMotor().getEncoderVelocity().into(RotationsPerMinute));

        telemetry.addData("kS", kS);

        telemetry.update();
    }
}