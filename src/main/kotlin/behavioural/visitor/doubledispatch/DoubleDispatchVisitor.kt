package behavioural.visitor.doubledispatch

/**
 * Classical implementation of visitor design pattern
 */
class DoubleDispatchVisitor

// visitor implementation
interface ExpressionVisitor {
    fun visit(expression: DoubleExpression)
    fun visit(expression: AdditionExpression)
}

class PrintingVisitor : ExpressionVisitor {
    private val sb: StringBuilder = StringBuilder()
    override fun visit(expression: DoubleExpression) {
        sb.append(expression.value)
    }

    override fun visit(expression: AdditionExpression) {
        sb.append("(")
        expression.left.accept(this)
        sb.append("+")
        expression.right.accept(this)
        sb.append(")")
    }

    fun print(): String {
        return sb.toString()
    }
}

class CalculationVisitor: ExpressionVisitor {
    var result: Double = 0.0
    override fun visit(expression: DoubleExpression) {
        result = expression.value
    }

    override fun visit(expression: AdditionExpression) {
        expression.left.accept(this)
        val leftResult = result
        expression.right.accept(this)
        val rightResult = result
        result = leftResult + rightResult
    }

}

// class hierarchy
abstract class Expression {
    abstract fun accept(visitor: ExpressionVisitor)
}

class DoubleExpression(val value: Double) : Expression() {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

class AdditionExpression(val left: Expression, val right: Expression) : Expression() {
    override fun accept(visitor: ExpressionVisitor) {
        visitor.visit(this)
    }
}

// usage
fun main() {
    val expression = AdditionExpression(
        DoubleExpression(2.0),
        AdditionExpression(
            DoubleExpression(1.0),
            DoubleExpression(3.0)
        )
    )
    val printingVisitor = PrintingVisitor()
    printingVisitor.visit(expression)
    val calculationVisitor = CalculationVisitor()
    calculationVisitor.visit(expression)

    // (2.0+(1.0+3.0)) = 6
    println(
        "${printingVisitor.print()} = ${calculationVisitor.result}"
    )
}