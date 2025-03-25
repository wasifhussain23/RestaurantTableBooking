package s3301912tablebooking.wasif.app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookingLaunchCheck(::customerStatusandGo)
        }
    }
    private fun customerStatusandGo(studentStatus: Int) {
        if (studentStatus == 2) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, BookingHomeActivity::class.java))
            finish()
        }

    }
}

@Composable
fun BookingLaunchCheck(onLoginClick: (studentStatus: Int) -> Unit) {
    val context = LocalContext.current

    SideEffect {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            onLoginClick(if (CustomerPreferences.getCS(context)) 1 else 2)
        }
    }

    BookingLaunch()
}



@Composable
fun BookingLaunch() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.firsthome_color),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.wave_up_white), // Replace with your actual SVG drawable
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.white),
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier
                    .size(150.dp),
                painter = painterResource(id = R.drawable.book_restaurant),
                contentDescription = "Book Table"
            )

            Text(
                text = "Restaurant Table Booking",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold))

            Text(
                text = "By",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))

            Text(
                text = "Wasif Hussain",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))

        }

        Image(
            painter = painterResource(id = R.drawable.wave_down_white), // Replace with your actual SVG drawable
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.weight(1f))

    }
}

@Preview(showBackground = true)
@Composable
fun BookingLaunchPreview() {
    BookingLaunch()
}