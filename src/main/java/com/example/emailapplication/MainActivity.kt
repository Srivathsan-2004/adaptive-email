package com.example.emailapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF6A1B9A), Color(0xFF8E24AA), Color(0xFFBA68C8))
                        )
                    )
            ) {
                Email(this)
            }
        }
    }
}

@Composable
fun Email(context: Context) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Padding for the column
    ) {
        Text(
            text = "Welcome to Your Email App!",
            modifier = Modifier.padding(top = 40.dp), // Adjusted top padding
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Decorative element with rounded box and vibrant color
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(color = Color(0xFF4db6ac), shape = RoundedCornerShape(10.dp)) // Decorative box
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_screen),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                context.startActivity(
                    Intent(context, SendMailActivity::class.java)
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009688)), // Vibrant teal button color
            modifier = Modifier
                .fillMaxWidth() // Make the button full width
                .padding(horizontal = 16.dp) // Add horizontal padding
                .height(50.dp) // Increase button height for better look
        ) {
            Text(
                text = "Send Email",
                modifier = Modifier.padding(5.dp),
                color = Color.White, // White text for better contrast
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                context.startActivity(
                    Intent(context, ViewMailActivity::class.java)
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF009688)), // Vibrant teal button color
            modifier = Modifier
                .fillMaxWidth() // Make the button full width
                .padding(horizontal = 16.dp) // Add horizontal padding
                .height(50.dp) // Increase button height for better look
        ) {
            Text(
                text = "View Emails",
                modifier = Modifier.padding(5.dp),
                color = Color.White, // White text for better contrast
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        }
    }
}
