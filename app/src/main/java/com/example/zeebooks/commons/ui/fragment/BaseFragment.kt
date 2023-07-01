package com.example.zeebooks.commons.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.zeebooks.commons.viewmodel.model.BaseViewModel


abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    protected open val viewModel: BaseViewModel by viewModels()

    val binding get() = _binding!!
    val optionalBinding get() = _binding
    private var _binding: VB? = null
    abstract val resId: Int
    open var isFullScreen = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (_binding == null) {
            _binding = DataBindingUtil.inflate(inflater, resId, container, false)
        }
        _binding?.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        unbind()
        super.onDestroyView()
    }
    open fun unbind() {
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        if (isFullScreen) {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, isFullScreen.not())
        }

    }

    override fun onStop() {
        if (isFullScreen) {
            WindowCompat.setDecorFitsSystemWindows(requireActivity().window, isFullScreen)
        }
        super.onStop()
    }


}