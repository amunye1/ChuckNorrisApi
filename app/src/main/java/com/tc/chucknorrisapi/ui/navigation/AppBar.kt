package com.tc.chucknorrisapi.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.tc.chucknorrisapi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onNavigationIconClick:() -> Unit
){

    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Magenta),
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Toggle drawer" )
            }
        }
        )
}
