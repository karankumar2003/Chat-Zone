package com.example.chatzone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.chatzone.navigation.AppGraphs
import com.example.chatzone.screens.AppScreens
import com.example.chatzone.screens.auth.CreateAccountScreen
import com.example.chatzone.screens.auth.LogInScreen
import com.example.chatzone.screens.main.MainScreen
import com.example.chatzone.screens.splash.SplashScreen
import com.example.chatzone.ui.theme.ChatZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatZoneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SplashScreen.name ){
        composable(AppScreens.SplashScreen.name){
            SplashScreen(navController)
        }
        navigation(startDestination = AppScreens.LogInScreen.name,route=AppGraphs.Auth.name){
            composable(AppScreens.LogInScreen.name){
                LogInScreen(navController)
            }
            composable(AppScreens.CreateAccountScreen.name){
                CreateAccountScreen(navController)
            }
        }
        navigation(startDestination = AppScreens.MainScreen.name,route = AppGraphs.Main.name){
            composable(AppScreens.MainScreen.name){
                MainScreen()
            }
        }
    }
}