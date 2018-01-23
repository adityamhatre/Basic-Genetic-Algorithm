import java.util.*

/**
 * Created by Aditya on January 23, 2018.
 */
class Population(populationSize: Int, private val target: String, mutationRate: Float) {
    var generations = 0
    private val population: Array<DNA> = Array(populationSize) {
        DNA(target.length, mutationRate)
    }

    private val matingPool = ArrayList<DNA>()

    init {
        calculateFitness()
    }

    fun mate() {
        matingPool.clear()

        val maxFitness = population.maxBy { it.fitness }!!.fitness
        population.forEach {
            val normalizedFitness = (it.fitness - 0) / (maxFitness - 0)
            val n = (normalizedFitness * 100).toInt()
            for (i in 0 until n) matingPool.add(it)
        }
    }

    fun generateNewPopulation() {
        for (i in 0 until population.size) {
            val father = matingPool[Random(System.nanoTime()).nextInt(matingPool.size)]
            val mother = matingPool[Random(System.nanoTime()).nextInt(matingPool.size)]
            val child = father.crossover(mother)
            population[i] = child
        }
        generations++
    }

    fun calculateFitness() {
        population.forEach { it.calculateFitness(this.target) }
    }

    fun getBestFromPopulation() = population.maxBy { it.fitness }!!.genes.joinToString("")

    fun everyone(): String {
        var string = ""
        population.forEach { string += it.genes.joinToString("") + "\n" }
        return string
    }

    fun finished() = population.any { it.genes.joinToString("") == target }
}