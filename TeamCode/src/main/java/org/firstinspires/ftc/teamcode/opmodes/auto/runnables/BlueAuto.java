package org.firstinspires.ftc.teamcode.opmodes.auto.runnables;

import static com.pedropathing.ivy.Scheduler.schedule;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.Routines;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;

@NextAutonomous(name = "Blue Auto")
public class BlueAuto extends NextOpMode {
    private final HazmatRobot hazmatRobot;
    private final Routines routines;
    private final AutoCommands commands;
    private final PathsAndPoses paths;
    private Command selectedRoutine;
    private Alliance selectedAlliance;

    public BlueAuto(HazmatRobot hazmatRobot) {
        super(hazmatRobot);
        this.hazmatRobot = hazmatRobot;
        Scheduler.reset();

        paths = new PathsAndPoses();
        commands = new AutoCommands(hazmatRobot.getFollower(), paths);
        routines = new Routines(hazmatRobot, paths, commands);

    }

    @Override
    public void start() {
        hazmatRobot.getFollower().setPose(paths.startPos);
        schedule(routines.leaveAuto(Alliance.BLUE));
    }

    @Override
    public void periodic() {
        hazmatRobot.getFollower().update();
        Scheduler.execute();
    }
}
