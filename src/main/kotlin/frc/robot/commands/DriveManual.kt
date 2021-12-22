package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.Drivebase

class DriveManual(drivebase: Drivebase) : CommandBase() {
    private val drivebase: Drivebase

    init {
        this.drivebase = drivebase
        addRequirements(this.drivebase)
    }

    override fun initialize() {}
    override fun execute() {}
    override fun end(interrupted: Boolean) {
        drivebase.drive(0.0, 0.0)
    }

    override fun isFinished(): Boolean {
        return true
    }
}