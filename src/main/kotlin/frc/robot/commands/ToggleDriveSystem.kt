package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.RobotContainer

class ToggleDriveSystem : CommandBase() {
    override fun initialize() {
        RobotContainer.useLegacy = !RobotContainer.useLegacy
    }

    override fun execute() {}
    override fun end(interrupted: Boolean) {}
    override fun isFinished(): Boolean {
        return false
    }
}