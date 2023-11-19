package com.example.calc

import java.util.Stack

class ExpressionSolver() {

    fun evaluateExpression(expression: String, x: Double?, y: Double?): Double {
        val operators = Stack<Char>()
        val values = Stack<Double>()

        var i = 0
        while (i < expression.length) {
            if (expression[i] in '0'..'9' || expression[i] == '.') {
                var value = ""
                while (i < expression.length && (expression[i] in '0'..'9' || expression[i] == '.')) {
                    value += expression[i]
                    i++
                }
                values.push(value.toDouble())
            } else if (expression[i] == 'x') {
                values.push(x)
                i++
            } else if (expression[i] == 'y') {
                values.push(y)
                i++
            } else if (expression[i] == '(') {
                operators.push(expression[i])
                i++
            } else if (expression[i] == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
                }
                operators.pop() // Удаляем открывающую скобку из стека
                i++
            } else if (expression[i] in "+/-*") {
                while (!operators.isEmpty() && hasPrecedence(expression[i], operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
                }
                operators.push(expression[i])
                i++
            }
        }

        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()))
        }

        return values.pop()
    }


    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') {
            return false
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '*' || op2 == '/')) {
            return false
        }
        if ((op1 == '+' || op1 == '-') && (op2 == '+' || op2 == '-')) {
            return false
        }
        return true
    }


    private fun applyOperator(operator: Char, b: Double, a: Double): Double {
        return when (operator) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> a / b
            else -> throw IllegalArgumentException("Неподдерживаемый оператор: $operator")
        }
    }
}
