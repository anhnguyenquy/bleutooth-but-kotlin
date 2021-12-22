package frc.robot

object CustomFunctions {

    @JvmStatic
    fun setTimeout(runnable: Runnable, delay: Int) {
        Thread {
            try {
                Thread.sleep(delay.toLong())
                runnable.run()
            } catch (e: Exception) {
                System.err.println(e)
            }
        }.start()
    }

    @JvmStatic
    fun map(s: Double, a1: Double, a2: Double, b1: Double, b2: Double): Double {
        return b1 + (s - a1) * (b2 - b1) / (a2 - a1)
    }
}