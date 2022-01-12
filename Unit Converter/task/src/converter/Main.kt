package converter

import java.util.*

fun main() {
    println("Enter a number and a measure:")
    val input = readLine()!!.lowercase(Locale.getDefault()).split(" ")
    when (input[1]) {
        "km", "kilometer", "kilometers" -> {
            try {
                val v = input[0].toInt()
                var m = "kilometer"
                if (v != 1) m += "s"
                println("$v $m is ${v * 1000} meters")
            } catch (e: Exception){
                println("Wrong input")
            }
        }
        else -> println("Wrong input")
    }
}
