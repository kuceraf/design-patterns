# Visitor

### Allows adding extra behaviors to entire hierarchies of classes.

A pattern where a component (visitor) is allowed to traverse the entire inheritance hierarchy. Implemented by
propagating a single `visit()` method throughout the entire hierarchy.

### Motivation:

* Need to define a new operation on an entire class hierarchy
    * e.g. make a document model printable to HTML/Markdown)
* Do not want to keep modifying every class in the hierarchy
* Need access to **non-common** aspects of classes in the hierarchy
* Create an external component to handle rendering
    * But avoid type checks

### Intrusive visitor (ani-pattern)
breaks open-close and single responsibility principle. It is about adding common method in parent class and force each
child to implement it.

```Kotlin
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
```

### Reflection visitor
Better than intrusive visitor but use reflection

### Double dispatch visitor
It is classical implementation of visitor design pattern.
By propagating `accept(visitor: MyVisitor)` method through entire class hierarchy,
we can extend the hierarchy by any numbers of visitors. `MyVisitor` explicitly requires that every single element of the hierarchy should be visitable.
