package com.example.tugassubmission.costumView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import com.example.tugassubmission.R

@SuppressLint("ClickableViewAccessibility")
class EdtPasswordText : AppCompatEditText {

    private var isPasswordVisible = false
    @SuppressLint("UseCompatLoadingForDrawables")
    private val openEyeDrawable: Drawable = context.getDrawable(R.drawable.baseline_visibility_24)!! // Ini adalah ikon mata terbuka
    @SuppressLint("UseCompatLoadingForDrawables")
    private val closedEyeDrawable: Drawable = context.getDrawable(R.drawable.baseline_visibility_off_24)!! // Ini adalah ikon mata tertutup

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        // Atur gambar mata tertutup sebagai gambar kompound terakhir (kanan)
        setCompoundDrawablesWithIntrinsicBounds(null, null, closedEyeDrawable, null) // Ikon mata tertutup

        // Atur touch listener pada EditText
        setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableRight = compoundDrawables[2]
                if (drawableRight != null && event.x >= right - drawableRight.bounds.width()) {
                    isPasswordVisible = !isPasswordVisible
                    updatePasswordVisibility()
                    true
                } else {
                    false
                }
            } else {
                false
            }
        }

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val password = s.toString()
                error = when {
                    password.isBlank() -> context.getString(R.string.PasswordKosong)
                    password.length < 8 -> context.getString(R.string.error_password_more_6)
                    else -> null
                }
            }
        })
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updatePasswordVisibility() {
        if (isPasswordVisible) {
            // Tampilkan teks biasa
            transformationMethod = null
            setCompoundDrawablesWithIntrinsicBounds(null, null, openEyeDrawable, null) // Ikon mata terbuka
        } else {
            // Sembunyikan teks
            transformationMethod = PasswordTransformationMethod.getInstance()
            setCompoundDrawablesWithIntrinsicBounds(null, null, closedEyeDrawable, null) // Ikon mata tertutup
        }
    }
}