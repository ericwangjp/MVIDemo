package com.example.mvidemo.ui.page.setting

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.mvidemo.base.BaseFragment
import com.example.mvidemo.databinding.FragmentSettingBinding
import com.example.mvidemo.utils.observeWithLifecycle

class SettingFragment :
    BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::inflate) {
    private val viewModel by viewModels<SettingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.state.collect {
//
//                }
//            }
//        }
        viewModel.state.observeWithLifecycle(this) {

        }
    }

    override fun initViews(binding: FragmentSettingBinding) {
        with(binding) {
            tvTitle.text = "设置"
        }
    }

}