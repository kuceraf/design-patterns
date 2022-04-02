package behavioural.visitor.reflective

/**
 *  Better than intrusive visitor, but use reflection
 *  and do not enforce to cover each case (we can forget and branch in when class check)
 */

class ReflectiveVisitor {
}

object ExpressionPrinter {
    fun print(expression: Expression, sb: StringBuilder) {
        when (expression) {
            is DoubleExpression -> {
                sb.append(expression.value)
            }
            is AdditionExpression -> {
                sb.append("(")
                ExpressionPrinter.print(expression.left, sb)
                sb.append("+")
                ExpressionPrinter.print(expression.right, sb)
                sb.append(")")
            }
        }
    }
}

abstract class Expression {
}

class DoubleExpression(val value: Double) : Expression() {
}

class AdditionExpression(
    val left: Expression,
    val right: Expression
) : Expression() {
}

fun main() {
    val expression = AdditionExpression(
        DoubleExpression(2.0),
        AdditionExpression(
            DoubleExpression(1.0),
            DoubleExpression(3.0),
        )
    )
    val sb = StringBuilder()

    ExpressionPrinter.print(expression, sb)

    print(sb.toString())
}