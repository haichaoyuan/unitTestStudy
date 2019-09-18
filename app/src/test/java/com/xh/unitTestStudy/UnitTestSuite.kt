package com.xh.unitTestStudy

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * [1] 注解配置总统套房类，并添加待运行的测试类，即可一次性统一运行
 * Author: hc
 * DATE: 2019-09-18 = 09:35
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(CalculatorTest::class, CalculatorWithParameterTest::class)
class UnitTestSuite