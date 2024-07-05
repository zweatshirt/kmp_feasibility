package home.presentation.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import home.domain.model.Tool
import ui.theme.primaryContainerLight
import ui.theme.primaryLight

/*
* Author: Zachery Linscott
* meetings.Meeting(s) section code
TODO: Fix padding at the end of the lazy rows
    (can be done by adding a unique pad value to the last item)
*/

@Composable
fun ToolsSection(toolList: List<Tool>) {
//    val containerPad = 16.dp
    SectionTitle("Recommended tools")
    LazyRow {
        items(toolList.size) {
            ToolCard(toolList[it])
        }
    }
}



// the time, data, and disciple parameters will need to be redefined to the specific object
// e.g. a disciple.Disciple object
@Composable
fun ToolCard(tool: Tool) {
    val tName = tool.name
    val tDescription = tool.description
    val uriHandler = LocalUriHandler.current
    val url = tool.toolLink

    Card(
        modifier = Modifier
            .width(240.dp)
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
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = tName,
                    fontSize = 24.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

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