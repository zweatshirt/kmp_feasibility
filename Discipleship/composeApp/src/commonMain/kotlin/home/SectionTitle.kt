package home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zachery Linscott */

@Composable
fun SectionTitle(titleString: String) {
    val containerPad = 16.dp
    Row(modifier = Modifier.fillMaxWidth().padding(containerPad),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = titleString,
            fontSize = 28.sp,
            color = primaryLight
        )
        Text(modifier = Modifier
            .clickable {},
            text = "View all",
            color = secondaryLight,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.End)
    }
}