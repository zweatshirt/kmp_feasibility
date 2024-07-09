package home.presentation.ui.composables

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import home.presentation.viewmodel.completedList
import home.presentation.viewmodel.discipleToolsList
import home.presentation.viewmodel.disciplerToolsList
import home.presentation.viewmodel.toDoList
import ui.theme.primaryCheck
import ui.theme.primaryLight

/* Author: Zachery Linscott */

//val iconSize = 30.dp
@Composable
fun ToDoSection() {
    SectionTitle("To-do list")
    LazyRow {
        items(toDoList.size) { index ->
            ToDoCard(index)
        }
    }
}


@Composable
private fun ToDoCard(index: Int) {
    val cardHeight = 140.dp
    val tName = toDoList[index].name
    val toDoListRemember = remember { toDoList }
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
                    text = tName, // replace with dynamic loading
                    fontSize = 20.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2
                )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = .2f))
                    .weight(1f)
            ) {
                ButtonBox(
                    Icons.Default.Delete,
                    "Trash Icon",
                    Color.Black,
                    onClick = {
                        discipleToolsList.add(toDoListRemember.removeAt(index))
                    })
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(
                    Icons.Default.Favorite,
                    "Favorite icon",
                    Color.Red.copy(alpha = .5f),
                    onClick = {})
                VerticalDivider(thickness = 2.dp, color = primaryLight, modifier = Modifier.padding(2.dp))
                ButtonBox(
                    Icons.Rounded.Check,
                    "Checkmark icon",
                    primaryCheck,
                    onClick = {
                        completedList.add(toDoList[index])
                        toDoListRemember.removeAt(index)
                    }
                )
            }

        }
    }
}