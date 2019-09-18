package com.xh.unitTestStudy

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString


/**
 * Mock 例子
 * Author: hc
 * DATE: 2019-09-18 = 09:46
 */
@RunWith(AndroidJUnit4::class)
class SharedPreferencesHelperTest {
    lateinit var mContext: Context
    lateinit var mSharePreferences: SharedPreferences
    lateinit var sharePreferenceHelper: SharePreferenceHelper

    @Before
    fun setUp() {
        mContext = InstrumentationRegistry.getTargetContext()

        // [1] PreferenceManager.getDefaultSharedPreferences 与 mContext.getSharedPreferences 逻辑类似
        // 区别就是文件名默认是 "包名_preferences"
        mSharePreferences = PreferenceManager.getDefaultSharedPreferences(mContext)
//        mSharePreferences = mContext.getSharedPreferences(SharePreferenceHelper.NAME, Context.MODE_PRIVATE)


        //[2] mock 出场
        //2.1 mock 两个类
        var mockSharedPref = Mockito.mock(SharedPreferences::class.java)
        var mockEditor = Mockito.mock(SharedPreferences.Editor::class.java)
        // 2.2 mock 已经声明的对象
//        var mockEditor2 = Mockito.spy(mockEditor)

        //[3]
        // 3.1 条件, 调用 mockSharedPref.edit -> mockEditor
        //条件，调用 mockEditor.commit -> false
        org.mockito.Mockito.`when`(mockSharedPref.edit()).thenReturn(mockEditor)
        `when`(mockEditor.commit()).thenReturn(true)
        `when`(mockSharedPref.getString(SharePreferenceHelper.KEY_NAME, "")).thenReturn("myname")
        // 3.2 进阶，使用 Matchers.anyString() 替换固定字符串，其他类型以此类推
        `when`(mockSharedPref.getString(Matchers.anyString(), "")).thenReturn("myname")

        //[4] 埋点完毕，可以使用了
        sharePreferenceHelper = SharePreferenceHelper(mSharePreferences)
    }

    @Test
    public fun testSave() {
        var saveBean = sharePreferenceHelper.saveBean(KeyBean("1", "12"))
        assert(saveBean)
    }

    @Test
    public fun testPut() {
        var bean = sharePreferenceHelper.getBean()
        assert(bean.name == "myname")
    }
}