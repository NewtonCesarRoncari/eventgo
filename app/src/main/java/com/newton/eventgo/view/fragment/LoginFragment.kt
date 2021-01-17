package com.newton.eventgo.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.airbnb.lottie.LottieAnimationView
import com.newton.eventgo.R
import com.newton.eventgo.view.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private val navController by lazy { NavHostFragment.findNavController(this) }

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

        login_btn.setOnClickListener {
            viewModel.login()
            goToListEventFragment()
        }
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
}