package com.lizzardry.listcomprehension.zipper

class CartesianZipper<T: Comparable<T>>: Zipper<T> {

    override fun zip(x: List<T>, y: List<T>): List<Zipper.XY<T>> {
        return x.map { x -> y.map { Zipper.XY(x, it) } }.flatten().sortXY()
    }

    override fun zip(x: List<T>, y: List<T>, z: List<T>): List<Zipper.XYZ<T>> {
        return zip(x, y).map { (x, y) -> z.map { Zipper.XYZ(x, y, it) } }.flatten().sortXYZ()
    }
}