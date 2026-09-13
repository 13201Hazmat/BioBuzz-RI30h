package org.firstinspires.ftc.teamcode.opmodes.auto.paths;

import static com.pedropathing.api.Paths.line;

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
        if(alliance == Alliance.BLUE){
            poseFactory.mirrorY(72.0);
            poseFactory.mirrorX(72.0);
        }
    }

    // MAKE POSES HERE
    public final Pose startPos = poseFactory.of(82.5, 133, 270);
    public final Pose leavePos = poseFactory.of(130, 127, 0);
    // MAKE PATHS HERE

    public Path startPos_to_leavePos() {
        return line(startPos, leavePos).linear(startPos, leavePos);
    }

    public Path leavePos_to_startPos(){
        return line(leavePos, startPos).linear(leavePos, startPos);
    }
}
