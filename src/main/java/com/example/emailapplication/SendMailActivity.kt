package com.example.emailapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emailapplication.ui.theme.EmailApplicationTheme

class SendMailActivity : ComponentActivity() {
    private lateinit var databaseHelper: EmailDatabaseHelper

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databaseHelper = EmailDatabaseHelper(this)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = Color(0xFF6C5B7B),
                        modifier = Modifier.height(80.dp),
                        title = {
                            Text(
                                text = "Send Mail",
                                fontSize = 28.sp,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    )
                }
            ) {
                openEmailer(this, databaseHelper)
            }
        }
    }
}

@Composable
fun openEmailer(context: Context, databaseHelper: EmailDatabaseHelper) {
    var recevierMail by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF355C7D), Color(0xFF6C5B7B), Color(0xFFC06C84))
                )
            )
            .padding(25.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Receiver Email-Id",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        TextField(
            value = recevierMail,
            onValueChange = { recevierMail = it },
            label = { Text(text = "Email address", color = Color.LightGray) },
            placeholder = { Text(text = "abc@gmail.com", color = Color.Gray) },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFC06C84),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        Text(
            text = "Mail Subject",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        TextField(
            value = subject,
            onValueChange = { subject = it },
            placeholder = { Text(text = "Subject", color = Color.Gray) },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFC06C84),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        Text(
            text = "Mail Body",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.White
        )
        TextField(
            value = body,
            onValueChange = { body = it },
            placeholder = { Text(text = "Body", color = Color.Gray) },
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
            singleLine = false,
            maxLines = 5,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFFC06C84),
                unfocusedIndicatorColor = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (recevierMail.isNotEmpty() && subject.isNotEmpty() && body.isNotEmpty()) {
                    val email = Email(
                        id = null,
                        recevierMail = recevierMail,
                        subject = subject,
                        body = body
                    )
                    databaseHelper.insertEmail(email)
                    error = "Mail Saved"
                } else {
                    error = "Please fill all fields"
                }

                // Start email intent
                val i = Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(recevierMail))
                    putExtra(Intent.EXTRA_SUBJECT, subject)
                    putExtra(Intent.EXTRA_TEXT, body)
                    type = "message/rfc822"
                }
                ctx.startActivity(Intent.createChooser(i, "Choose an Email client: "))
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFC06C84)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Send Email", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        // Display error message if exists
        if (error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = error, color = Color.Yellow, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
    }
}
