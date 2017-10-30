package com.lizzardry.listcomprehension.zipper

class NormalZipper<T: Comparable<T>>: Zipper<T> {
    override fun zip(x: List<T>, y: List<T>): List<Zipper.XY<T>> {
        return x.zip(y).map { Zipper.XY(it.first, it.second) }.sortXY()
    }

    override fun zip(x: List<T>, y: List<T>, z: List<T>): List<Zipper.XYZ<T>> {
        return x.zip(y).zip(z).map { (xy, z) ->  Zipper.XYZ(xy.first, xy.second, z)}.sortXYZ()
    }
}

