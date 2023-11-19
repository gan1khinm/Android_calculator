//package com.example.calc
//
//
//class Solver(private val expression: String) {
//
//    fun solve(): Double {
//        return evaluateExpression(expression)
//    }
//
//    private fun evaluateExpression(expression: String): Double {
//        val tokens = expression.split(" ")
//        val stack = mutableListOf<Double>()
//
//        for (token in tokens) {
//            if (token.isNumeric()) {
//                stack.add(token.toDouble())
//            } else {
//                val operand2 = stack.removeLast()
//                val operand1 = stack.removeLast()
//                val result = when (token) {
//                    "+" -> operand1 + operand2
//                    "-" -> operand1 - operand2
//                    "*" -> operand1 * operand2
//                    "/" -> operand1 / operand2
//                    else -> throw IllegalArgumentException("Invalid operator")
//                }
//                stack.add(result)
//            }
//        }
//
//        if (stack.size != 1) {
//            throw IllegalArgumentException("Invalid mathematical expression")
//        }
//
//        return stack.first()
//    }
//
//    private fun String.isNumeric(): Boolean {
//        return this.matches("-?\\d+(\\.\\d+)?".toRegex())
//    }
//}
//
//
