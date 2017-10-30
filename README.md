# L

## Simple set notation in Kotlin
Inspired by [medium post]([https://medium.com/@farolfo/list-comprehensions-in-java-230e6a9d3a0c]())

## Usage

### basic usage
![](http://lizzardry.download/3VXVy3/1U75qiD5+)

```kotlin
listOf(1,2,3,4).L().cond { it%2==0 }.get()
```

### with two list
![](http://lizzardry.download/11HiXR/5LSOgp3u+)

```kotlin
val intList1 = listOf(1, 2, 3)
val intList2 = listOf(4, 5, 6)
listOf(1, 2, 3).L(CartesianZipper()).with(listOf(4, 5, 6).cond { x, y -> x * 2 == y }.get()
```
### and many more

```kotlin
val intList1 = listOf(1, 3, 2)
val intList2 = listOf(4, 6, 5)
val intList3 = listOf(11, 13, 12)
val l3 = intList1.L(CartesianZipper()).with(intList2).with(intList3)
		.cond { _, y, z -> y * 2 == z }
		.cond { x, _, _ -> x > 2 }
		.get()
// [(3, 6, 12)]
```
