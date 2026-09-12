package org.firstinspires.ftc.teamcode.opmodes.auto.runnables;

import static com.pedropathing.ivy.Scheduler.*;

import com.pedropathing.ivy.Scheduler;

import org.firstinspires.ftc.teamcode.opmodes.auto.commands.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.commands.Routines;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;
import org.firstinspires.ftc.teamcode.robot.HazmatRobot;

import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;

@NextAutonomous(name = "Test-RI30H Auto")
public class TestAuto extends NextOpMode {
    private final HazmatRobot hazmatRobot;
    private Routines routines;
    private AutoCommands commands;
    private PathsAndPoses paths;
    public TestAuto(HazmatRobot hazmatRobot){
        super(hazmatRobot);
        this.hazmatRobot = hazmatRobot;
        Scheduler.reset();
    }

    @Override
    public void disabledPeriodic() {
        paths = new PathsAndPoses();
        commands = new AutoCommands(hazmatRobot.getFollower(), paths);
        routines = new Routines(hazmatRobot, paths, commands);

        hazmatRobot.getFollower().setPose(paths.exampleStart);
    }

    @Override
    public void start() {
        schedule(routines.doSomething());
    }

    @Override
    public void periodic() {
        hazmatRobot.getFollower().update();
        Scheduler.execute();
    }
}
