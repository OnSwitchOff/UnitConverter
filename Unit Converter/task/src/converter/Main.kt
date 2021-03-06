package converter

import java.util.*
enum class TemperatureMeasureUnit(val nameList: MutableList<String>) {
    Celsius(mutableListOf("c", "degree Celsius", "degrees Celsius", "celsius", "dc")),
    Fahrenheit(mutableListOf("f", "degree Fahrenheit", "degrees Fahrenheit", "fahrenheit", "df")),
    Kelvins(mutableListOf("k", "kelvin", "kelvins"));
    companion object {
        fun Convert(v: Double, f1: TemperatureMeasureUnit,f2: TemperatureMeasureUnit): Double {
            return when {
                f1 == f2 -> v
                f1 == Celsius && f2 == Fahrenheit -> (v * 9 / 5) + 32
                f2 == Celsius && f1 == Fahrenheit -> (v - 32) * 5 / 9
                f1 == Kelvins && f2 == Fahrenheit -> (v * 9 / 5) - 459.67
                f2 == Kelvins && f1 == Fahrenheit -> (v + 459.67) * 5 / 9
                f1 == Celsius && f2 == Kelvins -> v + 273.15
                f2 == Celsius && f1 == Kelvins -> v - 273.15
                else -> 0.0
            }
        }
        fun ShowConvertResult(v: Double, f1: TemperatureMeasureUnit,f2: TemperatureMeasureUnit) {
            val m1: String = if (v == 1.0) {
                f1.nameList[1]
            } else {
                f1.nameList[2]
            }
            val v2 = Convert(v, f1, f2)
            val m2: String = if (v2 == 1.0) {
                f2.nameList[1]
            } else {
                f2.nameList[2]
            }
            println("$v $m1 is $v2 $m2")
        }
    }
}

enum class LengthMeasureUnit (val nameList: MutableList<String>, val k: Double) {
    Meter(mutableListOf("m", "meter", "meters"), 1.0),
    Kilometer(mutableListOf("km", "kilometer","kilometers"), 1000.0),
    Centimeter(mutableListOf("cm", "centimeter", "centimeters"), 0.01),
    Millimeter(mutableListOf("mm", "millimeter", "millimeters"), 0.001),
    Mile(mutableListOf("mi", "mile", "miles"), 1609.35),
    Yard(mutableListOf("yd", "yard", "yards"), 0.9144),
    Feet(mutableListOf("ft", "foot", "feet"), 0.3048),
    Inches(mutableListOf("in", "inch", "inches"), 0.0254);
    fun ConvertTo(v: Double, u: LengthMeasureUnit): Double = v * this.k / u.k
    fun ShowConvertResult(v: Double, u: LengthMeasureUnit) {
        val m1: String = if (v == 1.0) {
            this.nameList[1]
        } else {
            this.nameList[2]
        }
        val v2 = this.ConvertTo(v, u)
        val m2: String = if (v2 == 1.0) {
            u.nameList[1]
        } else {
            u.nameList[2]
        }
        println("$v $m1 is $v2 $m2")
    }
}

enum class WeightMeasureUnit (val nameList: MutableList<String>, val k: Double) {
    Gram(mutableListOf("g", "gram", "grams"), 1.0),
    Kilogram(mutableListOf("kg", "kilogram", "kilograms"), 1000.0),
    Milligram(mutableListOf("mg", "milligram", "milligrams"), 0.001),
    Pound(mutableListOf("lb", "pound", "pounds"), 453.592),
    Ounce(mutableListOf("oz", "ounce", "ounces"), 28.3495);
    fun ConvertTo(v: Double, u: WeightMeasureUnit): Double = v * this.k / u.k
    fun ShowConvertResult(v: Double, u: WeightMeasureUnit) {
        val m1: String = if (v == 1.0) {
            this.nameList[1]
        } else {
            this.nameList[2]
        }
        val v2 = this.ConvertTo(v, u)
        val m2: String = if (v2 == 1.0) {
            u.nameList[1]
        } else {
            u.nameList[2]
        }
        println("$v $m1 is $v2 $m2")
    }
}


fun main() {
    while (true) {
        println("Enter what you want to convert (or exit): ")
        val input = readLine()!!.lowercase(Locale.getDefault())
        when (input) {
            "exit" -> return
            else -> {
                try {
                    val delim = if (input.contains(" to ")) " to " else if (input.contains(" in ")) " in " else " convertto "
                    val tmp = input.split(delim)
                    val p = mutableListOf<String>()
                    tmp[0].trim().split("\\s".toRegex(), 2).forEach { p.add(it) }
                    p.add(delim)
                    p.add(tmp[1].trim())
                    val v = p[0].toDouble()
                    val lFrom = LengthMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[1])  }
                    val lTo = LengthMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[3]) }
                    val wFrom = WeightMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[1]) }
                    val wTo = WeightMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[3]) }
                    val tFrom = TemperatureMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[1]) }
                    val tTo = TemperatureMeasureUnit.values().filter { it.nameList.map { it.lowercase(Locale.getDefault()) }.contains(p[3]) }
                    if (lFrom.isNotEmpty() && lTo.isNotEmpty()) {
                        if (v < 0) {
                            println("Length shouldn't be negative")
                        } else {
                            lFrom[0].ShowConvertResult(v, lTo[0])
                        }
                    } else if (wFrom.isNotEmpty() && wTo.isNotEmpty()) {
                        if (v < 0) {
                            println("Weight shouldn't be negative")
                        } else {
                            wFrom[0].ShowConvertResult(v, wTo[0])
                        }
                    } else if (tFrom.isNotEmpty() && tTo.isNotEmpty())  {
                        TemperatureMeasureUnit.ShowConvertResult(v, tFrom[0], tTo[0])
                    } else {
                        val from = when {
                            lFrom.isNotEmpty() -> lFrom[0].nameList[2]
                            wFrom.isNotEmpty() -> wFrom[0].nameList[2]
                            tFrom.isNotEmpty() -> tFrom[0].nameList[2]
                            else -> "???"
                        }
                        val to = when {
                            lTo.isNotEmpty() -> lTo[0].nameList[2]
                            wTo.isNotEmpty() -> wTo[0].nameList[2]
                            tTo.isNotEmpty() -> tTo[0].nameList[2]
                            else -> "???"
                        }
                        println("Conversion from $from to $to is impossible")
                    }
                } catch (e: Exception) {
                    println("Parse error")
                }
            }
        }
        println()
    }
}



