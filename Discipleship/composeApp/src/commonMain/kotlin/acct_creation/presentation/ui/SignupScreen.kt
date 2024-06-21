package acct_creation.presentation.ui

import acct_creation.presentation.viewmodel.SignupScreenViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import dev.gitlive.firebase.auth.FirebaseUser
import ui.theme.backgroundLight
import ui.theme.inverseSurfaceLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight
import viewmodel.ScreenData
import kotlin.math.sign

/* Author: Zachery Linscott
* */

class SignupScreen: Screen {
    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        var signupScreenViewModel = SignupScreenViewModel()
        var currentUser: FirebaseUser? by remember { mutableStateOf(null) }

        if (currentUser == null) {
        // Container for everything on the screen
            Scaffold {
                Column(
                    modifier = Modifier
                        .background(
                            backgroundLight
                        )
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Column {

                        Text(
                            modifier = Modifier
                                .align(Alignment.Start),
                            text = "Signup",
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            color = primaryLight
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        // User first name and last name fields
                        OutlinedTextField(value = signupScreenViewModel.firstName, onValueChange = {
                            firstName ->
                                signupScreenViewModel.updateFirstName(firstName)
                                Logger.i("First name: $firstName")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "First name", color = secondaryLight)
                            }
                        )

                        OutlinedTextField(value = signupScreenViewModel.lastName, onValueChange = {
                            lastName ->
                                signupScreenViewModel.updateLastName(lastName)
                                Logger.i("First name: $lastName")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "Last name", color = secondaryLight)
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        // Login fields
                        OutlinedTextField(value = signupScreenViewModel.email, onValueChange = {
                            email ->
                                signupScreenViewModel.updateEmail(email)
                                Logger.i("Email: $email")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "Email", color = secondaryLight)
                            }
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedTextField(value = signupScreenViewModel.password, onValueChange = {
                            password ->
                                signupScreenViewModel.updatePassword(password)
                                Logger.i("Password: $password")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "Password", color = secondaryLight)
                            })
                        OutlinedTextField(value = signupScreenViewModel.confirmPassword, onValueChange = {
                            confirmPass ->
                                signupScreenViewModel.updateConfirmPassword(confirmPass)
                                Logger.i("Confirmation pass: $confirmPass")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "Confirm password", color = secondaryLight)
                            })
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    // Login button
                    Button(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .width(180.dp),
                        onClick = {
                            Logger.i("Login button click success")
                            currentUser = signupScreenViewModel.firebaseAuth()
                            Logger.i("currentUser value update: $currentUser")
                                  },
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
                        Text(
                            modifier = Modifier.clickable {navigator.push(LoginScreen())},
                            text = "Click here.",
                            color = inverseSurfaceLight,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        } else {
            navigator.push(DorDScreen(ScreenData(false, currentUser)))
        }
    }
}