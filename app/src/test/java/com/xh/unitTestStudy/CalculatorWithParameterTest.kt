package com.xh.unitTestStudy

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.util.*

/** [1] 测试多个覆盖条件
 * 0. 右键，generate -> parameter funtion ,生成参数化数据提供方法
 * 1. 测试类 添加 @RunWith(Parameterized.class) 注解
 * 2. 添加构造函数，并将测试的参数作为其构造参数
 * 3. 新建测试方法 addTwoNumbers2，使用构造方法的传入的参数进行测试
 * Author: hc
 * DATE: 2019-09-18 = 09:27
 */
@RunWith(Parameterized::class)
class CalculatorWithParameterTest(val addA: Int, val addB: Int, val exceptV: Int) {
    //=========================================================================================
    //================================================ add in 2019-09-18 : 测试覆盖条件
    //=========================================================================================
    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return Arrays.asList(
                arrayOf<Any>(0, 0, 0),
                arrayOf<Any>(0, -1, -1),
                arrayOf<Any>(2, 2, 5),//x
                arrayOf<Any>(8, 8, 16),
                arrayOf<Any>(16, 16, 33),//x
                arrayOf<Any>(32, 0, 32),
                arrayOf<Any>(64, 64, 129)//x
            )
        }
    }

    private lateinit var mCalculator: Calculator

    @Before
    fun setUp() {
        mCalculator = Calculator()
    }

    /**
     * [2] 最简单测试例子
     */
    @Test
    fun addTwoNumbers2() {
        var addTwoNumbers = mCalculator.addTwoNumbers(addA, addB)

        assert(addTwoNumbers == exceptV) {
            "addTwoNumbers is error"
        }
    }
}