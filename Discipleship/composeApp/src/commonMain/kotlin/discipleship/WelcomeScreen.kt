package discipleship

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val resources = listOf(

    Resources(
        resourceName = "How to Disciple",
        resourceType = "Article"
    )
)

@Composable
fun WelcomeScreen() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ){
        Text(
            text = "Welcome",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = "What is Discipleship?",
            modifier = Modifier.align(Alignment.Start),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = "Discipleship is ...",
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Text(
            text = "Resources",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )

        Spacer(modifier = Modifier.padding(8.dp))

        LazyRow {
            items(resources.size) {index ->
                ResourceItem(index)
            }
        }
    }
}

@Composable
fun ResourceItem(
    index: Int
) {
    val resource = resources[index]
    var lastItemPaddingEnd = 0.dp
    if (index == resources.size - 1) {
        lastItemPaddingEnd = 16.dp
    }

    Box(
        modifier = Modifier.padding(start = 16.dp, end = lastItemPaddingEnd)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(Color.Blue)
                .width(250.dp)
                .height(160.dp)
                .clickable {}
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = resource.resourceName,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = resource.resourceType,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}