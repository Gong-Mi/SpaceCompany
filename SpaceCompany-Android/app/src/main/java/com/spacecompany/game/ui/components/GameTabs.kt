package com.spacecompany.game.ui.components

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import android.util.Log

@Composable
fun GameTabs(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Resources", "Research", "Settings", "Solar System", "Wonders")

    Log.d("GameTabs", "Composing GameTabs with ${tabs.size} tabs. Selected index: $selectedIndex")

    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, title ->
            Log.d("GameTabs", "Composing Tab: $title")
            Tab(text = { Text(title) },
                selected = selectedIndex == index,
                onClick = { onTabSelected(index) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewGameTabs() {
    GameTabs(selectedIndex = 0, onTabSelected = {})
}
