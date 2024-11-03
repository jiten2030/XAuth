package com.techduo.xauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.techduo.xauth.navigation.MainNavigation
import com.techduo.xauth.ui.theme.XAuthTheme
import com.techduo.xauth.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainViewModel: MainViewModel by viewModels()
        setContent {
            XAuthTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainNavigation(modifier = Modifier.padding(it), mainViewModel)
                }
            }
        }
    }
}