package org.zionprayerhouse.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF10264A)
private val Gold = Color(0xFFC99A36)
private val Cream = Color(0xFFF7F5EF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ZionApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZionApp() {
    var english by remember { mutableStateOf(false) }
    var selected by remember { mutableIntStateOf(0) }
    val labels = if (english)
        listOf("Home", "Bible", "Sermons", "Events", "Prayer", "More")
    else
        listOf("హోమ్", "బైబిల్", "ప్రసంగాలు", "కార్యక్రమాలు", "ప్రార్థన", "మరిన్ని")

    MaterialTheme(colorScheme = lightColorScheme(
        primary = Navy, secondary = Gold, background = Cream, surface = Color.White
    )) {
        Scaffold(
            containerColor = Cream,
            topBar = {
                TopAppBar(
                    title = {
                        Column {
                            Text("ZION PRAYER HOUSE", fontWeight = FontWeight.Bold, fontSize = 17.sp)
                            Text(if (english) "Established 1985" else "సీయోను ప్రార్థన మందిరం • స్థాపన 1985",
                                fontSize = 11.sp, color = Color.Gray)
                        }
                    },
                    actions = {
                        TextButton(onClick = { english = !english }) {
                            Text(if (english) "తెలుగు" else "English", color = Navy)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    val icons = listOf(Icons.Default.Home, Icons.Default.MenuBook,
                        Icons.Default.PlayCircle, Icons.Default.Event, Icons.Default.Favorite,
                        Icons.Default.MoreHoriz)
                    labels.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selected == index,
                            onClick = { selected = index },
                            icon = { Icon(icons[index], contentDescription = label) },
                            label = { Text(label, fontSize = 10.sp) }
                        )
                    }
                }
            }
        ) { padding ->
            when (selected) {
                0 -> HomeScreen(english, Modifier.padding(padding))
                1 -> InfoScreen(if (english) "Holy Bible" else "పరిశుద్ధ బైబిల్",
                    if (english) "Bible reading content will be added here." else "బైబిల్ పఠనానికి సంబంధించిన కంటెంట్ ఇక్కడ చేర్చబడుతుంది.", Modifier.padding(padding))
                2 -> InfoScreen(if (english) "Sermons" else "ప్రసంగాలు",
                    if (english) "Audio and video messages will appear here." else "ఆడియో మరియు వీడియో ప్రసంగాలు ఇక్కడ కనిపిస్తాయి.", Modifier.padding(padding))
                3 -> InfoScreen(if (english) "Church Events" else "చర్చి కార్యక్రమాలు",
                    if (english) "Upcoming worship services and events will appear here." else "రాబోయే ఆరాధనలు మరియు కార్యక్రమాలు ఇక్కడ కనిపిస్తాయి.", Modifier.padding(padding))
                4 -> InfoScreen(if (english) "Prayer Requests" else "ప్రార్థనా విజ్ఞప్తులు",
                    if (english) "A prayer request form will be connected in the next phase." else "ప్రార్థనా విజ్ఞప్తుల ఫారమ్‌ను తదుపరి దశలో అనుసంధానిస్తాం.", Modifier.padding(padding))
                else -> InfoScreen(if (english) "More" else "మరిన్ని",
                    if (english) "About the church • Contact • Notifications • Admin" else "చర్చి వివరాలు • సంప్రదింపు • ప్రకటనలు • అడ్మిన్", Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun HomeScreen(english: Boolean, modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item {
            Card(shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Navy)) {
                Column(Modifier.fillMaxWidth().padding(22.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(if (english) "WELCOME" else "స్వాగతం", color = Gold, fontWeight = FontWeight.Bold)
                    Text(if (english) "Zion Prayer House" else "సీయోను ప్రార్థన మందిరం",
                        color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Text(if (english) "The way, the truth and the life" else "మార్గము, సత్యము, జీవము",
                        color = Color.White.copy(alpha = .88f), fontSize = 14.sp)
                    Text("ESTD. 1985", color = Gold, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Text(if (english) "Explore" else "ప్రత్యేక విభాగాలు", fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Navy)
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                FeatureCard("📖", if (english) "Holy Bible" else "పరిశుద్ధ బైబిల్",
                    if (english) "Read and meditate on God's Word" else "దేవుని వాక్యాన్ని చదవండి, ధ్యానించండి")
                FeatureCard("🎧", if (english) "Sermons" else "ప్రసంగాలు",
                    if (english) "Listen to messages and teachings" else "సందేశాలు మరియు బోధనలు వినండి")
                FeatureCard("🗓️", if (english) "Events" else "కార్యక్రమాలు",
                    if (english) "Stay updated with church gatherings" else "చర్చి సమావేశాల వివరాలు తెలుసుకోండి")
                FeatureCard("🙏", if (english) "Prayer Requests" else "ప్రార్థనా విజ్ఞప్తులు",
                    if (english) "Share a request for prayer" else "ప్రార్థన కోసం మీ విజ్ఞప్తిని పంపండి")
            }
        }
        item {
            Text(if (english) "Church announcement" else "చర్చి ప్రకటన",
                color = Navy, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text(if (english) "Announcements will be published by the church admin." else "చర్చి అడ్మిన్ ద్వారా ప్రకటనలు ఇక్కడ ప్రచురించబడతాయి.",
                color = Color.DarkGray)
        }
    }
}

@Composable
fun FeatureCard(emoji: String, title: String, subtitle: String) {
    Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(Modifier.fillMaxWidth().padding(15.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(emoji, fontSize = 27.sp)
            Spacer(Modifier.width(14.dp))
            Column {
                Text(title, color = Navy, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(subtitle, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun InfoScreen(title: String, description: String, modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(title, color = Navy, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        HorizontalDivider(color = Gold)
        Text(description, color = Color.DarkGray, fontSize = 16.sp)
        Text("Zion Prayer House • ESTD 1985", color = Color.Gray, fontSize = 12.sp)
    }
}
