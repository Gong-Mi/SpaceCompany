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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.spacecompany.game.R
import com.spacecompany.game.model.Resource
import com.spacecompany.game.model.ResourceState
import java.text.DecimalFormat

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
            text = resource.id.name,
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
