package acct_creation.presentation.ui

import acct_creation.presentation.viewmodel.DisciplerFormViewModel
import acct_creation.presentation.viewmodel.SignupScreenViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import home.presentation.discipler_home.DisciplerHomeScreen
import viewmodel.ScreenData
import ui.theme.backgroundLight
import ui.theme.errorLight
import ui.theme.primaryDark
import ui.theme.primaryLight
import ui.theme.secondaryLight

/* Author: Josh */
data class DisciplerForm(val screenData: ScreenData): Screen {
    @Composable
    override fun Content() {
        var bibleKnowledge by remember { mutableStateOf(1f) }
        var years by remember { mutableStateOf("") }

        val experience = setOf("None", "Some", "Decent", "A Lot")
        val selectedExperience = remember { mutableStateListOf<String>() }

        val options = setOf("Yes", "No")
        var discipledBefore by remember { mutableStateOf("") }

        val navigator = LocalNavigator.currentOrThrow
        val disciplerScreenViewModel = DisciplerFormViewModel()

        Scaffold {

            Column(
                modifier = Modifier
                    .background(
                        backgroundLight
                    )
                    .padding(24.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Discipler",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = primaryLight
                )

                HorizontalDivider(thickness = 8.dp, color = primaryDark)
                Spacer(modifier = Modifier.padding(8.dp))


                Text(text = "Have you discipled someone before?")
                Row {
                    options.forEach {
                        Column(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            RadioButton(
                                selected = discipledBefore == it,
                                onClick = {
                                    discipledBefore = it
                                    disciplerScreenViewModel.haveDiscipled = it
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = secondaryLight
                                )
                            )
                            Text(text = it, style = MaterialTheme.typography.caption)
                        }
                    }
                }
                if (disciplerScreenViewModel.haveDiscipledResult.errorMessage != null) {
                    Text(
                        text = "${disciplerScreenViewModel.haveDiscipledResult.errorMessage}",
                        color = errorLight
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider(thickness = 2.dp, color = primaryDark)
                Spacer(modifier = Modifier.padding(8.dp))

                Text(text = "How many years of experience do you have discipling?")
                Row(
                    modifier = Modifier.padding(12.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .weight(2f)
                            .align(Alignment.CenterVertically),
                        value = disciplerScreenViewModel.yearsOfExperience,
                        onValueChange = {
                            disciplerScreenViewModel.yearsOfExperience = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = secondaryLight,
                            focusedLabelColor = secondaryLight,
                            cursorColor = secondaryLight,
                            backgroundColor = backgroundLight
                        )
                    )
                    Text(
                        text = "years",
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                            .padding(12.dp),
                        fontSize = 16.sp,
                    )
                }
                if (disciplerScreenViewModel.yearsOfExperienceResult.errorMessage != null) {
                    Text(
                        text = "${disciplerScreenViewModel.yearsOfExperienceResult.errorMessage}",
                        color = errorLight
                    )
                }


                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider(thickness = 2.dp, color = primaryDark)
                Spacer(modifier = Modifier.padding(8.dp))

                Text(text = "Rate your knowledge of the Bible on a scale of 1 to 10")
                Column {
                    Slider(
                        value = bibleKnowledge,
                        onValueChange = {
                            bibleKnowledge = it
                            disciplerScreenViewModel.bibleKnowledge = it.toInt()
                        },
                        colors = SliderDefaults.colors(
                            thumbColor = secondaryLight,
                            activeTrackColor = secondaryLight,
                            inactiveTickColor = secondaryLight,
                        ),
                        steps = 9,
                        valueRange = 1f..10f

                    )
                    Text(text = bibleKnowledge.toInt().toString())

                }

                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider(thickness = 2.dp, color = primaryDark)
                Spacer(modifier = Modifier.padding(8.dp))

                Text("Select your experience level sharing your faith")
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    experience.forEach {
                        Column(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Checkbox(
                                checked = selectedExperience.contains(it),
                                onCheckedChange = { unchecked ->
                                    if (!unchecked) selectedExperience.remove(it)
                                    else {
                                        selectedExperience.removeAll(selectedExperience)
                                        selectedExperience.add(it)
                                        disciplerScreenViewModel.evangalismExperience = it
                                    }
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = secondaryLight
                                )
                            )
                            Text(text = it, style = MaterialTheme.typography.caption)
                        }
                    }
                }
                if (disciplerScreenViewModel.evanglismExperienceResult.errorMessage != null) {
                    Text(
                        text = "${disciplerScreenViewModel.evanglismExperienceResult.errorMessage}",
                        color = errorLight
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))
                HorizontalDivider(thickness = 2.dp, color = primaryDark)
                Spacer(modifier = Modifier.padding(8.dp))

                Button(
                    onClick = {
                        if (disciplerScreenViewModel.disciplerFormIsValid()) {
                            navigator.replaceAll(DisciplerHomeScreen(screenData))
                        }
                    },
                    modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = primaryLight,
                        contentColor = backgroundLight
                    )
                ) {
                    Text("Submit")
                }
            }
        }
    }
}
