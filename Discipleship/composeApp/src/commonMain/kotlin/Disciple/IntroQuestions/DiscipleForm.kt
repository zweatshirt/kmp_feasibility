package Disciple.IntroQuestions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun DiscipleQuestions () {
    var BibleKnowledge by remember { mutableStateOf("")}
    var name by remember { mutableStateOf("")}

    val experience = setOf("None", "Some", "Decent", "A Lot")
    val selectedExperience = remember { mutableStateListOf<String>() }

    val options = setOf("Yes", "No")
    var discipledBefore by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.padding(24.dp)
    ) {
        Text(text = "Your Name")
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colors.onPrimary,
                unfocusedLabelColor = MaterialTheme.colors.onPrimary
            )
        )

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
//                        colors = RadioButtonDefaults.colors(
//                            selectedColor = MaterialTheme.colors.onPrimary
//                        )
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Text(text = "Rate your knowledge of the Bible on a scale of 1 to 10")
        OutlinedTextField(
            value = BibleKnowledge,
            onValueChange = {BibleKnowledge = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colors.onPrimary,
                unfocusedLabelColor = MaterialTheme.colors.onPrimary
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text("Select your experience level sharing your faith")
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            experience.forEach {
                Column(modifier = Modifier.padding(horizontal = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Checkbox(
                        checked = selectedExperience.contains(it),
//                        colors = CheckboxDefaults.colors(
//                            checkedColor = MaterialTheme.colors.onPrimary
//                        )
                        onCheckedChange = { unchecked ->
                            if (!unchecked) selectedExperience.remove(it)
                            else selectedExperience.add(it)
                        },
                    )
                    Text(text = it, style = MaterialTheme.typography.caption)
                }
            }
        }
    }
}