package id.adiyusuf.hjc

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.adiyusuf.hjc.ui.theme.HelloJetpackComposeTheme

data class User(val name: String, val status: String)

private val sampleName = listOf(
    "Andre",
    "Desta",
    "Parto",
    "Wendy",
    "Komeng",
    "Raffi Ahmad",
    "Andhika Pratama",
    "Vincent Ryan Rompies"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloJetpackComposeTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        GreetingList(sampleName)
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if (names.isNotEmpty()) {
//        primitive steps
//        Column {
//            for (name in names) {
//                GreetingVersion2(name)
//            }
//        }

        LazyColumn {
            items(names) { name ->
                GreetingVersion2(name)
            }
        }
    } else {
        Text("No people to greet :(")
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
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Online Status",
                    tint = Color.Green,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )

            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(weight = 1f)) {
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
fun GreetingVersion2(name: String) {
    var isExpanded by remember { mutableStateOf(false) }

    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )

    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(animatedSizeDp),
                painter = painterResource(R.drawable.jetpack_compose),
                contentDescription = "Logo Jetpack Compose"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Welcome to My House!",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontStyle = FontStyle.Italic
                    )
                )
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
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
    uiMode = UI_MODE_NIGHT_YES
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
    uiMode = UI_MODE_NIGHT_YES
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

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    name = "My App Preview Light Mode",
    group = "My App"
)
@Preview(
    showBackground = true,
    device = Devices.PIXEL_4,
    name = "My App Preview Dark Mode",
    group = "My App",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun MyAppPreview() {
    HelloJetpackComposeTheme {
        MyApp()
    }
}