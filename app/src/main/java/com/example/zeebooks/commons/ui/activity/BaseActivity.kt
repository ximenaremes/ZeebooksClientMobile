package com.example.zeebooks.commons.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity : AppCompatActivity() {
//    lateinit var controller: NavHostFragment

//    protected open val viewModel: BaseViewModel by viewModels()
    private lateinit var binding: ViewDataBinding
    protected abstract val contentResId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, contentResId)

    }

    protected open fun <VB : ViewDataBinding> getViewDataBinding(): ViewDataBinding {
        return binding
    }

}

