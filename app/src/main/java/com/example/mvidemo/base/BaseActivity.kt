package com.example.mvidemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * desc: BaseActivity
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/12 14:23
 */
class BaseActivity<VB : ViewBinding>(val viewBinding: (LayoutInflater) -> VB) :
    AppCompatActivity() {

    private var _binding: VB? = null

    protected val binding: VB
        get() = requireNotNull(_binding) { "The property of binding has been destroyed." }

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = viewBinding(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}