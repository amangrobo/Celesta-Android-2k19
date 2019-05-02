package `in`.org.celesta.iitp.account

import `in`.org.celesta.iitp.R
import android.annotation.SuppressLint
import android.view.MotionEvent
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.TextInputLayout


@SuppressLint("ClickableViewAccessibility")
fun TextInputLayout.setPasswordToggleListener(listener: OnPasswordToggleListener) {
    val passwordToggle = findViewById<CheckableImageButton>(R.id.text_input_password_toggle)
    passwordToggle.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            listener.onPasswordToggleChanged(passwordToggle.isChecked)
        }
        false
    }
}

fun TextInputLayout.isPasswordToggleChecked(): Boolean {
    val passwordToggle = findViewById<CheckableImageButton>(R.id.text_input_password_toggle)
    return passwordToggle.isChecked
}

interface OnPasswordToggleListener {
    fun onPasswordToggleChanged(isChecked: Boolean)
}