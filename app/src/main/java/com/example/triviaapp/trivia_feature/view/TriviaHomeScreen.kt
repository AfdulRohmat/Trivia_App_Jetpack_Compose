package com.example.triviaapp.trivia_feature.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.triviaapp.trivia_feature.view.components.CustomButton
import com.example.triviaapp.trivia_feature.view.components.CustomRadioButton
import com.example.triviaapp.trivia_feature.view_model.QuestionViewModel
import com.example.triviaapp.utils.AppColors


@SuppressLint("RememberReturnType")
@Composable
fun TriviaHome(questionViewModel: QuestionViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 8.dp, vertical = 16.dp)
           ,
        horizontalAlignment = Alignment.Start
    ) {

        // VARIABLES AND STATE
        val questionsData = questionViewModel.data.value.data?.toMutableList()
        val indexQuestionNow = remember {
            mutableStateOf(0)
        }
        val correctAnswer = remember {
            mutableStateOf("")
        }


        fun goToNextQuestion() {
            indexQuestionNow.value++
        }

        fun goToPreviousQuestion() {
            if (indexQuestionNow.value != 0) {
                indexQuestionNow.value--
            }
        }


        // COMPOSABLE VIEW
        if (questionViewModel.data.value.loading == true) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppColors.mDarkPurple)
            ) {
                CircularProgressIndicator(color = AppColors.mLightGray)
            }

        } else {
            correctAnswer.value = questionsData!![indexQuestionNow.value].answer
            Log.d("correctAnswer", "correctAnswer: ${correctAnswer.value}")

            // QUESTION TRACKER SECTION
            Row(
                modifier = Modifier
                    .background(
                        color = AppColors.mDarkPurple
                    )
                    .padding(4.dp), verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "Question ${indexQuestionNow.value + 1}/",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.mOffWhite
                )
                Text(
                    text = "${questionsData.size - 1}",
                    fontSize = 30.sp,
                    color = AppColors.mOffWhite
                )

            }

            // DOT LINE
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            Box(modifier = Modifier.padding(vertical = 16.dp)) {
                Canvas(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    drawLine(
                        color = AppColors.mOffWhite,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        pathEffect = pathEffect
                    )
                }
            }

            // QUESTION DISPLAY
            Text(
                text = questionsData[indexQuestionNow.value].question,
                fontSize = 18.sp, color = AppColors.mOffWhite,
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(),
                lineHeight = 22.sp
            )


            // CHOICE OPTION -- RADIO BUTTON
            questionsData[indexQuestionNow.value].choices.forEachIndexed { index, questionItem ->
                CustomRadioButton(questionItem = questionItem, correctAnswer = correctAnswer.value)
            }

            // BUTTON NEXT QUESTION

            if (indexQuestionNow.value != questionsData.size) CustomButton(
                title = "Next Question",
                onTapButton = { goToNextQuestion() }) else Spacer(
                modifier = Modifier.height(0.dp)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            if (indexQuestionNow.value != 0) CustomButton(
                title = "Previous Question",
                onTapButton = { goToPreviousQuestion() }) else Spacer(
                modifier = Modifier.height(0.dp)
            )


        }


    }


}


@Preview(showBackground = true)
@Composable
fun TriviaHomePreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppColors.mDarkPurple)
            .padding(8.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = AppColors.mLightBlue)
        ) {
            Text(text = "Next Question", color = AppColors.mOffWhite)

        }

    }


}