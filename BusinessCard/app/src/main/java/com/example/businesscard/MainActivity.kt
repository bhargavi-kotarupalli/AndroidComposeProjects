package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun MainInformation(fullName: String, role: String, modifier: Modifier = Modifier){
    val logo = painterResource(id = R.drawable.android_logo)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Icon(
            painter = logo,
            contentDescription = null,
            tint = colorResource(id = R.color.cgreen),
            modifier = modifier
                .height(245.dp)
                .width(245.dp)
        )
        Text(
            text = fullName,
            fontSize = 40.sp
        )
        Text(
            text = role,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.cgreen)
        )
    }
}

@Composable
fun ContactInformationEntry(iconId: Int, displayText: String, modifier: Modifier = Modifier){
    val image = painterResource(id = iconId)
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .height(50.dp)
    ) {
        Icon(
            painter = image,
            contentDescription = null,
            tint = colorResource(id = R.color.cgreen),
            modifier = modifier
                .width(50.dp)
                .height(50.dp)
                .padding(end = 16.dp)
        )
        Text(
            text = displayText,
            textAlign = TextAlign.Start,
            minLines = 1,
            maxLines = 1,
            style = LocalTextStyle.current.merge(
                TextStyle(
                    lineHeight = 2.5.em,
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    ),
                    lineHeightStyle = LineHeightStyle(
                        alignment = LineHeightStyle.Alignment.Center,
                        trim = LineHeightStyle.Trim.None
                    )
                )
            ),
            modifier = modifier
                .height(50.dp)
                .width(240.dp)
        )
    }
}

@Composable
fun ContactInformation(contactNumber: String, socialMediaInfo: String, mailInfo: String, modifier: Modifier = Modifier){

    Column (
        modifier = modifier
            .padding(8.dp)
    )
    {
        ContactInformationEntry(
            iconId = R.drawable.baseline_phone_24,
            displayText = contactNumber,
            modifier = modifier
        )
        ContactInformationEntry(
            iconId = R.drawable.baseline_share_24,
            displayText = socialMediaInfo,
            modifier = modifier
        )
        ContactInformationEntry(
            iconId = R.drawable.baseline_email_24,
            displayText = mailInfo,
            modifier = modifier
        )
    }
}

@Composable
fun BusinessCard(name: String = "Casper Talks",
                 role: String = "Android Developer",
                 number: String = "+91 7036911122",
                 socialmedia: String = "caspertalks@linkedin.com",
                 mail: String = "caspertalks@gmail.com",
                 modifier: Modifier = Modifier){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxSize()
    )
    {
        MainInformation(
            fullName = name,
            role = role
        )
        ContactInformation(
            contactNumber = number,
            socialMediaInfo = socialmedia,
            mailInfo = mail
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        BusinessCard()
    }
}