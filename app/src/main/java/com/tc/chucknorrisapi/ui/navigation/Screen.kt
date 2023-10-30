package com.tc.chucknorrisapi.ui.navigation

sealed class Screen(val route :String){
    object MainScreen :Screen("mainScreen")
    object RandomJoke: Screen ("randomJoke")
    object TextInput: Screen ("textInput")
    object NeverEndingList: Screen("neverEndingList")
}
