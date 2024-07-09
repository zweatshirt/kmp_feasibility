package home.presentation.ui.disciple_home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.jetpack.navigatorViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import calendar.domain.model.Meeting
import co.touchlab.kermit.Logger
import discipleship.composeapp.generated.resources.Res
import discipleship.composeapp.generated.resources.avatar
import home.data.remote.ToolsApi
import home.data.repository.ToolsRepoImplementation
import home.domain.model.Tool
import home.presentation.ui.composables.BottomBar
import home.presentation.ui.composables.FinishedStudiesSection
import home.presentation.ui.composables.MeetingSection
import home.presentation.ui.composables.ToDoSection
import home.presentation.ui.composables.ToolsSection
import home.presentation.ui.composables.TopBar
import home.presentation.ui.discipler_home.disciplesList
import home.presentation.viewmodel.DiscipleHomeViewModel
import home.presentation.viewmodel.discipleToolsList
import home.presentation.viewmodel.disciplerToolsList
import home.presentation.viewmodel.toDoList
import profile.domain.model.Disciple
import realm.domain.model.DiscipleEntity
import realm.domain.model.UserEntity
import screenmodel.ScreenData
import ui.theme.backgroundLight

/* Author: Zachery Linscott */

// Will eventually be dynamically loaded
// needs to be fetched from DiscipleHomeScreenState
val disciplerList = mutableListOf(
    Disciple(
        uID = "20",
        firstName = "Zach",
        lastName = "Linscott",
        email = "zach7307@gmail.com",
        image = Res.drawable.avatar,
        bio = "I like food"
    ),
    Disciple(
        uID = "20",
        firstName = "Josh",
        lastName = "Ward",
        email = "idk",
        image = null,
        bio = "I like food too"
    ),
    Disciple(
        uID = "20",
        firstName = "Meep",
        lastName = "Moop",
        email = "meepmoop",
        image = null,
        bio = "I like food"
    ),
    Disciple(
        uID = "20",
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

////// will eventually be populated dynamically
//val toDoList = mutableListOf(
//    Tool(
//        "100",
//        name = "Four Spiritual Laws",
//        description = "This is a cool tool.",
//        toolLink = "https://knowgod.com/en/fourlaws/0",
//        totalViews = 100
//    ),
//    Tool(
//        "100",
//        name = "Four Spiritual Laws",
//        description = "This is a cool tool.",
//        toolLink = "https://knowgod.com/en/fourlaws/0",
//        totalViews = 1000
//    ),
//)

val finishedList = listOf(
    Tool(
        "100",
        name = "Four Spiritual Laws",
        description = "This is a cool tool.",
        toolLink = "https://knowgod.com/en/fourlaws/0",
        totalViews = 1000000
    ),
)


data class DiscipleHomeScreen(val screenData: ScreenData): Screen {
    override val key = "DiscipleHome"
    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        // initialize the disciple view model, passing in the tools repository that fetches from
        // the GodTools/KnowGod.com API by use of the ToolsApi
        val discipleHomeViewModel = navigatorViewModel {
            DiscipleHomeViewModel(toolsRepository =
            ToolsRepoImplementation(ToolsApi())
            )
        }

        discipleHomeViewModel.getTools()
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
                // pass in lists for each section as arguments eventually
                // eg ToDoSection(toDoList)
                MeetingSection()
                Spacer(modifier = Modifier.padding(12.dp))
                ToDoSection()
                Spacer(modifier = Modifier.padding(12.dp))
                FinishedStudiesSection()
                Spacer(modifier = Modifier.padding(12.dp))
                ToolsSection(screenData.userEntity!!)
            }
        }
    }
}

