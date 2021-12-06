import java.io.File

fun convertBinaryToDecimal(num: Long): Int {
    var num = num; var decimalNumber = 0 ;var i = 0 ;var remainder: Long
    while (num.toInt() != 0) { remainder = num % 10; num /= 10 ;decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt() ;++i }
    return decimalNumber
}

fun main() {

    var file: File = File("C:\\Users\\Ricar\\Desktop\\Nueva carpeta\\input.txt")


    //Calculating gamma rate

    val Zero = mutableListOf<Int>()
    val One = mutableListOf<Int>()
    var position: Int = 0

    //I save at least one line of the file for using it later with .length
    val size = mutableListOf<String>()
    file.forEachLine { size.add(it) }

    var gammaRate: String = ""
    var epsilonRate: String = ""

    for (i in 0 until size[0].length) {
        file.forEachLine { if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0) } }
        if (Zero.size > One.size) {gammaRate = gammaRate + "0"} else {gammaRate = gammaRate + "1"}
        Zero.clear(); One.clear()
        position++
    }

    //We clear the Zero and One lists before continuing. Also we set the position to 0
    Zero.clear(); One.clear(); position = 0


    //Now we calculate epsilon rate doing the same but switching ones and zeros at the last if
    for (i in 0 until size[0].length) {
        file.forEachLine { if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0) } }
        if (Zero.size > One.size) {epsilonRate = epsilonRate + "1"} else {epsilonRate = epsilonRate + "0"}
        Zero.clear(); One.clear()
        position++
    }

    //Now we get the result by multiplying gammaRate and epsilonRate's decimal numbers
    println(convertBinaryToDecimal(gammaRate.toLong()) * convertBinaryToDecimal(epsilonRate.toLong()))


}