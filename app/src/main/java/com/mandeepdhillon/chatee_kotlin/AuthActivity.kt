package com.mandeepdhillon.chatee_kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.mandeepdhillon.chatee_kotlin.databinding.ActivityAuthBinding

class AuthActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private lateinit var authMode: AuthMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authMode = intent?.extras?.getParcelable(AuthModeExtraName)!!

        when (authMode) {
            is AuthMode.Login -> {
                binding.authButton.text = getString(R.string.login)
            }
            is AuthMode.Register -> {
                binding.authButton.text = getString(R.string.register)
            }
        }
//    fun Activity.hideKeyboard(){
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0);
//    }
    }
        companion object {
            const val AuthModeExtraName = "AUTH_MODE"
            fun newIntent(authMode: AuthMode, context: Context): Intent {
                val intent = Intent(context, AuthActivity::class.java)
                intent.putExtra(AuthModeExtraName, authMode)
                return intent
            }
        }
    }
