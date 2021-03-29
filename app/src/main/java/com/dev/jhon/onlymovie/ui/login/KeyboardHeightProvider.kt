package com.dev.jhon.onlymovie.ui.login

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.*
import android.widget.LinearLayout
import android.widget.PopupWindow

class KeyboardHeightProvider(
    context: Context,
    windowManager: WindowManager,
    parentView: View,
    listener: KeyboardHeightListener?
) : PopupWindow(context) {
    interface KeyboardHeightListener {
        fun onKeyboardHeightChanged(
            keyboardHeight: Int,
            keyboardOpen: Boolean,
            isLandscape: Boolean
        )
    }

    private var popupView: LinearLayout = LinearLayout(context)
    private val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val rect = Rect()
        popupView.getWindowVisibleDisplayFrame(rect)
        var keyboardHeight = metrics.heightPixels - (rect.bottom - rect.top)
        val resourceID =
            context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceID > 0) {
            keyboardHeight -= context.resources.getDimensionPixelSize(resourceID)
        }
        if (keyboardHeight < 100) {
            keyboardHeight = 0
        }
        val isLandscape = metrics.widthPixels > metrics.heightPixels
        val keyboardOpen = keyboardHeight > 0
        listener?.onKeyboardHeightChanged(keyboardHeight, keyboardOpen, isLandscape)
    }

    init {
        popupView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        popupView.viewTreeObserver
            .addOnGlobalLayoutListener(onGlobalLayoutListener)
        contentView = popupView
        softInputMode =
            WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
        inputMethodMode = INPUT_METHOD_NEEDED
        width = 0
        height = ViewGroup.LayoutParams.MATCH_PARENT
        setBackgroundDrawable(ColorDrawable(0))
        parentView.post {
            showAtLocation(
                parentView,
                Gravity.NO_GRAVITY,
                0,
                0
            )
        }
    }

    fun stopGlobalLayoutListener(){
        popupView.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }
}