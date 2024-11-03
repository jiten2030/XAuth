package com.techduo.xauth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.techduo.xauth.screens.HomeScreen
import com.techduo.xauth.screens.LoginScreen
import com.techduo.xauth.screens.SignUpScreen
import com.techduo.xauth.viewmodel.MainViewModel

@Composable
fun MainNavigation(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login", builder = {
        composable("login") {
            LoginScreen(modifier = modifier, navController, mainViewModel)
        }
        composable("signup") {
            SignUpScreen(modifier = modifier, navController, mainViewModel)
        }
        composable("home") {
            HomeScreen(modifier = modifier, navController, mainViewModel)
        }
    })
}