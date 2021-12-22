package frc.robot.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import frc.robot.subsystems.*
import frc.robot.Constants.Speed
import frc.robot.Constants.Time
import frc.robot.CustomFunctions

class Forward(drivebase: Drivebase, meters: Double) : CommandBase() {
    private val drivebase: Drivebase
    private val meters: Double

    init {
        this.drivebase = drivebase
        this.meters = meters
        addRequirements(this.drivebase)
    }

    override fun initialize() {}
    override fun execute() {
        drivebase.drive(Speed.defaultMoveSpeed, Speed.defaultMoveSpeed)
        CustomFunctions.setTimeout({ end(true) }, (Time.timeToMove1MeterAtDefaultSpeed * meters).toInt())
    }

    override fun end(interrupted: Boolean) {
        drivebase.drive(0.0, 0.0)
    }

    override fun isFinished(): Boolean {
        return true
    }
}