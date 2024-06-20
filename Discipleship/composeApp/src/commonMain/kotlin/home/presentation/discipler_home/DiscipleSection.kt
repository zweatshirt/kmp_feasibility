package home.presentation.discipler_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import profile.domain.model.Disciple
import home.presentation.SectionTitle
import org.jetbrains.compose.resources.painterResource
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.tertiaryLight


/* Author: Zachery Linscott
TODO: Fix padding at the end of the lazy rows
    (can be done by adding a unique pad value to the last item)
*/
// Info about all the disciples
@Composable
fun DiscipleSection() {
    val containerPad = 16.dp
    SectionTitle("Disciples")
    LazyRow() {
        items(disciplesList.size) {
            DiscipleCard(disciplesList[it])
        }
    }
}

// Cards to populate the DiscipleSection composable
@Composable
fun DiscipleCard(disciple: Disciple) {
    Card(modifier = Modifier
        .width(240.dp)
        .height(120.dp)
        .padding(start = 16.dp, end = 8.dp, bottom = 16.dp)
        .clickable {},
        elevation = 12.dp
    ) {
        Column(modifier = Modifier
            .background(primaryContainerLight)
        ) {
            Row(modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (disciple.image != null) {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                            .border(2.dp, tertiaryLight, CircleShape),
                        painter = painterResource(disciple.image),
                        contentDescription = "Icon for ${disciple.firstName}",
                        contentScale = ContentScale.Fit,
                    )
                }
                Box(modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(2.dp)
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier
                            .padding(4.dp),
                        text = "${disciple.firstName}\n${disciple.lastName}",
                        fontSize = 16.sp,
                        color = primaryLight,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}