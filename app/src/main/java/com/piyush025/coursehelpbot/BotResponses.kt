package com.piyush025.coursehelpbot

import com.piyush025.coursehelpbot.Constants.OPEN_CLASS
import com.piyush025.coursehelpbot.Constants.OPEN_SEARCH
import java.lang.Exception
import java.util.*

object BotResponses {
    fun basicResponses(_message: String): String {
        val random = (0..2).random()
        val message = _message.toLowerCase(Locale.getDefault())
        return when {
            message.contains("Hello") -> {
                when (random) {
                    in 0..2 -> "Hello there!"
                    else -> "error"
                }
            }

            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            message.contains("solve") -> {
                val equation: String? = message.substringAfter("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    answer.toString()
                } catch (e: Exception) {
                    "Sorry, I can't solve that"
                }
            }


            (message.contains("services") && message.contains("offer")) || message.contains("help me") -> {
                """
                 I can provide the following services:-
                    1. Tell about any recent announcement by the Professor.
                    2. Tell about any upcoming quiz.
                    3. Directly join the class on Google meet/zoom.
                    4. Provide link to Notes/PPT
                    5. Tell the class timings
                    and many more...
                """.trimIndent()
            }


            message.contains("notes") || message.contains("ppt") -> "https://drive.google.com/drive/folders/1DFaQ33AZNfvnbFJW1_gQYFEtlv5k4G3e?usp=sharing"


            message.contains("class timing") -> {
                """
                    Monday 9:00 AM - 10:00 AM
                    Wednesday 10:00 AM - 11:00 AM
                    Friday 11:00 AM - 12:00 PM
                """.trimIndent()
            }

            message.contains("time") && message.contains("? ") -> Time.timeStamp()

            message.contains("join") && message.contains("class") -> {
                OPEN_CLASS
            }

            //Search on the internet
            message.contains("search") -> {
                OPEN_SEARCH
            }

            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    in 1..2 -> "Pretty good! "
                    else -> "error"
                }
            }
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}