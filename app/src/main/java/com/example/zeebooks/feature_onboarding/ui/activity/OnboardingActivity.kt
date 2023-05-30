package com.example.zeebooks.feature_onboarding.ui.activity


import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.zeebooks.R
import com.example.zeebooks.commons.ui.activity.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class OnboardingActivity : BaseActivity() {

    override val contentResId = R.layout.activity_onboarding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        splashScreen.setOnExitAnimationListener {
//            it.view.animate().setDuration(ANIM_DURATION_BACKGROUND_IN_MILLISEC).alpha(0f).start()
//        }

//        val navController = (supportFragmentManager.findFragmentById(
//            R.id.nav_host_fragment
//        ) as NavHostFragment).navController

//        val graph = navController.navInflater.inflate(R.navigation.nav_onboarding)
////
//        if (intent?.extras?.getBoolean(OPEN_CREATE_ACCOUNT) == true) {
//            graph.setStartDestination(R.id.action_registerFragment_to_loginFragment)
//        }

//        navController.setGraph(graph, intent?.extras)

    }

 open val navController: NavController? by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_onboarding) as NavHostFragment?
        return@lazy navHostFragment?.navController
    }


    companion object {
        private const val ANIM_DURATION_ICON_IN_MILLISEC = 400L
        private const val ANIM_DURATION_BACKGROUND_IN_MILLISEC = 1000L
    }
}