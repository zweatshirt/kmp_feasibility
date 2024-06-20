package home.presentation.disciple_home

import calendar.domain.model.Meeting
import home.domain.model.Tool
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.avatar
import home.presentation.BottomBar
import home.presentation.TopBar
import profile.data.user.Disciple
import home.presentation.MeetingSection
import home.presentation.ToolsSection
import home.presentation.discipler_home.disciplesList
import viewmodel.ScreenData
import ui.theme.backgroundLight

/* Author: Zachery Linscott */

// Will eventually be dynamically loaded
val disciplerList = mutableListOf(
    Disciple(
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = Res.drawable.avatar,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        firstName = "Z",
        lastName = "L",
        email = "Zach2",
        image = null,
        bio = "I like food"
    ),
)

// will eventually be populated dynamically
val discipleMeetingsList = mutableListOf(
    Meeting(
        date = "7/7/2024",
        time = "10:30AM",
        disciple = disciplesList[2],
        note = "Meep mooping"
    )
)

// will eventually be populated dynamically
val toDoList = mutableListOf(
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
)

val finishedList = listOf(
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
    Tool(
        toolName = "Four Spiritual Laws",
        toolDescription = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        languageList = listOf("English", "German")
    ),
)

data class DiscipleHomeScreen(val screenData: ScreenData) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopBar(navigator = navigator, title = "Christ Companions")
            },
            bottomBar = {  // will refactor
                BottomBar(navigator = navigator, currentScreen = "Home", screenData = screenData)
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .background(backgroundLight)
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                MeetingSection()
                Spacer(modifier = Modifier.padding(12.dp))
                ToDoSection()
                Spacer(modifier = Modifier.padding(12.dp))
                FinishedStudiesSection()
                Spacer(modifier = Modifier.padding(12.dp))
                ToolsSection()
            }
        }
    }
}