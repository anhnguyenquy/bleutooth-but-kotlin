package frc.robot.commands

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import frc.robot.subsystems.Drivebase

class Auto(drive: Drivebase?) : ParallelCommandGroup() {
    init {
        addCommands()
    }
}