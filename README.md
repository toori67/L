# L

## Simple set notation in Kotlin
Inspired by [medium post]([https://medium.com/@farolfo/list-comprehensions-in-java-230e6a9d3a0c]())

## Usage

```kotlin
val intList1 = listOf(1, 3, 2)
val intList2 = listOf(4, 6, 5)
val intList3 = listOf(11, 13, 12)

val l1 = intList1.L().cond { x -> x<=2 }.get()
// [2, 3]
val l2 = instList1.L(CartesianZipper()).with(intList2).cond { x, y -> x * 2 == y}.get()
// [(2, 4), (3, 6)]
val l3 = intList1.L(CartesianZipper()).with(intList2).with(intList3)
		.cond { _, y, z -> y * 2 == z }
		.cond { x, _, _ -> x > 2 }
		.get()
// [(3, 6, 12)]
```

