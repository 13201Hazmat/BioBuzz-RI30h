package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.api.Paths.through;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

import org.firstinspires.ftc.teamcode.data.Alliance;

public class PathsAndPoses {
    private final PoseFactory poseFactory = PoseFactory.degrees();
    public PoseFactory getPoseFactory(){
        return poseFactory;
    }

    // WE ARE ALWAYS MAKING RED SIDE POSES
    public void mirrorPose(Alliance alliance){
        if (alliance == Alliance.RED) {
            poseFactory.mirrorX(72.0);
            poseFactory.mirrorY(72.0);
        }
    }

    // MAKE POSES HERE
    public final Pose startPos = poseFactory.of(83, 8.65, 270);
    public final Pose leavePos = poseFactory.of(137.37, 30, 180);
    // MAKE PATHS HERE

    public Path startPos_to_leavePos() {
        return through(startPos, leavePos).constant(startPos);
    }

    public Path leavePos_to_startPos(){
        return line(leavePos, startPos).constant(leavePos);
    }
}
