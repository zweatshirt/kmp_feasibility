package profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import profile.domain.model.Disciple
import ui.theme.primaryLight
import ui.theme.secondaryLight

@Composable
fun DisciplerProfileInfo(user: Disciple) {
    // Bible Knowledge
    Text (
        text = "Bible Knowledge",
        modifier = Modifier.padding(horizontal = 12.dp),
        fontSize = 12.sp,
        color = primaryLight,
        fontWeight = FontWeight.Medium
    )
    Slider(
        value = 7F, // need to be changed to not hardcoded
        onValueChange = {},
        modifier = Modifier.padding(horizontal = 24.dp).padding(end = 8.dp),
        enabled = false,
        colors = SliderDefaults.colors(
            disabledThumbColor = secondaryLight,
            disabledActiveTrackColor = secondaryLight,
            disabledInactiveTickColor = secondaryLight,
        ),
        valueRange = 0f..10f
    )

    val bibleKnowledgeNumbers = mutableListOf<String>()
    bibleKnowledgeNumbers.add("0")
    for (i in 1..9) {
        if (i == 7) { // need to be changed to not hardcoded
            bibleKnowledgeNumbers.add(i.toString())
        } else {
            bibleKnowledgeNumbers.add(" ")
        }
    }
    bibleKnowledgeNumbers.add("10")

    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        bibleKnowledgeNumbers.forEach {
            Text( text = it )
        }
    }

    Spacer(modifier = Modifier.padding(8.dp))

    // Discipleship Experience
    OutlinedTextField(
        value = "5 years",
        onValueChange = {},
        enabled = false,
        label = {
            Text(
                text = "Discipleship Experience"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Discipleship Experience"
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

    // Evangelism Experience
    OutlinedTextField(
        value = "Decent",
        onValueChange = {},
        enabled = false,
        label = {
            Text(
                text = "Evangelism Experience"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Evangelism Experience"
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
}