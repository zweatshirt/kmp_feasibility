package home.presentation.ui.home_composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonBox(icon: ImageVector, contentDesc: String, iconTint: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable {},
    ) {
        Icon(
            icon,
            contentDesc,
            modifier = Modifier
                //.size(iconSize)
                .padding(4.dp),
            tint = iconTint
        )
    }
}