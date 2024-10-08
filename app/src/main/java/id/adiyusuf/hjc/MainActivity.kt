package id.adiyusuf.hjc

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.adiyusuf.hjc.ui.theme.HelloJetpackComposeTheme

data class User(val name: String, val status: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android", modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.safeContentPadding(),
        color = Color(0x80CC6565),
    )
}

@Composable
fun ContactCard(modifier: Modifier = Modifier, name: String = "", status: String = "offline") {
    Card(
        modifier = modifier
            .safeContentPadding()
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "User Profile",
                modifier = Modifier.size(60.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp)
            ) {
                Text(name)
                Text(status)
            }
        }
    }
}

class UserPreviewParameterProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User>
        get() = sequenceOf(
            User(
                name = "Muhammad Adi Yusuf",
                status = "online"
            ),
            User(
                name = "Mark Zu Berg",
                status = "offline"
            )
        )

}

@Composable
fun GreetingVersion2(name: String, modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(R.drawable.jetpack_compose),
            contentDescription = "Logo Jetpack Compose"
        )
        Column {
            Text(
                text = "Hello $name!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Hello $name!",
            )
        }
    }
}

@Preview(
    name = "Single Preview",
    group = "Greeting",
    showBackground = true,
    widthDp = 100,
    heightDp = 200
)
@Composable
fun GreetingPreview() {
    HelloJetpackComposeTheme {
        Greeting("Android")
    }
}

@Preview(
    name = "Device Without Surface Preview",
    group = "Greeting",
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingDarkMode1Preview() {
    HelloJetpackComposeTheme {
        Greeting("Android")
    }
}

@Preview(
    name = "Device With Surface Preview",
    group = "Greeting",
    device = Devices.PIXEL_3A,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GreetingDarkMode2Preview() {
    HelloJetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) { Greeting("Android") }
    }
}


@Preview(
    name = "Card Preview",
    group = "Card",
)
@Composable
private fun ContactCardCardPreview(
    @PreviewParameter(UserPreviewParameterProvider::class) user: User
) {
    HelloJetpackComposeTheme {
        ContactCard(name = user.name, status = user.status)
    }
}

@Preview(
    name = "Single Preview",
    group = "GreetingV2",
    showBackground = true,
)
@Composable
fun GreetingVersion2Preview() {
    HelloJetpackComposeTheme {
        GreetingVersion2("Android")
    }
}