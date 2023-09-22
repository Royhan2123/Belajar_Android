package com.example.storyapp.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.doOnTextChanged

class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {

//        Alih-alih kamu menggunakan fungsi addTextChangedListener dan diharuskan
//        mengoverride semua fungsi yang ada didalamnya, sebaiknya kamu menggunakan
//        fungsi berikut agar lebih singkat lagi :

        doOnTextChanged { text, _, _, _ ->
            if (text?.isNotEmpty() == true && text.length < 8) {
                error = "Password tidak boleh kurang dari 8 karakter"
            }
        }

//
//        addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                // Do nothing
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (s.isNotEmpty() && s.length < 8) {
//                    error = "Password tidak boleh kurang dari 8 karakter"
//                }
//
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                // Do nothing
//            }
//
//        })
//    }
    }
}