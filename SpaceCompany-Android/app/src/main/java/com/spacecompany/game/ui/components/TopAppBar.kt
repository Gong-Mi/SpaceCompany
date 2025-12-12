package com.spacecompany.game.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar(
    title: String,
    isEnergyLow: Boolean
) {
    CenterAlignedTopAppBar(
        title = {
            Text(title)
        },
        actions = {
            if (isEnergyLow) {
                Text("Energy Low!", color = Color.Red)
            }
        }
    )
}

@Preview
@Composable
fun PreviewGameTopAppBar() {
    GameTopAppBar("Space Company", isEnergyLow = true)
}
