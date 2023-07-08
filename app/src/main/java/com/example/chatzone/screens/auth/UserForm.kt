package com.example.chatzone.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.chatzone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserForm(
    modifier: Modifier = Modifier,
    onButtonClick: (String, String) -> Unit,
    buttonText: String,
    newUserText: String,
    signUpText: String,
    signUpTextOnClick: () -> Unit
) {
    var phoneNumber by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val isValid = remember(phoneNumber, password) {
        phoneNumber.trim().length == 10 && password.trim().length >= 6
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {

        Text(text = "Please enter your phone number and password",modifier = Modifier.padding(4.dp))


        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {
                phoneNumber = it
            },
            leadingIcon = {
                Icon(Icons.Default.Phone, "phoneNumber")
            },
            label = {
                Text("Phone Number")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier =Modifier
                .fillMaxWidth()

        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, "Password")
            },
            trailingIcon = {
                Icon(
                    painterResource(id= R.drawable.visibility_icon),
                    null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            isPasswordVisible = !isPasswordVisible
                        })
            },

            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text("Password")
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                if(isValid) onButtonClick(phoneNumber,password)

            }),modifier =Modifier
                .fillMaxWidth()


        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onButtonClick(phoneNumber,password)
            },
            enabled = isValid,
            modifier = Modifier.fillMaxWidth()

        ){
            Text(text = buttonText)
        }




        Spacer(modifier = Modifier.height(20.dp))

        val annotatedString = buildAnnotatedString {
            append(newUserText,)

            withStyle(SpanStyle(color= Color(0xFF1E90FF))){

                pushStringAnnotation(signUpText, annotation = signUpText)
                append(signUpText)

            }
        }
        ClickableText(
           annotatedString,
            style = TextStyle(color = MaterialTheme.colorScheme.onSurface)
        ){
                annotatedString.getStringAnnotations(it,it)
                    .firstOrNull()?.let{
                        signUpTextOnClick()
                    }
        }


    }
}