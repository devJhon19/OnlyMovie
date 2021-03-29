package com.dev.jhon.onlymovie.ui.login

import android.app.Application
import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.jhon.onlymovie.ui.ScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

sealed class LoginState{
    object Start : LoginState()
    class Successful(val user : String): LoginState()
    class Error(val message: String): LoginState()
    class KeyboardHeightChanged(val height : Int, val isOpen: Boolean, val isLandScape: Boolean): LoginState()
}

class LoginViewModel(application: Application, val state : MutableLiveData<ScreenState<LoginState>> ) : ViewModel() {

    private lateinit var keyboardHeightProvider: KeyboardHeightProvider

    /**
     * Al usar el Flag: WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, no se puede usar windowSoftInputMode:AdjustResize
     * Detecta cuando el teclado está abierto
     */
    fun startKeyBoardHeightProvider(context: Context, windowManager: WindowManager, view: View){
        keyboardHeightProvider = KeyboardHeightProvider(context, windowManager, view,
            object : KeyboardHeightProvider.KeyboardHeightListener {
                override fun onKeyboardHeightChanged(
                    keyboardHeight: Int,
                    keyboardOpen: Boolean,
                    isLandscape: Boolean
                ) {
                    //Do what you want or have to with the parameters..
                    state.value = ScreenState.Render(LoginState.KeyboardHeightChanged(keyboardHeight, keyboardOpen, isLandscape))
                }
            })
    }

    /**
     * Cancela el listener para evitar perdida de memoria
     */
    fun stopKeyBoardHeightProvider(){
        keyboardHeightProvider.stopGlobalLayoutListener()
    }

    /**
     * simula tarea de N segundos y realiza un callback para notificar a la actividad..
     */
    fun requestLogin(username: String, password: String){
        state.value = ScreenState.Render(LoginState.Start)
        GlobalScope.launch {
            Thread.sleep(2000)
            GlobalScope.launch(Dispatchers.Main) {
                if(username == "Admin" && password == "Password*123"){
                    state.value = ScreenState.Render(LoginState.Successful("Admin"))
                }else{
                    state.value = ScreenState.Render(LoginState.Error("Usuario o contraseña incorrecto"))
                }
            }
        }
    }

    /**
     * Fábrica para construir HomeViewModel con parámetro
     */
    class Factory(val app: Application, private val state : MutableLiveData<ScreenState<LoginState>> ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel(app, state) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

