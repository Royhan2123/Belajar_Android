package com.example.jetpackcompose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.Black
import com.example.jetpackcompose.ui.theme.Grey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextField(
    modifier: Modifier,
    //value state = controller berupa String
    valueState: MutableState<String>,
    enabled: Boolean,
    isSingleLine: Boolean,
    labelId: String,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Number,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    Column {
        TextField(
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
            ),
            //value = Controller
            value = valueState.value,
            onValueChange = {
                valueState.value = it
            },
            // di gunakan jika kalau text field ini dapat di akses dan di interaksikan oleh pengguna
            enabled = enabled,
            label = {
                Text(
                    text = labelId, style = TextStyle(
                        color = Grey, fontSize = 15.sp
                    )
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "")
            },
            // single line cuman berupa satu baris saja ketika pengisian textField
            singleLine = isSingleLine,
            textStyle = TextStyle(
                color = Black,
                fontSize = 17.sp
            ),
            keyboardOptions = KeyboardOptions(
                // imeAction adalah
                imeAction = imeAction,
                keyboardType = keyboardType),
            keyboardActions = onAction,
            modifier = modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 1.dp, color = Black)
                ),
        )
    }
    
}