import java.io.File

fun convertBinaryToDecimal(num: Long): Int {
    var num = num; var decimalNumber = 0 ;var i = 0 ;var remainder: Long
    while (num.toInt() != 0) { remainder = num % 10; num /= 10 ;decimalNumber += (remainder * Math.pow(2.0, i.toDouble())).toInt() ;++i }
    return decimalNumber
}

fun main() {

    var file: File = File("C:\\Users\\Ricar\\Desktop\\Nueva carpeta\\input.txt")


    //First Part

    val Zero = mutableListOf<Int>()
    val One = mutableListOf<Int>()
    var position: Int = 0

    //I save at least one line of the file for using it later with .length
    val size = mutableListOf<String>()
    file.forEachLine { size.add(it) }

    var gammaRate: String = ""
    var epsilonRate: String = ""

    //Calculating gamma rate
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
    position = 0 ; Zero.clear(); One.clear()

    //Now we get the result by multiplying gammaRate and epsilonRate's decimal numbers
    println(convertBinaryToDecimal(gammaRate.toLong()) * convertBinaryToDecimal(epsilonRate.toLong()))


    //Second Part
    val values = mutableListOf<String>()
    file.forEachLine { values.add(it) }
    var OneB: Boolean = false
    var ZeroB: Boolean = false
    var oxygenRating: String = ""
    var co2Rating: String = ""
    var toBeRemoved = mutableListOf<Int>()

    //Oxygen generator rating

    /*
    for (i in 0 until values.size){
        values.forEach { if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0) } }
        if (One.size >= Zero.size) {oxygenRating = oxygenRating + "1"; OneB = true} else {oxygenRating = oxygenRating + "0"}
        if (OneB == true) {values.forEach {if (it.get(0).toString()=="0") {toBeRemoved.add(values.indexOf(it))}}} else {values.forEach {if (it.get(0).toString()=="1") {toBeRemoved.add(values.indexOf(it))}}}
        for (i in 0 until toBeRemoved.size) {values.removeAt(toBeRemoved[i])}
        Zero.clear(); One.clear(); OneB = false; toBeRemoved.clear()
        position++
    }

     */

    for (i in 0 until values.size){
        try { file.forEachLine { if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0)}}} catch(e:StringIndexOutOfBoundsException){}
        if (One.size >= Zero.size) {oxygenRating = oxygenRating + "1"; OneB = true} else {oxygenRating = oxygenRating + "0"}
        try {if (OneB == true) {for (i in 0 until values.size) {if (values[i].get(0).toString()=="0") {values.removeAt(i)}}} else {for (i in 0 until values.size) {if (values[i].get(0).toString()=="1") {values.removeAt(i)}}}} catch (e:IndexOutOfBoundsException){}
        Zero.clear(); One.clear(); OneB = false
        position++
    }
    oxygenRating = oxygenRating.removeRange(11,1000)


    //We reset the values list
    values.clear(); file.forEachLine { values.add(it) }

    /*
    //CO2 scrubber rating
    values.forEach {
        if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0) }
        if (Zero.size >= One.size) {co2Rating = co2Rating + "1"; ZeroB = true} else {co2Rating = co2Rating + "0"}
        if (ZeroB == true) {values.forEach {if (it.get(0).toString()=="0") {values.remove(it)}}} else {values.forEach {if (it.get(0).toString()=="1") {values.remove(it)}}}
        Zero.clear(); One.clear(); ZeroB = false
        position++
    }


 */

    for (i in 0 until values.size){
        try { file.forEachLine { if (it.get(position).toString() == "1") { One.add(1) } else { Zero.add(0)}}} catch(e:StringIndexOutOfBoundsException){}
        if (One.size < Zero.size && One.size != Zero.size) {co2Rating = co2Rating + "1" } else {co2Rating = co2Rating + "0" ; ZeroB = true }
        try {if (ZeroB == true) {for (i in 0 until values.size) {if (values[i].get(0).toString()=="0") {values.removeAt(i)}}} else {for (i in 0 until values.size) {if (values[i].get(0).toString()=="1") {values.removeAt(i)}}}} catch (e:IndexOutOfBoundsException){}
        Zero.clear(); Zero.clear(); ZeroB = false
        position++
    }

    co2Rating = co2Rating.removeRange(11,1000)
    println(oxygenRating)
    println(co2Rating)
    println(convertBinaryToDecimal(oxygenRating.toLong()) * convertBinaryToDecimal(co2Rating.toLong()))



}