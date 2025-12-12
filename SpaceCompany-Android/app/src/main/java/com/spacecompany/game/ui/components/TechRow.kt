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
import com.spacecompany.game.data.TechData
import com.spacecompany.game.model.TechState
import java.text.DecimalFormat

@Composable
fun TechRow(
    techData: TechData,
    techState: TechState,
    canAfford: Boolean,
    onBuy: () -> Unit
) {
    val df = DecimalFormat("#,##0")
    val costString = techData.cost.map { (res, amount) -> "${df.format(amount)} ${res.name}" }.joinToString(", ")

    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = techData.name, style = MaterialTheme.typography.titleMedium)
            Text(text = techData.desc, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Cost: $costString", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(Modifier.width(16.dp))
        if (techState.current < techData.maxLevel) {
            Button(onClick = onBuy, enabled = canAfford) {
                Text(techData.buttonText ?: "Research")
            }
        } else {
            Text("Researched")
        }
    }
}
