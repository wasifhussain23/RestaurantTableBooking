package s3301912tablebooking.wasif.app

import android.content.Context


object CustomerPreferences {

    fun storeCS(context: Context, value: Boolean) {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putBoolean("CUSTOMER_LOGIN_STATUS", value).apply()
    }

    fun getCS(context: Context): Boolean {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        return userLogin.getBoolean("CUSTOMER_LOGIN_STATUS", false)
    }

    fun storeCSName(context: Context, value: String) {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putString("CUSTOMER_NAME", value).apply()
    }

    fun getCSName(context: Context): String {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        return userLogin.getString("CUSTOMER_NAME", "")!!
    }

    fun storeCSMail(context: Context, value: String) {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        val editor = userLogin.edit()
        editor.putString("CUSTOMER_MAIL", value).apply()
    }

    fun getCSMail(context: Context): String {
        val userLogin = context.getSharedPreferences("TABLE_BOOKING_DATA", Context.MODE_PRIVATE)
        return userLogin.getString("CUSTOMER_MAIL", "")!!
    }
}