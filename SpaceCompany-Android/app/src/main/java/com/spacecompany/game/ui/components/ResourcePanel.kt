package com.spacecompany.game.ui.components

import androidx.compose.ui.res.stringResource
import androidx.annotation.StringRes

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
    LazyColumn(modifier = modifier) {
        items(resources.filter { it.unlocked || it.current > 0 || it.id == Resource.METAL || it.id == Resource.WOOD || it.id == Resource.GEM }) { resource ->
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
            contentDescription = resource.id.name,
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


@Preview(showBackground = true)
@Composable
fun PreviewResourcePanel() {
    val previewResources = listOf(
        ResourceState(id = Resource.METAL, current = 12345.67, perSecond = 10.1, capacity = 50000.0, unlocked = true),
        ResourceState(id = Resource.GEM, current = 50.8, perSecond = 2.5, capacity = 500.0, unlocked = true),
        ResourceState(id = Resource.OIL, current = 200.0, perSecond = -5.0, capacity = 1000.0, unlocked = true),
        ResourceState(id = Resource.SCIENCE, current = 999.0, perSecond = 100.0, capacity = -1.0, unlocked = true) // Unlimited
    )
    ResourcePanel(resources = previewResources, onResourceSelected = {})
}
