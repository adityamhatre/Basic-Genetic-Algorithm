import java.util.*

/**
 * Created by Aditya on January 23, 2018.
 */
class DNA(lengthOfTarget: Int, private val mutationRate: Float) {
    var genes: CharArray = CharArray(lengthOfTarget)
    var fitness: Float = 0.0f

    init {
        for (i in 0 until genes.size) {
            genes[i] = (32 + Random(System.nanoTime()).nextInt(128 - 32)).toChar()
        }
    }

    fun calculateFitness(target: String) {
        val score = (0 until genes.size).count { genes[it] == target.toCharArray()[it] }
        fitness = Math.pow((score.toFloat() / target.length.toFloat()).toDouble(),2.0).toFloat()
    }

    fun crossover(partner: DNA): DNA {
        val child = DNA(genes.size, mutationRate)
        val crossoverPoint = Random(System.nanoTime()).nextInt(genes.size)
        for (i in 0 until crossoverPoint) {
            child.genes[i] = genes[i]
        }
        for (i in crossoverPoint until genes.size) {
            child.genes[i] = partner.genes[i]
        }
        child.mutate(mutationRate)
        return child
    }

    private fun mutate(mutationRate: Float) {
        for (i in 0 until genes.size) {
            if (Random().nextFloat() < mutationRate) {
                genes[i] = (32 + Random(System.nanoTime()).nextInt(128 - 32)).toChar()
            }
        }
    }
}