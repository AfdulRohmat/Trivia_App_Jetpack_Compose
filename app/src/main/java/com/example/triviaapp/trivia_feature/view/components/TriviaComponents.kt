package com.example.triviaapp.trivia_feature.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triviaapp.trivia_feature.model.QuestionModelItem
import com.example.triviaapp.trivia_feature.view_model.QuestionViewModel
import com.example.triviaapp.utils.AppColors


@Composable
fun CustomRadioButton(
    answerOption: String,
    correctAnswer: String,
    selectedValue: String,
    isSelectedItem: (String) -> Boolean,
    onChangeState: (String) -> Unit,


    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .border(
                width = 4.dp, brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mDarkPurple, AppColors.mDarkPurple
                    )
                ), shape = RoundedCornerShape(8.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent)
            .selectable(
                selected = isSelectedItem(answerOption),
                onClick = { onChangeState(answerOption) },
                role = Role.RadioButton
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Radio Button
        RadioButton(
            selected = isSelectedItem(answerOption),
            onClick = { null },
            colors = RadioButtonDefaults.colors(
                unselectedColor = AppColors.mOffWhite,
                selectedColor = if (selectedValue == correctAnswer) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        )

        // Text Option Answer
        Text(text = answerOption, fontSize = 16.sp, color = AppColors.mOffWhite)
    }
}

@Composable
fun CustomButton(
    title: String, onTapButton: () -> Unit
) {
    Button(
        onClick = { onTapButton() },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(34.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.mLightBlue)

    ) {
        Text(text = title, modifier = Modifier.padding(4.dp), color = AppColors.mOffWhite)

    }
}
