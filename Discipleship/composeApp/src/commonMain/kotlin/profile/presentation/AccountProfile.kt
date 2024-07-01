package profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import home.presentation.ui.composables.BottomBar
import home.presentation.ui.composables.TopBar
import home.presentation.ui.discipler_home.disciplesList
import viewmodel.ScreenData
import org.jetbrains.compose.resources.painterResource
import ui.theme.backgroundLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.tertiaryLight

data class AccountProfile(val screenData: ScreenData): Screen {
    override val key = "Profile"
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val user = disciplesList[0]

        Scaffold(
            topBar = {
                TopBar(navigator = navigator, title = "Profile")
            },
            bottomBar = {
                BottomBar(navigator = navigator, currentScreen = "Profile", screenData = screenData)
            }
        ) {
            Column(
                modifier = Modifier
                    .background(backgroundLight)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.padding(22.dp))
                if (user.image != null) {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                            .border(2.dp, tertiaryLight, CircleShape)
                            .size(140.dp)
                            .background(primaryContainerLight)
                            .align(alignment = Alignment.CenterHorizontally),
                        painter = painterResource(user.image),
                        contentDescription = "Icon for ${user.firstName}",
                        contentScale = ContentScale.Fit,
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "ProfilePicture",
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "${user.firstName} ${user.lastName}",
                    fontSize = 32.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Bio: ${user.bio}",
                    fontSize = 16.sp,
                    color = primaryLight,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.padding(12.dp))

                OutlinedTextField(
                    value = user.email,
                    onValueChange = {},
                    enabled = false,
                    label = {
                        Text(
                            text = "Email"
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = "Email"
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        disabledBorderColor = primaryLight,
                        disabledTextColor = primaryLight,
                        disabledLabelColor = primaryLight,
                        disabledLeadingIconColor = primaryLight
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                if (screenData.isDisciple) {
                    DiscipleProfileInfo(user)
                } else {
                    DisciplerProfileInfo(user)
                }

            }
        }
    }
}