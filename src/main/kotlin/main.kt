
import br.com.utils.EMPTY_ERROR_EXIT
import br.com.utils.CANCEL_EXIT
import java.util.Scanner

fun main() {

    val scanner = Scanner(System.`in`)
    do {

        val (projectName, packageName) = setup(scanner)
        println("Create project $projectName package $packageName? Yes/No [N]")
        val choose = scanner.nextLine()
        if (choose.isEmpty() || choose.equals("Y", false)) {
            break
        } else {
            println("Canceled by user.")
            System.exit(CANCEL_EXIT)
        }

    } while(true)

    println("Creating project")


}

private fun setup(scanner: Scanner): List<String> {

    println("Initializing Gradle Kotlin Project.")

    println("Type project nema [unanmed]:")
    var projectName = scanner.nextLine()
    if (projectName.isNullOrEmpty()) {
        projectName = "unanmed"
    }

    println("Type package nema [me.project]:")
    var packageName = scanner.nextLine()
    if (packageName.isNullOrEmpty()) {
        packageName = "me.project"
    }

    return listOf<String>(projectName, packageName)

}