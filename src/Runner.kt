import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * Created by Aditya on January 23, 2018.
 */
val window = Window()
fun main(args: Array<String>) {
    var target = "To be or not to be."
    val jFrame = JFrame()
    jFrame.title = "Genetic Algorithm"
    jFrame.contentPane = window.contentPane
    jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    jFrame.setSize(800, 600)
    jFrame.isResizable = false
    jFrame.isVisible = true
    jFrame.setLocationRelativeTo(null)
    window.evolveButton.addActionListener {
        if(!window.input.text.isEmpty()){
            target = window.input.text
            Thread{
                evolve(target)
            }.start()
        }
    }

    evolve(target)
}
fun evolve(target: String){
    val populationSize = target.length * 100
    val mutationRate = 0.01f
    val population = Population(populationSize, target, mutationRate)
    while (true) {
        window.evolveButton.text = "Evolving"
        window.evolveButton.isEnabled = false
        population.mate()
        population.generateNewPopulation()
        population.calculateFitness()
        println("${population.getBestFromPopulation()} |<- Generation ${population.generations}")
        window.bestSentenceView.text = population.getBestFromPopulation()
        if (population.finished()) {
            window.evolveButton.text = "Evolve"
            window.evolveButton.isEnabled = true
            break
        }
    }
}

