package home.disciple_home

import Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite

import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.ButtonBox
import home.SectionTitle
import ui.theme.inversePrimaryLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
/* Author: Zachery Linscott */

//val iconSize = 30.dp
@Composable
fun ToDoSection() {
    SectionTitle("To-do list")
    ToDoCard(Tool("four", "wow", "link", listOf("en")))
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ToDoCard(tool: Tool) {
    val cardHeight = 140.dp
    val squareSize = 48.dp

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { squareSize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1) // Maps anchor points (in px) to states

    Card(
        modifier = Modifier
            .width(240.dp)
            .height(cardHeight)
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
        elevation = 12.dp,
        backgroundColor = primaryContainerLight
    ) {
        Column(
        ) {

                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 36.dp ),
                    text = tool.toolName, // replace with dynamic loading
                    fontSize = 20.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.SemiBold
                )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
                    .background(inversePrimaryLight)
                    .weight(1f)
            ) {
                ButtonBox(Icons.Default.Delete, "Trash Icon") {  }
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(Icons.Default.Favorite, "Favorite icon") {  }
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(Icons.Default.Check, "Checkmark icon") {  }
            }

        }
    }
}