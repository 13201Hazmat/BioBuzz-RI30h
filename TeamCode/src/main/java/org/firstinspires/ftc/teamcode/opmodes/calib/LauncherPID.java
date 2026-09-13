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
    public static double targetVelocity = 1000;
    public static double kV = 0.0004;
    public static double kS = 0.02;
    private final HazmatRobot robot;
    // Subsystems
    private Launcher launcher;
    public LauncherPID(HazmatRobot robot) {
        super(robot);
        this.robot = robot;

        Trigger.Companion.getDefaultEventLoop().clear();

        CommandGamepad gp1 = new CommandGamepad(gamepad1);

        gp1.rightBumper().onTrue(instant(() -> kV+=0.01));
        gp1.leftBumper().onTrue(instant(() -> kV-=0.01));

        gp1.dpadRight().onTrue(instant(() -> kS+=0.01));
        gp1.dpadLeft().onTrue(instant(() -> kS-=0.01));
    }

    @Override
    public void disabledPeriodic() {
        telemetry.addLine("Launcher PIDFF Tuner Ready!");
    }

    @Override
    public void start() {
        schedule(instant(() -> launcher.setTargetVelocity(targetVelocity)));
    }

    @Override
    public void periodic() {

        // Update shooter velocity & hood angle
        schedule(instant(() -> launcher.setTargetVelocity(targetVelocity)));

        // Update PID / FF constants

        launcher.kV = kV;
        launcher.kS = kS;

        // Telemetry
        telemetry.addData("Target Velocity", targetVelocity);
        telemetry.addData("Measured Velocity", launcher.getLauncherMotor().getEncoderVelocity().into(RotationsPerMinute));

        telemetry.addData("kV", kV);
        telemetry.addData("kS", kS);

        telemetry.update();
    }
}