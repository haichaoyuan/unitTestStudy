package com.xh.unitTestStudy

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/** [1] 自动生成待测试类。右键 generate -> Test, 选择待测试方法，勾选 setUp 方法
 * Author: hc
 * DATE: 2019-09-18 = 09:27
 */
class CalculatorTest {
    private lateinit var mCalculator: Calculator

    @Before
    fun setUp() {
        mCalculator = Calculator()
    }

    /**
     * [2] 最简单测试例子
     */
    @Test
    fun addTwoNumbers() {
        var addTwoNumbers = mCalculator.addTwoNumbers(1, 2)
        assert(addTwoNumbers == 3) {
            "addTwoNumbers is error"
        }
    }
}