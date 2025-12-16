package com.spacecompany.game.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spacecompany.game.R
import com.spacecompany.game.model.Resource
import com.spacecompany.game.model.ResourceState
import java.text.DecimalFormat

import androidx.annotation.StringRes
import android.util.Log // Added for logging

// Helper function to map Resource enum to its string resource ID
@StringRes
fun getResourceNameId(resource: Resource): Int {
    return when (resource) {
        Resource.ENERGY -> R.string.resource_energy
        Resource.PLASMA -> R.string.resource_plasma
        Resource.URANIUM -> R.string.resource_uranium
        Resource.LAVA -> R.string.resource_lava
        Resource.OIL -> R.string.resource_oil
        Resource.METAL -> R.string.resource_metal
        Resource.GEM -> R.string.resource_gem
        Resource.CHARCOAL -> R.string.resource_charcoal
        Resource.WOOD -> R.string.resource_wood
        Resource.SILICON -> R.string.resource_silicon
        Resource.LUNARITE -> R.string.resource_lunarite
        Resource.METHANE -> R.string.resource_methane
        Resource.TITANIUM -> R.string.resource_titanium
        Resource.GOLD -> R.string.resource_gold
        Resource.SILVER -> R.string.resource_silver
        Resource.HYDROGEN -> R.string.resource_hydrogen
        Resource.HELIUM -> R.string.resource_helium
        Resource.ICE -> R.string.resource_ice
        Resource.METEORITE -> R.string.resource_meteorite
        Resource.SCIENCE -> R.string.resource_science
        Resource.ROCKETFUEL -> R.string.resource_rocketfuel
    }
}

@Composable
fun ResourcePanel(
    modifier: Modifier = Modifier,
    resources: List<ResourceState>,
    onResourceSelected: (Resource) -> Unit
) {
    Log.d("ResourcePanel", "Composing ResourcePanel. Total resources: ${resources.size}")
    val filteredResources = resources.filter { it.unlocked || it.current > 0 || it.id == Resource.METAL || it.id == Resource.WOOD || it.id == Resource.GEM }
    Log.d("ResourcePanel", "Filtered resources count: ${filteredResources.size}")
    filteredResources.forEach { Log.d("ResourcePanel", "  - Visible resource: ${it.id.name}, current: ${it.current}, unlocked: ${it.unlocked}") }

    LazyColumn(modifier = modifier) {
        items(filteredResources) { resource ->
            ResourceRow(
                resource = resource,
                onResourceSelected = { onResourceSelected(resource.id) }
            )
        }
    }
}

@Composable
fun ResourceRow(
    resource: ResourceState,
    onResourceSelected: () -> Unit
) {
    val df = DecimalFormat("#,##0.0")
    val perSecondText = if (resource.perSecond > 0) "+${df.format(resource.perSecond)}/s" else "${df.format(resource.perSecond)}/s"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onResourceSelected)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Placeholder for an icon
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual icons later
            contentDescription = stringResource(id = getResourceNameId(resource.id)), // Use string resource for content description
            modifier = Modifier.weight(0.1f)
        )
        Text(
            text = stringResource(id = getResourceNameId(resource.id)),
            modifier = Modifier.weight(0.3f)
        )
        Text(
            text = perSecondText,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.25f)
        )
        Text(
            text = if (resource.capacity < 0) df.format(resource.current) else "${df.format(resource.current)} / ${df.format(resource.capacity)}",
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.35f)
        )
    }
}

