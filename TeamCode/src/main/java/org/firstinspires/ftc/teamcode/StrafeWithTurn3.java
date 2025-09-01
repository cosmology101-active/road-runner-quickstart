package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "StrafeWithTurn3", group = "Autonomous")
public class StrafeWithTurn3 extends LinearOpMode {
    
    @Override
    public void runOpMode() {
        // Initialize your MecanumDrive (replace with your actual drive class)
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        
        // Build the action sequence
        Action trajectoryAction = drive.actionBuilder(drive.pose)
                .strafeToLinearHeading(new Vector2d(-80, 0), 0) // Move 80 inches left
                .turn(Math.toRadians(-90))                      // Turn 90 degrees right
                .lineToY(20)                                    // Move forward 20 inches
                .build();
        
        waitForStart();
        
        if (opModeIsActive()) {
            // Execute the action sequence
            Actions.runBlocking(trajectoryAction);
        }
    }
}

// Alternative approach with separate actions for more control:
/*
@Autonomous(name = "StrafeWithTurn3Alt", group = "Autonomous")
public class StrafeWithTurn3Alt extends LinearOpMode {
    
    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        
        waitForStart();
        
        if (opModeIsActive()) {
            // Action 1: Strafe left 80 inches
            Action strafeAction = drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(new Vector2d(-80, 0), 0)
                    .build();
            Actions.runBlocking(strafeAction);
            
            // Action 2: Turn 90 degrees right
            Action turnAction = drive.actionBuilder(drive.pose)
                    .turn(Math.toRadians(-90))
                    .build();
            Actions.runBlocking(turnAction);
            
            // Action 3: Move forward 20 inches
            Action forwardAction = drive.actionBuilder(drive.pose)
                    .lineToY(drive.pose.position.y + 20)
                    .build();
            Actions.runBlocking(forwardAction);
        }
    }
}
*/
