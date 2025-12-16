package com.spacecompany.game.ui.components

import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.spacecompany.game.R

@Composable
fun GameTabs(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        stringResource(R.string.tab_resources),
        stringResource(R.string.tab_research),
        stringResource(R.string.tab_settings),
        stringResource(R.string.tab_solar_system),
        stringResource(R.string.tab_wonders)
    )

    TabRow(selectedTabIndex = selectedIndex) {
        tabs.forEachIndexed { index, title ->
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
