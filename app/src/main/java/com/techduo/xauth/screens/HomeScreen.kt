package com.techduo.xauth.screens

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.techduo.xauth.viewmodel.AuthState
import com.techduo.xauth.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    BackHandler {
        (navController.context as? Activity)?.finish()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val authStatus = mainViewModel.authStatus.observeAsState()
        LaunchedEffect(authStatus.value) {
            when (authStatus.value) {
                is AuthState.UnAuthenticated -> navController.navigate("login")
                else -> Unit
            }
        }

        Text(text = "Hello No Data!", modifier.clickable {
        })
        Spacer(Modifier.height(30.dp))
        Button(onClick = {
            mainViewModel.logout()
        }) {
            Text("Logout")
        }
    }
}