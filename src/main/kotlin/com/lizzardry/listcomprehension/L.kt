package com.lizzardry.listcomprehension

fun <T> List<T>.toL(): L1<T> {
    return L1(this)
}


class L1<T>(private val x: List<T>){
    fun with(y: List<T>): L2<T> {
        return L2(x, y)
    }

    fun cond(cond:(T) -> (Boolean)): L1<T> {
        return L1(x.filter(cond))
    }
}
class L2<T>(private val x: List<T>,
            private val y: List<T>) {
    fun with(z: List<T>): L3<T> {
        return L3(x, y, z)
    }

    fun cond(cond:(x: T, y: T) -> (Boolean)): L2<T> {
        val condResult =
                x.zip(y).filter { cond(it.first, it.second) }.unzip()
        x.zip(y).map { XY(it) }.filter { cond(it.x, it.y) }
        return L2(condResult.first, condResult.second)
    }

    fun get(): List<Pair<T, T>> {
        return x.zip(y)
    }

    private data
    class XY<out T>(private val p: Pair<T, T>) {
        val x = p.first
        val y = p.second
    }
}

class L3<T>(private val x: List<T>,
            private val y: List<T>,
            private val z: List<T>) {

    fun cond(cond:(x: T, y: T, z: T) -> (Boolean)): L3<T> {
        val condResult =
                x.zip(y).zips(z).map { XYZ(it) }.filter { cond(it.x, it.y, it.z) }.unzip()
        return L3(condResult.first, condResult.second, condResult.third)
    }

    fun get(): List<Triple<T, T, T>> {
        return x.zip(y).zips(z)
    }

    private infix
    fun List<Pair<T,T>>.zips(z: List<T>): List<Triple<T, T, T>> {
        return this.zip(z).map { Triple(it.first.first, it.first.second, it.second) }
    }

    private
    fun List<XYZ<T>>.unzip():Triple<List<T>, List<T>, List<T>> {
        val expectedSize = collectionSizeOrDefault(10)
        val list1 = ArrayList<T>(expectedSize)
        val list2 = ArrayList<T>(expectedSize)
        val list3 = ArrayList<T>(expectedSize)
        for (triple in this) {
            list1.add(triple.x)
            list2.add(triple.y)
            list3.add(triple.z)
        }
        return Triple(list1, list2, list3)
    }

    private
    fun collectionSizeOrDefault(default: Int) =
            if (this is Collection<*>) this.size else default


    private data
    class XYZ<out T>(private val p:Triple<T, T, T>) {
        val x = p.first
        val y = p.second
        val z = p.third
    }
}
