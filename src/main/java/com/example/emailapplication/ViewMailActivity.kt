package com.example.emailapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ViewMailActivity : ComponentActivity() {
    private lateinit var emailDatabaseHelper: EmailDatabaseHelper

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emailDatabaseHelper = EmailDatabaseHelper(this)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(
                        backgroundColor = Color(0xFF37474F),
                        modifier = Modifier.height(80.dp),
                        title = {
                            Text(
                                text = "View Mails",
                                fontSize = 28.sp,
                                color = Color.White,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center,
                            )
                        }
                    )
                }
            ) {
                // Fetch all emails to display
                val emails: List<Email> = emailDatabaseHelper.getAllEmails()

                // Display emails
                EmailListSample(emails)
            }
        }
    }
}

@Composable
fun EmailListSample(emails: List<Email>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF455A64), Color(0xFF546E7A), Color(0xFF607D8B))
                )
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp) // Adjusted spacing between items
    ) {
        items(emails) { email ->
            EmailCard(email)
        }
    }
}

@Composable
fun EmailCard(email: Email) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp), // Increased corner radius
        elevation = 6.dp // Increased shadow for a more prominent effect
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFCFD8DC)) // Light background for card content
        ) {
            // Decorative icon
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your actual email icon resource
                contentDescription = null,
                modifier = Modifier.size(32.dp).padding(bottom = 8.dp),
                tint = Color(0xFF37474F) // Darker color for icon
            )

            Text(
                text = "Receiver: ${email.recevierMail}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Subject: ${email.subject}",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Body: ${email.body}",
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 3, // Increased max lines for body preview
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
