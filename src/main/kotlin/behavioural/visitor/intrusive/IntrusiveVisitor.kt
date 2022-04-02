package behavioural.visitor.intrusive

/**
 *  Intrusive visitor is anti pattern !!!
 */

class IntrusiveVisitor {
}

abstract class Expression {
    abstract fun print(sb: StringBuilder)
}

class DoubleExpression(private val value: Double) : Expression() {
    override fun print(sb: StringBuilder) {
        sb.append(value)
    }
}

class AdditionExpression(
    private val left: Expression,
    private val right: Expression
) : Expression() {
    override fun print(sb: StringBuilder) {
        sb.append("( ")
        left.print(sb)
        sb.append(" + ")
        right.print(sb)
        sb.append(" )")
    }
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
    expression.print(sb)
    println(sb)
}