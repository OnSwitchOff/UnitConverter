package converter

import java.util.*
enum class MeasureUnit (val nameList: MutableList<String>, val k: Double) {
    Meter(mutableListOf("m", "meter", "meters"), 1.0),
    Kilometer(mutableListOf("km", "kilometer","kilometers"), 1000.0),
    Centimeter(mutableListOf("cm", "centimeter", "centimeters"), 0.01),
    Millimeter(mutableListOf("mm", "millimeter", "millimeters"), 0.001),
    Mile(mutableListOf("mi", "mile", "miles"), 1609.35),
    Yard(mutableListOf("yd", "yard", "yards"), 0.9144),
    Feet(mutableListOf("ft", "foot", "feet"), 0.3048),
    Inches(mutableListOf("in", "inch", "inches"), 0.0254);
    fun toMeter(v: Double): Double = v * this.k
}


fun main() {
    println("Enter a number and a measure of length:")
    val input = readLine()!!.lowercase(Locale.getDefault()).split(" ")
    val m = MeasureUnit.values().filter { it.nameList.contains(input[1]) }
    if (m.isEmpty()) {
        println("Wrong input. Unknown unit ${input[1]}")
    } else {
        try {
            val v1 = input[0].toDouble()
            val m1: String = if (v1 == 1.0) {
                m[0].nameList[1]
            } else {
                m[0].nameList[2]
            }
            val v2 = m[0].toMeter(v1)
            val m2: String = if (v2 == 1.0) {
                MeasureUnit.Meter.nameList[1]
            } else {
                MeasureUnit.Meter.nameList[2]
            }
            println("$v1 $m1 is $v2 $m2")
        } catch (e: Exception){
            println("Wrong input")
        }
    }
}



