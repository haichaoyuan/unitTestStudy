package com.xh.unitTestStudy

import android.content.SharedPreferences

/**
 * Author: hc
 * DATE: 2019-09-18 = 09:57
 */
class SharePreferenceHelper(val mSharedPreferences: SharedPreferences) {
    companion object {
        const val NAME = "name"
        const val KEY_NAME = "key_name"
        const val KEY_MAIL = "key_mail"
    }


    /**
     * 存
     */
    fun saveBean(keyBean: KeyBean): Boolean {
        var editor = mSharedPreferences.edit()
        editor.putString(KEY_NAME, keyBean.name)
        editor.putString(KEY_MAIL, keyBean.mail)
        return editor.commit()
    }

    fun getBean():KeyBean{
        val name = mSharedPreferences.getString(KEY_NAME, "")?:""
        val mail = mSharedPreferences.getString(KEY_MAIL,"")?:""
        return KeyBean(name, mail)
    }
}