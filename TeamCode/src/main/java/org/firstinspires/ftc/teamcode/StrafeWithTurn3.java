package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Import your drive class - replace with your actual drive class name
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name = "StrafeWithTurn3", group = "Autonomous")
public class StrafeWithTurn3 extends LinearOpMode {
    
    @Override
    public void runOpMode() {
        // Initialize your drive class (replace SampleMecanumDrive with your actual class)
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        
        // Starting pose (assuming robot starts at origin facing forward)
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);
        
        waitForStart();
        
        if (opModeIsActive()) {
            executeMovement(drive);
        }
    }
    
    public void executeMovement(SampleMecanumDrive drive) {
        // Build trajectory: Move 80 inches from right to left (negative X direction)
        // then turn 90 degrees right and move forward 20 inches
        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d(0, 0, 0))
                .strafeLeft(80)  // Move 80 inches to the left (from robot's perspective)
                .build();
        
        // Execute the strafe movement
        drive.followTrajectory(trajectory);
        
        // Get the current pose after the strafe
        Pose2d currentPose = drive.getPoseEstimate();
        
        // Turn 90 degrees to the right (clockwise)
        drive.turn(Math.toRadians(-90));
        
        // Update pose estimate after turn
        currentPose = drive.getPoseEstimate();
        
        // Move forward 20 inches
        Trajectory forwardTrajectory = drive.trajectoryBuilder(currentPose)
                .forward(20)
                .build();
        
        drive.followTrajectory(forwardTrajectory);
    }
    
    // Alternative approach using a single trajectory with spline paths:
    public void executeMovementSingleTrajectory(SampleMecanumDrive drive) {
        Pose2d startPose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(startPose);
        
        Trajectory trajectory = drive.trajectoryBuilder(startPose)
                .strafeLeft(80)
                .splineToConstantHeading(new Vector2d(-80, 20), Math.toRadians(-90))
                .build();
        
        drive.followTrajectory(trajectory);
    }
}
