package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.Drivebase

class DriveStraight(drivebase: Drivebase, v: Double) : CommandBase() {
    private val drivebase: Drivebase
    private val v: Double

    init {
        this.drivebase = drivebase
        this.v = v
        addRequirements(this.drivebase)
    }

    override fun initialize() {}
    override fun execute() {
        drivebase.drive(v, v)
    }

    override fun end(interrupted: Boolean) {
        drivebase.drive(0.0, 0.0)
    }

    override fun isFinished(): Boolean {
        return true
    }
}