package com.example.triviaapp.trivia_feature.view.components

import android.util.Log
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.triviaapp.trivia_feature.model.QuestionModelItem
import com.example.triviaapp.trivia_feature.view_model.QuestionViewModel
import com.example.triviaapp.utils.AppColors


@Composable
fun CustomRadioButton(
    questionItem: String,
    correctAnswer: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        AppColors.mDarkPurple,
                        AppColors.mDarkPurple
                    )
                ),
                shape = RoundedCornerShape(8.dp)
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
            .selectable(selected = true, onClick = {}, role = Role.RadioButton),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        // Radio Button
        RadioButton(
            selected = false,
            onClick = { },
            colors = RadioButtonDefaults.colors(
                unselectedColor = AppColors.mOffWhite,
                selectedColor = Color.Red
            )
        )

        // Text Option Answer
        Text(text = questionItem, fontSize = 16.sp, color = AppColors.mOffWhite)

    }
}

@Composable
fun CustomButton(
    title: String,
    onTapButton: () -> Unit
) {
    Button(
        onClick = { onTapButton() },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.mLightBlue)

    ) {
        Text(text = title, color = AppColors.mOffWhite)

    }
}
