package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "StrafeWithTurn3", group = "Autonomous")
public class StrafeWithTurn3 extends LinearOpMode {
    
    // You need to replace this with your actual RoadRunner drive class
    // Common names: MecanumDrive, SampleMecanumDrive, or whatever you named it
    private Object drive; // Replace 'Object' with your actual drive class
    
    @Override
    public void runOpMode() {
        // Initialize your drive system
        // Uncomment and modify this line based on your actual drive class:
        // drive = new YourDriveClassName(hardwareMap, new Pose2d(0, 0, 0));
        
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
        // PLACEHOLDER CODE - Replace with your RoadRunner implementation
        // This is a template showing the movement sequence you want:
        
        telemetry.addData("Step 1", "Strafing left 80 inches");
        telemetry.update();
        // TODO: Add your RoadRunner strafe left command here
        sleep(3000); // Placeholder delay
        
        telemetry.addData("Step 2", "Turning right 90 degrees");
        telemetry.update();
        // TODO: Add your RoadRunner turn right command here
        sleep(2000); // Placeholder delay
        
        telemetry.addData("Step 3", "Moving forward 20 inches");
        telemetry.update();
        // TODO: Add your RoadRunner move forward command here
        sleep(2000); // Placeholder delay
    }
}

/* 
TO FIX THIS CODE, YOU NEED TO:

1. First, make sure RoadRunner 1.0.1 is properly installed in your project.
   Check your build.gradle files for the RoadRunner dependency.

2. Find your drive class file (usually in TeamCode/src/main/java/org/firstinspires/ftc/teamcode/drive/)
   Common names: MecanumDrive.java, SampleMecanumDrive.java, etc.

3. Replace the 'Object drive;' declaration with your actual drive class, for example:
   private MecanumDrive drive;

4. Replace the commented initialization line with:
   drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

5. Replace the TODO sections with actual RoadRunner commands, for example:
   
   // Strafe left 80 inches:
   Trajectory strafeTrajectory = drive.trajectoryBuilder(drive.getPoseEstimate())
           .strafeLeft(80)
           .build();
   drive.followTrajectory(strafeTrajectory);
   
   // Turn right 90 degrees:
   drive.turn(Math.toRadians(-90));
   
   // Move forward 20 inches:
   Trajectory forwardTrajectory = drive.trajectoryBuilder(drive.getPoseEstimate())
           .forward(20)
           .build();
   drive.followTrajectory(forwardTrajectory);

6. Make sure you have the proper imports based on your RoadRunner version:
   import com.acmerobotics.roadrunner.geometry.Pose2d;
   import com.acmerobotics.roadrunner.trajectory.Trajectory;
   import org.firstinspires.ftc.teamcode.drive.YourDriveClassName;
*/
