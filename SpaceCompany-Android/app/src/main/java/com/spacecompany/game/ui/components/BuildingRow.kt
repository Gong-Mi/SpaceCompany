package com.spacecompany.game.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spacecompany.game.model.BuildingState
import com.spacecompany.game.model.Resource
import java.text.DecimalFormat

@Composable
fun BuildingRow(
    building: BuildingState,
    cost: Map<Resource, Double>,
    isAffordable: Boolean,
    onBuy: () -> Unit
) {
    val df = DecimalFormat("#,##0")
    val costString = cost.map { (res, amount) -> "${df.format(amount)} ${res.name}" }.joinToString(", ")

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "${building.id.replaceFirstChar { it.uppercase() }}: ${building.count}")
            Text(text = "Cost: $costString", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(Modifier.width(16.dp))
        Button(onClick = onBuy, enabled = isAffordable) {
            Text("Buy")
        }
    }
}
