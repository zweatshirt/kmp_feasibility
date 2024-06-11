package disciple.introQuestions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonColors
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.backgroundLight
import ui.theme.primaryDark
import ui.theme.primaryLight
import ui.theme.secondaryLight


@Composable
fun DiscipleQuestions () {
    var bibleKnowledge by remember { mutableStateOf(0f)}


    val experience = setOf("None", "Some", "Decent", "A Lot")
    val selectedExperience = remember { mutableStateListOf<String>() }

    val options = setOf("Yes", "No")
    var discipledBefore by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .background(
                backgroundLight
            )
            .padding(24.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Disciple",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 46.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = primaryLight
        )

        HorizontalDivider(thickness = 8.dp, color = primaryDark)
        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Have you been discipled before?")
        Row {
            options.forEach {
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RadioButton(
                        selected = discipledBefore == it,
                        onClick = { discipledBefore = it },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = secondaryLight
                        )
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(thickness = 2.dp, color = primaryDark)
        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Rate your knowledge of the Bible on a scale of 1 to 10")
        Column {
            Slider(
                value = bibleKnowledge,
                onValueChange = { bibleKnowledge = it },
                colors = SliderDefaults.colors(
                    thumbColor = secondaryLight,
                    activeTrackColor = secondaryLight,
                    inactiveTickColor = secondaryLight,
                ),
                steps = 9,
                valueRange = 0f..10f

            )
            Text(text = bibleKnowledge.toInt().toString())

        }

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(thickness = 2.dp, color = primaryDark)
        Spacer(modifier = Modifier.padding(8.dp))

        Text("Select your experience level sharing your faith")
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            experience.forEach {
                Column(modifier = Modifier.padding(horizontal = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = selectedExperience.contains(it),
                        onCheckedChange = { unchecked ->
                            if (!unchecked) selectedExperience.remove(it)
                            else selectedExperience.add(it)
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = secondaryLight
                        )
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))
        HorizontalDivider(thickness = 2.dp, color = primaryDark)
        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = {},
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