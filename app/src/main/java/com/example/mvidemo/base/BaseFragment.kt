package com.example.mvidemo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * desc: BaseFragment
 * Author: fengqy
 * Version: V1.0.0
 * Create: 2024/1/12 11:24
 */
abstract class BaseFragment<VB : ViewBinding>(
    val viewBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null

    protected val binding: VB
        get() = requireNotNull(_binding) { "The property of binding has been destroyed." }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        _binding = viewBinding(inflater, container, false)
        return binding.run {
            initViews(this)
            root
        }
    }

    abstract fun initViews(binding: VB)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
