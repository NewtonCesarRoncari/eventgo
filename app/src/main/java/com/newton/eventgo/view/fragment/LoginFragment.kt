package com.newton.eventgo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieAnimationView
import com.newton.eventgo.R
import com.newton.eventgo.validation.PatternValidation
import com.newton.eventgo.validation.ValidaEmail
import com.newton.eventgo.validation.Validator
import com.newton.eventgo.view.viewmodel.LoginViewModel
import com.newton.eventgo.view.viewmodel.UserDataViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private val userViewModel: UserDataViewModel by viewModel()
    private val validators = mutableListOf<Validator>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logo_app_animation.setAnimation("anim/animate_login.json")
        initAnimation(logo_app_animation)

        validateFieldName()
        validateFieldEmail()

        login_btn.setOnClickListener {
            val formIsValid: Boolean = validAllFields()
            if (formIsValid) {
                getUserData()
                viewModel.login()
                goToListEventFragment()
            }
        }
    }

    private fun getUserData() {
        userViewModel.setName(login_name.text.toString())
        userViewModel.setEmail(login_email.text.toString())
    }

    private fun goToListEventFragment() {
        val direction = LoginFragmentDirections
            .actionLoginFragmentToHomeFragment()
        navController.navigate(direction)
    }

    private fun initAnimation(animation: LottieAnimationView) {
        with(animation) {
            scaleX = 0.5f
            scaleY = 0.5f
            visibility = View.VISIBLE
            playAnimation()
        }
    }

    private fun validateFieldEmail() {
        val fieldEmail: EditText? = loginEmailTextInputLayout.editText
        val validator = ValidaEmail(loginEmailTextInputLayout)
        validators.add(validator)
        fieldEmail!!.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validator.isValid
            }
        }
    }

    private fun validateFieldName() {
        val field = loginNameTextInputLayout.editText
        val validator = PatternValidation(loginNameTextInputLayout)
        validators.add(validator)
        field!!.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validator.isValid
            }
        }
    }

    private fun validAllFields(): Boolean {
        var formIsValid = true
        for (validator in validators) {
            if (!validator.isValid) {
                formIsValid = false
            }
        }
        return formIsValid
    }
}