package org.firstinspires.ftc.teamcode.opmodes.auto.runnables;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.Routines;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;

@NextAutonomous(name = "Auto")
public class Auto extends NextOpMode {
    private final HazmatRobot hazmatRobot;
    private final Routines routines;
    private final AutoCommands commands;
    private final PathsAndPoses paths;
    private Command selectedRoutine;

    public Auto(HazmatRobot hazmatRobot) {
        super(hazmatRobot);
        this.hazmatRobot = hazmatRobot;
        Scheduler.reset();

        paths = new PathsAndPoses();
        commands = new AutoCommands(hazmatRobot.getFollower(), paths);
        routines = new Routines(hazmatRobot, paths, commands);

        hazmatRobot.getFollower().setPose(paths.startPos);
    }

    @Override
    public void start() {
        telemetry.addLine("Pick an auto");
        telemetry.addLine("Right bumper for LeaveAuto");
        telemetry.addLine("Left bumper for PreloadAuto");
        telemetry.addLine("Cross for CycleAuto");
        if(gamepad1.rightBumperWasPressed()){
            selectedRoutine = routines.leaveAuto();
        }
        else if(gamepad1.leftBumperWasPressed()){
            selectedRoutine = routines.preloadAuto();
        }
        else if(gamepad1.crossWasPressed()){
            selectedRoutine = routines.fullCycleAuto();
        }
        schedule(selectedRoutine);
    }

    @Override
    public void periodic() {
        hazmatRobot.getFollower().update();
        Scheduler.execute();
    }
}
