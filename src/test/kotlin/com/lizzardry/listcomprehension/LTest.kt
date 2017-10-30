package com.lizzardry.listcomprehension

import org.junit.Test

class LTest {
    @Test
    fun with() {
        val intList = listOf(1, 2, 3)
        intList.toL().condition { true }.condition {  }
    }
}