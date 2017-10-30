package com.lizzardry.listcomprehension.zipper

interface Zipper<T: Comparable<T>> {
    fun zip(x: List<T>, y: List<T>): List<XY<T>>
    fun zip(x: List<T>, y: List<T>, z: List<T>): List<XYZ<T>>

    fun List<XY<T>>.sortXY(): List<XY<T>> {
        return this.sortedBy { it.x }.sortedBy { it.y }
    }

    fun List<XYZ<T>>.sortXYZ(): List<XYZ<T>> {
        return this.sortedBy { it.x }.sortedBy { it.y }.sortedBy { it.z }
    }

    fun separate(data: List<Zipper.XY<T>>): Pair<List<T>, List<T>> {
        return data.map { Pair(it.x, it.y) }.unzip()
    }

    fun separate(data: List<Zipper.XYZ<T>>): Triple<List<T>, List<T>, List<T>> {
        val expectedSize = Math.round(data.size/3.0).toInt()
        val setX = HashSet<T>(expectedSize)
        val setY = HashSet<T>(expectedSize)
        val setZ = HashSet<T>(expectedSize)
        data.map { Triple(it.x, it.y, it.z) }.forEach { (x, y, z) -> setX.add(x); setY.add(y); setZ.add(z) }
        return Triple(setX.toList(), setY.toList(), setZ.toList())
    }

    data
    class XY<out T>(val x: T, val y: T)
    data
    class XYZ<out T>(val x: T, val y: T, val z: T)
}