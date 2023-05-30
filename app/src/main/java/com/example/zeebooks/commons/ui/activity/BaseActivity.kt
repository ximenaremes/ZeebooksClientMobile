package com.example.zeebooks.commons.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.zeebooks.R
import com.example.zeebooks.feature_dashboard.ui.fragment.CategoriesFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.ExploreFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.FilterFragment
import com.example.zeebooks.feature_dashboard.ui.fragment.ReviewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


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

