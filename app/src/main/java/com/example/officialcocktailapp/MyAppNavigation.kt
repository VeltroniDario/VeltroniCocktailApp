package com.example.application01

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.application01.Route.DrinkDetailScreen
import com.example.officialcocktailapp.MainScreen


@Composable
fun MyAppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Route.MainScreen, builder = {
        composable(Route.MainScreen) {
            MainScreen(navController)
        }


    } )
}