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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import co.touchlab.kermit.Logger
import realm.data.remote.RealmApi
import realm.data.repository.RealmRepoImpl
import realm.domain.model.UserEntity
import screenmodel.ScreenData
import ui.theme.backgroundLight
import ui.theme.errorLight
import ui.theme.inverseSurfaceLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zach */

class SignupScreen: Screen {
    override val key: ScreenKey = "SignupScreen"


    @Composable
    override fun Content(){
        val navigator = LocalNavigator.currentOrThrow
        val signupScreenViewModel =
            SignupScreenViewModel(
                realmRepository = RealmRepoImpl(
                    RealmApi()
                )
            )
        var currentUser: io.realm.kotlin.mongodb.User? by remember { mutableStateOf(null) }
        var userEntity: UserEntity? by remember { mutableStateOf(null) }
        var passwordVisible by remember { mutableStateOf(false) }
        passwordVisible = false

        if (currentUser == null) {
        // Container for everything on the screen
            Scaffold {
                Column(
                    modifier = Modifier
                        .background(
                            backgroundLight
                        )
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
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
                        OutlinedTextField(value = signupScreenViewModel.vmFirstName, onValueChange = {
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

                        if (signupScreenViewModel.signupValidation.firstNameValidationResult.errorMessage != null) {
                            Text(
                                text = "${signupScreenViewModel.signupValidation.firstNameValidationResult.errorMessage}",
                                color = errorLight
                            )
                        } else {
                            //Spacer(modifier = Modifier.padding(8.dp))
                        }

                        OutlinedTextField(value = signupScreenViewModel.vmLastName, onValueChange = {
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

                        if (signupScreenViewModel.signupValidation.lastNameValidationResult.errorMessage != null) {
                            Text(
                                text = "${signupScreenViewModel.signupValidation.lastNameValidationResult.errorMessage}",
                                color = errorLight
                            )
                        } else {
                            //Spacer(modifier = Modifier.padding(8.dp))
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        // Login fields
                        OutlinedTextField(value = signupScreenViewModel.vmEmail, onValueChange = {
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

                        if (signupScreenViewModel.signupValidation.emailValidationResult.errorMessage != null) {
                            Text(
                                text = "${signupScreenViewModel.signupValidation.emailValidationResult.errorMessage}",
                                color = errorLight
                            )
                        } else {
                            //Spacer(modifier = Modifier.padding(8.dp))
                        }

                        Spacer(modifier = Modifier.padding(8.dp))

                        OutlinedTextField(value = signupScreenViewModel.vmPassword, onValueChange = {
                            password ->
                                signupScreenViewModel.updatePassword(password)
                                Logger.i("Password: $password")
                        },
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = primaryLight, focusedIndicatorColor = primaryContainerLight
                            ),
                            label = {
                                Text(text = "Password", color = secondaryLight)
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            )
                        if (signupScreenViewModel.signupValidation.passwordValidationResult.errorMessage != null) {
                            Text(
                                text = "${signupScreenViewModel.signupValidation.passwordValidationResult.errorMessage}",
                                color = errorLight
                            )
                        } else {
                            //Spacer(modifier = Modifier.padding(8.dp))
                        }

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
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            )
                        if (signupScreenViewModel.signupValidation.confirmPasswordValidationResult.errorMessage != null) {
                            Text(
                                modifier = Modifier.padding(12.dp),
                                text = "${signupScreenViewModel.signupValidation.confirmPasswordValidationResult.errorMessage}",
                                color = errorLight
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    // Login button
                    Button(modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .width(180.dp),
                        onClick = {
                            Logger.i("Login button click success")
                            signupScreenViewModel.validationOnSubmit()
                            if (signupScreenViewModel.signupValidation.isValidated) {
                                Logger.i("Signup validated")
                                currentUser = signupScreenViewModel.atlasAuth()
                                if (currentUser != null) {
                                    Logger.i("User authorized by Atlas successfully")

                                    signupScreenViewModel.initRealm()
                                    signupScreenViewModel.createUserObject()
                                    userEntity = signupScreenViewModel.writeUserToDb()
                                    navigator.push(DorDScreen(ScreenData(userEntity)))
                                }
                                // eventually give user feedback on failed sign up
                                // likely due to lack of internet connection
                                else throw Exception("Failed to initialize Realm, app user is null")
                                Logger.i("currentUser value update: $currentUser")
                            }
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
        }
        else {
            navigator.push(DorDScreen(ScreenData(userEntity)))
        }
    }
}