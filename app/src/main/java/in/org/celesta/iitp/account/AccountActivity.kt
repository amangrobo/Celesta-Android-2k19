package `in`.org.celesta.iitp.account

import `in`.org.celesta.iitp.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {

    private lateinit var bunn: Bunn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        bunn = Bunn(animationView)

        setupBunnListeners()
    }

    private fun setupBunnListeners() {
        emailInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                bunn.setPreTrackingState()
            }
        }

        passwordInputEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                if (passwordInputLayout.isPasswordToggleChecked())
                    bunn.setPeekState()
                else
                    bunn.setShyState()
            }
        }

        passwordInputLayout.setPasswordToggleListener(object : OnPasswordToggleListener {
            override fun onPasswordToggleChanged(isChecked: Boolean) {
                if (!passwordInputEditText.hasFocus()) return
                if (isChecked) {
                    bunn.setShyState()
                } else {
                    bunn.setPeekState()
                }
            }
        })

        emailInputEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                bunn.setEyesPosition(getTextWidth(emailInputEditText) / emailInputEditText.width)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                bunn.startTracking()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun getTextWidth(editText: TextInputEditText): Float {
        return editText.paint.measureText(editText.text.toString())
    }
}
