package home.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.touchlab.kermit.Logger
import home.domain.model.Tool
import home.presentation.viewmodel.completedList
import home.presentation.viewmodel.discipleToolsList
import home.presentation.viewmodel.disciplerToolsList
import home.presentation.viewmodel.toDoList
import profile.domain.model.Disciple
import realm.domain.model.UserEntity
import ui.theme.primaryCheck
import ui.theme.primaryContainerLight
import ui.theme.primaryLight

/*
* Author: Zachery Linscott
* meetings.Meeting(s) section code
TODO: Fix padding at the end of the lazy rows
    (can be done by adding a unique pad value to the last item)
*/

@Composable
fun ToolsSection(userEntity: UserEntity) {
//    val containerPad = 16.dp
    SectionTitle("Recommended tools")

    // This needs to be fixed asap:
    // Differentiate between populating discipler list and
    // disciple list. As it stands the disciple list is technically
    // populated for both because it is hardcoded in the ToolCard
    if (userEntity.isDisciple == true) {
        LazyRow {
            items(discipleToolsList.size) { index ->
                ToolCard(index, true)
            }
        }
    }
    else {
        LazyRow {
            items(disciplerToolsList.size) { index ->
                ToolCard(index, false)
            }
        }
    }
}



// the time, data, and disciple parameters will need to be redefined to the specific object
// e.g. a disciple.Disciple object
@Composable
fun ToolCard(index: Int, isDisciple: Boolean) {
    var listRemember = mutableStateListOf<Tool>()
    if (isDisciple) {
        listRemember = remember { discipleToolsList }
    }
    else {
        listRemember = remember { disciplerToolsList }
    }
    val tName = listRemember[index].name
    val tDescription =listRemember[index].description
    val uriHandler = LocalUriHandler.current
    val url = listRemember[index].toolLink

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(175.dp)
            .padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
            .background(primaryContainerLight)
            .clickable {
                uriHandler.openUri(url)
            },
        elevation = 12.dp
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                Column(modifier = Modifier.width(200.dp)) {
                    Text(
                        text = tName,
                        fontSize = 24.sp,
                        color = primaryLight,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2
                    )
                }
                Column {
                    ButtonBox(
                        Icons.Rounded.Add,
                        "Icon",
                        primaryCheck,
                        onClick = {
                            if (!toDoList.contains(listRemember[index]))
                                toDoList.add(listRemember.removeAt(index))
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (tDescription != null) {
                    Text(
                        text = tDescription,
                        fontSize = 12.sp,
                        color = primaryLight,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}