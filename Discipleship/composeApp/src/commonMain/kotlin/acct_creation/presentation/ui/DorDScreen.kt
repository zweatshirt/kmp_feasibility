package acct_creation.presentation.ui

import acct_creation.presentation.viewmodel.DorDScreenViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.internal.getIdentifierOrNull
import io.realm.kotlin.schema.RealmStorageType
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmUUID
import realm.data.remote.RealmApi
import realm.data.repository.RealmRepoImpl
import realm.domain.model.DiscipleEntity
import realm.domain.model.DisciplerEntity
import screenmodel.ScreenData
import ui.theme.backgroundLight
import ui.theme.onSecondaryContainerLight
import ui.theme.primaryContainerLight
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Zachery Linscott
*
* This screen asks a user whether they are a disciple or discipler upon first sign up
 */

class DorDScreen(val screenData: ScreenData): Screen {
    override val key: ScreenKey = "DorDScreen"
    val dOrDScreenViewModel = DorDScreenViewModel(
        realmRepository = RealmRepoImpl(
            realmApi = RealmApi()
        )
    )
    @Composable
    override fun Content() {
        val fSize = 30.sp // font size for all font on screen
        val fSizeTwo = 20.sp
        val navigator = LocalNavigator.currentOrThrow

        Scaffold {

            Column(
                modifier = Modifier
                    .background(backgroundLight)
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                // You have to build a string
                // if you want to style different parts of the string
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = secondaryLight
                            )
                        ) {
                            append("Are you a ")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = primaryLight,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold
                            ),
                        ) {
                            append("Disciple")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = secondaryLight
                            )
                        ) {
                            append(", or a\n\n")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = primaryLight,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append("Discipler")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = secondaryLight
                            )
                        ) {
                            append("?")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    fontSize = fSize
                )

                Spacer(modifier = Modifier.padding(16.dp))

                // This column contains the cards that the user can click on
                // where they determine if they are a disciple or discipler
                Column(verticalArrangement = Arrangement.Center) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                val discipleEntity = dOrDScreenViewModel.initEntity(isDisciple = true)
                                val screenData = ScreenData(discipleEntity)
                                navigator.push(DiscipleForm(screenData))
                            }, // Needs to route to the disciple intro screens
                        border = BorderStroke(2.dp, primaryLight),
                        colors = CardDefaults.cardColors(
                            primaryContainerLight
                        ),
                        elevation = CardDefaults.cardElevation(16.dp)
                    ) {
                        Column {
                            Row(modifier = Modifier.padding(16.dp)) {
                                Icon(
                                    modifier = Modifier
                                        .padding(6.dp),
                                    imageVector = Icons.Outlined.Face,
                                    contentDescription = "Disciple icon"
                                )
                                Text(
                                    text = "Disciple",
                                    fontSize = fSize,
                                    color = primaryLight,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                modifier = Modifier.padding(
                                    end = 16.dp,
                                    start = 16.dp,
                                    bottom = 12.dp
                                ),
                                text = "Disciples are dedicated to learning about " +
                                        "Jesus Christ and the gospel, and take on the role of the learner.",
                                color = onSecondaryContainerLight,
                                fontSize = fSizeTwo
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(16.dp))

                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable {
                                val disciplerEntity = dOrDScreenViewModel.initEntity(isDisciple = false)
                                // write to database
                                val screenData = ScreenData(disciplerEntity)
                                navigator.push(DisciplerForm(screenData))
                            }, // needs to route to discipler intro screens
                        border = BorderStroke(2.dp, primaryLight),
                        colors = CardDefaults.cardColors(
                            primaryContainerLight
                        ),
                        elevation = CardDefaults.cardElevation(16.dp)
                    ) {
                        Column {
                            Row(modifier = Modifier.padding(16.dp)) {
                                Icon(
                                    modifier = Modifier
                                        .padding(6.dp),
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Discipler icon"
                                )
                                Text(
                                    text = "Discipler",
                                    fontSize = fSize,
                                    color = primaryLight,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(
                                modifier = Modifier.padding(
                                    end = 16.dp,
                                    start = 16.dp,
                                    bottom = 12.dp
                                ),
                                text = "Disciplers are dedicated to teaching others about Jesus Christ " +
                                        "and the gospel, and typically take on one or more disciples.",
                                color = onSecondaryContainerLight,
                                fontSize = fSizeTwo
                            )
                        }
                    }
                }

            }
        }
    }
}