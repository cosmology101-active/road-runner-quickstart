package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.drive.ChampionMecanumDrive;

@Autonomous(name = "StrafeWithTurn3", group = "Autonomous")
public class StrafeWithTurn3 extends LinearOpMode {
    
    private ChampionMecanumDrive drive;
    
    @Override
    public void runOpMode() {
        // Initialize your ChampionMecanumDrive
        drive = new ChampionMecanumDrive(hardwareMap);
        
        // Set starting pose
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);
        
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
        waitForStart();
        
        if (opModeIsActive()) {
            executeMovement();
            
            telemetry.addData("Status", "Movement Complete");
            telemetry.update();
        }
    }
    
    public void executeMovement() {
        // Step 1: Strafe left 80 inches
        telemetry.addData("Step 1", "Strafing left 80 inches");
        telemetry.update();
        
        Trajectory strafeTrajectory = drive.trajectoryBuilder(drive.getPoseEstimate())
                .strafeLeft(80)
                .build();
        drive.followTrajectory(strafeTrajectory);
        
        // Step 2: Turn right 90 degrees
        telemetry.addData("Step 2", "Turning right 90 degrees");
        telemetry.update();
        
        drive.turn(Math.toRadians(-90));
        
        // Step 3: Move forward 20 inches
        telemetry.addData("Step 3", "Moving forward 20 inches");
        telemetry.update();
        
        Trajectory forwardTrajectory = drive.trajectoryBuilder(drive.getPoseEstimate())
                .forward(20)
                .build();
        drive.followTrajectory(forwardTrajectory);
    }
    
    // Alternative single trajectory approach:
    public void executeMovementSingleTrajectory() {
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);
        
        Trajectory trajectory = drive.trajectoryBuilder(startPose)
                .strafeLeft(80)
                .splineToConstantHeading(new Vector2d(-80, 20), Math.toRadians(-90))
                .build();
        
        drive.followTrajectory(trajectory);
    }
}
