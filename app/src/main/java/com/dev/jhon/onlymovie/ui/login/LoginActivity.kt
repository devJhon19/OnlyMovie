package com.dev.jhon.onlymovie.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.dev.jhon.onlymovie.R
import com.dev.jhon.onlymovie.domain.extensions.toast
import com.dev.jhon.onlymovie.ui.BaseActivity
import com.dev.jhon.onlymovie.ui.ScreenState
import com.dev.jhon.onlymovie.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var viewModel : LoginViewModel

    /**
     * puente de comunicaciòn con el view model
     */
    fun updateUI(screenState: ScreenState<LoginState>?) {
        when (screenState) {
            is ScreenState.Render -> processRenderState(screenState.renderState, this)
        }
    }

    /**
     * procesa los distintos tipos de estado
     */
    fun processRenderState(renderState: LoginState, context: Context){
        when (renderState){
            is LoginState.Start -> {
                loadingPanel?.visibility = View.VISIBLE
            }
            is LoginState.Successful -> {
                loadingPanel?.visibility = View.GONE
                toast("Bienvenido:\n" + renderState.user)
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            }
            is LoginState.Error -> {
                loadingPanel?.visibility = View.GONE
                toast(renderState.message)
            }
            is LoginState.KeyboardHeightChanged -> {
                if (renderState.isOpen) {
                    if (clMain.paddingBottom != renderState.height) {
                        //set the padding of the contentView for the keyboard
                        clMain.setPadding(0, 0, 0, renderState.height)
                        if(renderState.isLandScape){
                            tvTitle.setPadding(0, 0, 0, renderState.height)
                        }
                    }
                } else {
                    if (clMain.paddingBottom != 0) {
                        //set the padding of the contentView for the keyboard
                        clMain.setPadding(0, 0, 0, 0)
                        if(renderState.isLandScape){
                            tvTitle.setPadding(0, 0, 0, 0)
                        }
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this, LoginViewModel.Factory(application, MutableLiveData())).get(
            LoginViewModel::class.java)

        initViews()
    }

    private fun initViews(){
        viewModel.state.observe(::getLifecycle, ::updateUI)

        btLogin?.setOnClickListener{
            if(etUsername.text.isEmpty() || etPassword.text.isEmpty()){
                toast("Campos incompletos")
                return@setOnClickListener
            }

            viewModel.requestLogin(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startKeyBoardHeightProvider(this, windowManager, clMain)
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopKeyBoardHeightProvider()
    }
}