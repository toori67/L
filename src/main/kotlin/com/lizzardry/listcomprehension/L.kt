package com.lizzardry.listcomprehension

import com.lizzardry.listcomprehension.zipper.NormalZipper
import com.lizzardry.listcomprehension.zipper.Zipper

fun <T: Comparable<T>> List<T>.L(zipper: Zipper<T>?=null): L1<T> {
    val passingZipper = zipper ?:NormalZipper<T>()
    return L1(this, passingZipper)
}


class L1<T: Comparable<T>>(private val x: List<T>, private val zipper: Zipper<T>){
    fun with(y: List<T>): L2<T> {
        return L2(x, y, zipper)
    }

    fun cond(cond:(T) -> (Boolean)): L1<T> {
        return L1(x.filter(cond), zipper)
    }

    fun get(): List<T> {
        return x.sortedBy { it }
    }
}

class L2<T: Comparable<T>>(private val x: List<T>,
            private val y: List<T>,
            private val zipper:Zipper<T>) {
    fun with(z: List<T>): L3<T> {
        return L3(x, y, z, zipper)
    }

    fun cond(cond:(x: T, y: T) -> (Boolean)): L2<T> {
        val zipped = zipper.separate(zipper.zip(x, y).filter { (x, y) -> cond(x, y) })
        return L2(zipped.first, zipped.second, zipper)
    }

    fun get(): List<Zipper.XY<T>> {
        return NormalZipper<T>().zip(x, y)
    }
}

class L3<T: Comparable<T>>(private val x: List<T>,
            private val y: List<T>,
            private val z: List<T>,
            private val zipper: Zipper<T>) {

    fun cond(cond: (x: T, y: T, z: T) -> (Boolean)): L3<T> {
        val zipped = zipper.separate(zipper.zip(x, y, z).filter { (x, y, z) -> cond(x, y, z) })
        return L3(zipped.first, zipped.second, zipped.third, zipper)
    }

    fun get(): List<Zipper.XYZ<T>> {
        return NormalZipper<T>().zip(x, y, z)
    }
}


