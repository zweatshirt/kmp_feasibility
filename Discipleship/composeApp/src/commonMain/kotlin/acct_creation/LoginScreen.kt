package acct_creation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.crulogo
import discipleship.composeapp.generated.resources.dove
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ui.theme.backgroundLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zachery Linscott
*
* This is the primary login screen for the application.
* TODO: Change color of input fields to blue or something other than purple
* */

@Composable
fun LoginScreen() {
    val cru: DrawableResource = Res.drawable.crulogo // image of the Cru logo

    // Container for everything on the screen
    Column(modifier = Modifier
        .background(
            backgroundLight
        )
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painterResource(cru),
            contentDescription = "Cru logo",
            modifier = Modifier
                .size(200.dp)
        )

        Text(
            text = "Christ Companions",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Default,
            color = primaryLight
        )
        Image(
            modifier = Modifier.size(120.dp),
            painter = painterResource(Res.drawable.dove),
            contentDescription = "Dove"
        )

        Spacer(modifier = Modifier.padding(16.dp))

        // Login fields
        Column {
            Text(modifier = Modifier
                .padding(),
                text = "Login to your account",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = primaryLight
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    textColor = primaryLight,
                    focusedIndicatorColor = primaryContainerLight,
                    cursorColor = secondaryLight),
                label = {
                    Text(text = "Email address", color = secondaryLight)
                }
            )

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                colors = TextFieldDefaults.textFieldColors(
                    textColor = primaryLight,
                    focusedIndicatorColor = primaryContainerLight,
                    cursorColor = secondaryLight
                ),
                label = {
                    Text(text = "Password", color = secondaryLight)
                }
            )

            TextButton(modifier = Modifier.align(Alignment.End), onClick = {}) {
                Text(text = "Forgot password?", color = secondaryLight)
            }
        }

        // Login button
        Button(
            modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(100.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = primaryContainerLight, contentColor = secondaryLight
            ),
            content = {
                Text("Login", color = secondaryLight)
            }
        )

        Spacer(modifier = Modifier.padding(bottom = 8.dp))
        Divider(color = secondaryLight)
        Text(text = "or", color = secondaryLight)

        // Signup button (needs to go to SignupScreen on click)
        Button(modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .width(100.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Gray, contentColor = Color.White
            ),
            content = {
                Text("Signup")
            }
        )
    }
}