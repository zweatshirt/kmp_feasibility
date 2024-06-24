package home.presentation.ui.home_composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import ui.theme.inversePrimaryLight
import ui.theme.primaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navigator: Navigator, title: String) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { navigator.pop() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Localized description",
                )
            }
        },
        title = {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = primaryLight
            ) // change in the future to show current screen name
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = inversePrimaryLight,
            titleContentColor = primaryLight
        ),
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "App info"
                )
            }
        }
    )
}