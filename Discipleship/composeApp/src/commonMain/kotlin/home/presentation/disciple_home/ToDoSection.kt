package home.presentation.disciple_home

import home.domain.model.Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.presentation.ButtonBox
import home.presentation.SectionTitle
import ui.theme.primaryCheck
import ui.theme.primaryLight

/* Author: Zachery Linscott */

//val iconSize = 30.dp
@Composable
fun ToDoSection() {
    SectionTitle("To-do list")
    LazyRow {
        items(toDoList.size) {
            ToDoCard(toDoList[it])
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ToDoCard(tool: Tool) {
    val cardHeight = 140.dp
    val squareSize = 48.dp

    Card(
        modifier = Modifier
            .width(240.dp)
            .height(cardHeight)
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp),
        elevation = 12.dp,
        backgroundColor = Color.White
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
                    .background(Color.LightGray.copy(alpha = .2f))
                    .weight(1f)
            ) {
                ButtonBox(Icons.Default.Delete, "Trash Icon", Color.Black) {  }
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(
                    Icons.Default.Favorite,
                    "Favorite icon",
                    Color.Red.copy(alpha = .5f)) {  }
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(Icons.Rounded.Check, "Checkmark icon", primaryCheck) {  }
            }

        }
    }
}