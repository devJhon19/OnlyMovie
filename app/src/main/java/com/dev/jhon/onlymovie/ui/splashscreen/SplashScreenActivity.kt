package com.dev.jhon.onlymovie.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.dev.jhon.onlymovie.R
import com.dev.jhon.onlymovie.ui.BaseActivity
import com.dev.jhon.onlymovie.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivAppLogo?.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out))

        initView()

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            this@SplashScreenActivity.finish()
        },2000)
    }

    private fun initView() {
    }
}