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
        }
    }

    // MAKE POSES HERE
    public final Pose exampleStart = poseFactory.of(72, 0, 0);
    public final Pose exampleEnd = poseFactory.of(72, 72, 0);

    // MAKE PATHS HERE

    public Path exampleStart_to_exampleEnd(){
        return line(exampleStart, exampleEnd).linear(exampleStart, exampleEnd);
    }
}
