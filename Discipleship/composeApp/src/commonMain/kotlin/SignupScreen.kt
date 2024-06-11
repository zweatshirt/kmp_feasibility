import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.backgroundLight
import ui.theme.inversePrimaryLight
import ui.theme.inverseSurfaceLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zachery Linscott
* TODO: Change color of input fields to blue or something other than purple
* */

@Composable
fun SignupScreen() {

    // Container for everything on the screen
    Column(modifier = Modifier
        .background(
            backgroundLight
        )
        .fillMaxSize()
        .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column {
        Text(modifier = Modifier
            .align(Alignment.Start),
            text = "Signup",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = primaryLight
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // Login fields
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "First name", color = secondaryLight)
        })

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Last name", color = secondaryLight)
        })
        Spacer(modifier = Modifier.padding(8.dp))
        // Login fields
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Email", color = secondaryLight)
        })

        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Password", color = secondaryLight)
        })
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Confirm password", color = secondaryLight)
        })
        }

        Spacer(modifier = Modifier.padding(16.dp))

        // Login button
        Button(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(180.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primaryContainerLight, contentColor = primaryLight
            ),
            content = {
                Text("Create account", color = primaryLight)
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        // Needs to route back to the Login screen
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(text = "Already have an account?", fontSize = 16.sp, color = primaryLight)
            Text(modifier = Modifier.clickable {}, text = "Click here.", color = inverseSurfaceLight, fontSize = 16.sp)
        }
    }
}