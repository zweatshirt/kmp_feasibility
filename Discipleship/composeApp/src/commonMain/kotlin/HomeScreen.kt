import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import discipleship.composeapp.generated.resources.Res
import ui.theme.backgroundLight
import ui.theme.onSecondaryContainerLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

// Will eventually be dynamically loaded
val disciplesList = mutableListOf(
    Disciple(
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Z",
        lastName = "L",
        email = "Zach2",
        image = null,
        bio = "I like food"
    ),
    )

// will eventually be populated dynamically
val meetingsList = mutableListOf(
    Meeting(
        date = "7/7/2024",
        time = "10:30AM",
        disciple = disciplesList[2]
    )
)

@Composable
fun HomeScreen() {
    Column(modifier = Modifier
        .background(backgroundLight)
    ) {
        MeetingSection()
        DiscipleSection()
    }
}

/* Meeting(s) section code */

@Composable
fun MeetingSection() {
    val containerPad = 16.dp
    Row(modifier = Modifier.fillMaxWidth().padding(containerPad),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Upcoming meetings",
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
    LazyRow(modifier = Modifier.padding(containerPad)) {
        items(meetingsList.size) {
            MeetingCard(meetingsList[it])
        }
    }

}

// the time, data, and disciple parameters will need to be redefined to the specific object
// e.g. a Disciple object
@Composable
fun MeetingCard(meeting: Meeting) {
    val meetDisc = meeting.disciple
    Card(modifier = Modifier
        .width(160.dp)
        .height(140.dp)
        .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
        .clickable {},
        elevation = 12.dp
    ) {
        Column(modifier = Modifier.background(primaryContainerLight).padding(16.dp)) {
            Row() {
                Text(
                    text = "${meeting.date} ${meeting.time}",
                    fontSize = 16.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.Bold
                )
                if (meetDisc.image != null) {
                    Icon(
                        imageVector = meetDisc.image,
                        contentDescription = "Icon for ${meetDisc.firstName}"
                    )
                }
            }
            Text(
                text = "with ${meetDisc.firstName} ${meetDisc.lastName}",
                fontSize = 16.sp,
                color = primaryLight,
                fontWeight = FontWeight.Bold
            )

        }
    }
}


/* Disciple(s) Section code*/



// Info about all the disciples
@Composable
fun DiscipleSection() {
    val containerPad = 16.dp
    Row(modifier = Modifier.fillMaxWidth().padding(containerPad),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Disciples",
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
    LazyRow(modifier = Modifier.padding(containerPad)) {
        items(disciplesList.size) {
            DiscipleCard(disciplesList[it])
        }
    }
}

// Cards to populate the DiscipleSection composable
@Composable
fun DiscipleCard(disciple: Disciple) {
    Card(modifier = Modifier
        .width(160.dp)
        .height(140.dp)
        .padding(start = 8.dp, end = 8.dp, bottom = 16.dp)
        .clickable {},
        elevation = 12.dp
    ) {
        Column(modifier = Modifier.background(primaryContainerLight)) {
            Row(modifier = Modifier.padding(16.dp)) {
                if (disciple.image != null) {
                    Icon(
                        imageVector = disciple.image,
                        contentDescription = "Icon for ${disciple.firstName}"
                    )
                }
                Text(
                    text = "${disciple.firstName} ${disciple.lastName}",
                    fontSize = 16.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.Bold
                )
                if (disciple.image != null) {
                    Icon(
                        imageVector = disciple.image,
                        contentDescription = "Icon for ${disciple.firstName}"
                    )
                }
            }

        }
    }
}

