package org.firstinspires.ftc.teamcode.opmodes.auto.commands;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;

public class AutoCommands {
    private final Follower follower;
    private final PathsAndPoses paths;

    public AutoCommands(Follower follower, PathsAndPoses paths){
        this.follower = follower;
        this.paths = paths;
    }
    public CommandBuilder runPath(Path path){
        return follow(follower, path);
    }
}
