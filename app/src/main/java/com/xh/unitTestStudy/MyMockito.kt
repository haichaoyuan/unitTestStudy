package com.xh.unitTestStudy

import net.sf.cglib.proxy.Enhancer
import net.sf.cglib.proxy.MethodInterceptor
import net.sf.cglib.proxy.MethodProxy
import java.lang.reflect.Method
import java.util.*

/**
 * 自定义 Mockito，cglib 库简单使用
 * Author: hc
 * DATE: 2019-09-18 = 10:58
 */
class MyMockito {

    companion object {
        val MOCKED_METHODS = HashMap<MyMethodInfo, Any>()

        //[4] when 条件语法
        fun myWhen(methodCall: Any): MyMockInjector {
            return MyMockInjector(methodCall as MyMethodInfo)
        }

        //[1] 第一步
        fun getInterceptor(): MyCGLibInterceptor {
            return MyCGLibInterceptor()
        }
    }
}

/**
 * MethodInfo 作为 key
 */
data class MyMethodInfo(val interceptor: MethodInterceptor, val method: Method?, val arrays: Array<out Any>?)

/**
 * [5] MyMockInjector 数据类
 */
data class MyMockInjector(val methodInfo: MyMethodInfo) {

    /**
     * [6] Return 语法，从缓存重新拿出来
     */
    fun myThenReturn(mockResult: Any) {
        MyMockito.MOCKED_METHODS[methodInfo] = mockResult
        System.out.println("myThenReturn")
    }

}

//[2] 继承 MethodInterceptor 的 MyCGLibInterceptor
class MyCGLibInterceptor : MethodInterceptor {
    override fun intercept(p0: Any?, p1: Method?, p2: Array<out Any>?, p3: MethodProxy?): Any? {
        System.out.println("intercept")
        val key = MyMethodInfo(this, p1, p2)
        // 此处存储key
        var hasMocked = MyMockito.MOCKED_METHODS.containsKey(key)
        if (!hasMocked) {
            // 调用 MyMockito.myWhen 会返回一个 MyMethodInfo 对象作为 key
            // 随后的  MyMethodInfo.thenReturn 将使用这个key 去插入 mock 结果到 MyMockito.MOCKED_METHODS
            System.out.println("Initializing the mock for $key")
            return key
        } else {
            System.out.println("Returns the mock result:")
            return MyMockito.MOCKED_METHODS[key]
        }
    }

    /**
     * [3] Enhancer
     */
    fun <T> getInstance(t: Class<T>): Any? {
        var enhancer = Enhancer()
        enhancer.setSuperclass(t)
        enhancer.setCallback(this)
        return enhancer.create()
    }

}

fun main(args: Array<String>) {
    val myMockList1 = MyMockito.getInterceptor().getInstance(List::class.java) as List<Any>

    MyMockito.myWhen(myMockList1.get(0)).myThenReturn("Hello, I am James")

    var get = myMockList1.get(0)
    System.out.println(get)
}
