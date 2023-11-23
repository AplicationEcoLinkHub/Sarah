package tn.esprit.educatif.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import tn.esprit.educatif.model.Cours
import tn.esprit.educatif.R
const val OTP_EMAIL = "OTP_EMAIL"
const val OTP_MOBILE = "OTP_MOBILE"

class MyStatics {

    companion object {
        fun hideKeyboard(context: Context, view: View) {
            val inputMethodManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
        fun getListCours(context: Context) : MutableList<Cours> {
            return mutableListOf<Cours>(
                Cours("1", "R.drawable.tempimage3refgl_1", context.getString(R.string.title) ,context.getString(R.string.header),false),
                Cours("2", "R.drawable.tempimagesfkwbf_1", context.getString(R.string.title) ,context.getString(R.string.header),false),
                Cours("3", "R.drawable.tempimage3refgl_1", context.getString(R.string.title) ,context.getString(R.string.header),false),
                Cours("4", "R.drawable.tempimagesfkwbf_1", context.getString(R.string.title) ,context.getString(R.string.header),false),
            )
        }
    }

}