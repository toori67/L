package com.lizzardry.listcomprehension

import com.lizzardry.listcomprehension.zipper.CartesianZipper
import com.lizzardry.listcomprehension.zipper.Zipper
import org.junit.Test

import org.junit.Assert.*

class L1Test {

    val intList1 = listOf(1, 3, 2)
    val intList2 = listOf(4, 6, 5)
    val intList3 = listOf(11, 13, 12)

    @Test
    fun with() {
        val l1 = intList1.reversed().L().get()
        val _l1 = intList1
                .sortedBy { it }
        assertArrayEquals(l1.toTypedArray(), _l1.toTypedArray())

        val l2 = intList1.L().with(intList2).get()
        val _l2 = intList1.zip(intList2).map { (x, y) -> Zipper.XY(x, y)}.sortedBy { it.x }.sortedBy { it.y }
        assertArrayEquals(_l2.toTypedArray(), l2.toTypedArray())

        val l3 = intList1.L().with(intList2).with(intList3).get()
        val _l3 = listOf(Zipper.XYZ(1, 4, 11), Zipper.XYZ(2, 5, 12), Zipper.XYZ(3, 6, 13))
                .sortedBy { it.x }.sortedBy { it.y }.sortedBy { it.z }
        assertArrayEquals(l3.toTypedArray(), _l3.toTypedArray())
    }

    @Test
    fun cond() {
        val l1 = intList2.L().cond { x -> x>4 }.get()
        val _l1 = listOf(6, 5).sortedBy { it }
        assertArrayEquals(l1.toTypedArray(), _l1.toTypedArray())

        val l2 = intList1.L(CartesianZipper()).with(intList2).cond {x, y -> x*2 == y}.get()
        val _l2 = intList1.map { x -> intList2.map { y -> Zipper.XY(x, y) } }.flatten().filter { (x, y) -> x * 2 == y }
                .sortedBy { it.x }.sortedBy { it.y }
        assertArrayEquals(_l2.toTypedArray(), l2.toTypedArray())

        val l3 = intList1.L(CartesianZipper()).with(intList2).with(intList3)
                .cond { _, y, z -> y * 2 == z }
                .cond { x, _, _ -> x > 2 }
                .get()
        val _l3 = listOf(Zipper.XYZ(3, 6, 12))
        assertArrayEquals(_l3.toTypedArray(), l3.toTypedArray())
    }

}