import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/* Author: Zachery Linscott
*
* This screen asks a user whether they are a disciple or discipler upon first sign up
 */
@Composable
fun DorDScreen() {
    val fSize = 30.sp // font size for all font on screen
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            buildAnnotatedString {
                append("Are you a ")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold)
                ) {
                    append("Disciple")
                }
                append(", or a\n\n")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.SemiBold)
                ) {
                    append("Discipler")
                }
                append("?")
            },
            modifier = Modifier
                .padding(top = 40.dp),
            fontSize = fSize
        )

        Card(modifier = Modifier.width(200.dp), backgroundColor = Color.Black){
            Text(text = "Disciple", fontSize = fSize, color = Color.White)
        }

        Card(modifier = Modifier.width(200.dp), backgroundColor = Color.Black) {
            Text(text = "Discipler", fontSize = fSize, color = Color.White)
        }
    }
}