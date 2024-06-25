package acct_creation.presentation.ui

import acct_creation.presentation.viewmodel.LoginScreenViewModel
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.crulogo
import discipleship.composeapp.generated.resources.dove
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import  androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.auth.FirebaseUser
import home.presentation.ui.disciple_home.DiscipleHomeScreen
import home.presentation.viewmodel.DiscipleHomeScreenState
import ui.theme.backgroundLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight
import viewmodel.ScreenData

/* Author: Zach and Josh
* This is the primary login screen for the application.
* TODO: Complete "Forgot password?" functionality, notifying user upon incorrect login info, etc
* */
class LoginScreen: Screen {
    @Composable
    override fun Content() {
        val cru: DrawableResource = Res.drawable.crulogo // image of the Cru logo
        val navigator = LocalNavigator.currentOrThrow
        val loginViewModel = LoginScreenViewModel()
        var currentUser: FirebaseUser? by remember { mutableStateOf(null) }

        // Container for everything on the screen
        // This screen only needs to be displayed if the user
        // is not logged in
        Scaffold {
            if (currentUser == null) {
                Column(
                    modifier = Modifier
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
                        Text(
                            modifier = Modifier
                                .padding(),
                            text = "Login to your account",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color = primaryLight
                        )

                        OutlinedTextField(
                            value = loginViewModel.email,
                            onValueChange = {
                                email ->
                                    loginViewModel.updateEmail(email)
                                    Logger.i("email value change: $email")
                                            },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight,
                                focusedIndicatorColor = primaryContainerLight,
                                cursorColor = secondaryLight
                            ),
                            label = {
                                Text(text = "Email address", color = secondaryLight)
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedTextField(
                            value = loginViewModel.password,
                            onValueChange = { password ->
                                loginViewModel.updatePassword(password) },
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
    //                    onClick = {navigator.push(DorDScreen())},
                        onClick = {
                            Logger.i("Login button click success")
                            currentUser = loginViewModel.firebaseAuth()
                            Logger.i("currentUser value update: $currentUser")
                        },
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
                        onClick = {navigator.push(SignupScreen())},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Gray, contentColor = Color.White
                        ),
                        content = {
                            Text("Signup")
                        }
                    )
                }
            }
            else {
                navigator.push(DiscipleHomeScreen(screenData = ScreenData(false, currentUser)))
            }
        }
    }

}