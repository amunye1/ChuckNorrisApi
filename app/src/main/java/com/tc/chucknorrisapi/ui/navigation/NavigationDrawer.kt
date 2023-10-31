package com.tc.chucknorrisapi.ui.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerHeader() {
    // Centered box with the title of the app
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Chuck Norris App", fontSize = 60.sp) // Display the app title
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>, // List of items to display in the drawer
    modifier: Modifier = Modifier, // Optional modifier for custom styling
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp), // Text style for item titles
    onItemClick: (MenuItem) -> Unit // Callback for item click
) {
    // Create a LazyColumn to display the list of items
    LazyColumn(modifier) {
        items(items) { item ->
            // Row for each item with an icon, title, and click functionality
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription) // Display item icon
                Spacer(modifier = Modifier.width(16.dp)) // Add spacing
                Text(
                    text = item.title, // Display item title
                    style = itemTextStyle, // Apply the specified text style
                    modifier = Modifier.weight(1f) // Expand the title to fill available space
                )
            }
        }
    }
}
