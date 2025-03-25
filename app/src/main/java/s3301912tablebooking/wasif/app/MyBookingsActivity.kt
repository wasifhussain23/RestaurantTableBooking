package s3301912tablebooking.wasif.app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MyBookingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBookingsScreen()
        }
    }
}

@Composable
fun MyBookingsScreen() {
    val context = LocalContext.current as Activity

    val userEmail = CustomerPreferences.getCSMail(context)

    var myBookings by remember { mutableStateOf(listOf<Booking>()) }
    LaunchedEffect(userEmail) {
        getBookedTables(userEmail) { orders ->
            myBookings = orders
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.firsthome_color),
                )
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        context.finish()
                    },
                painter = painterResource(id = R.drawable.baseline_arrow_back_36),
                contentDescription = "Back"
            )

            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "My Bookings",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


        }

        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {

                LazyColumn {
                    items(myBookings) { restaurant ->
                        BookedRestaurantItem(restaurant)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

        }

    }

}

@Composable
fun BookedRestaurantItem(restaurantData: Booking) {

    val restaurantList = getRestaurants()
   val restaurant = restaurantList.find { it.restaurantId == restaurantData.restaurantId.toInt() }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.FillBounds,
                painter = painterResource(id = restaurant!!.profilePic),
                contentDescription = "ThumbNail"
            )
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = restaurant.name, fontSize = 24.sp, fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.baseline_date_range_24),
                    contentDescription = "ThumbNail"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Date of Booking : ${restaurantData.date}", fontSize = 18.sp)

            }

            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "ThumbNail"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Time Slot :${restaurantData.time}", fontSize = 16.sp)

            }

            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.table),
                    contentDescription = "ThumbNail"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = "Tables Booked :${restaurantData.selectedTables}", fontSize = 16.sp)

            }
        }
    }
}

fun getBookedTables(customerMail: String, callback: (List<Booking>) -> Unit) {

    val emailKey = customerMail.replace(".", "_")
    Log.e("Test","User Mail : $emailKey")

    val databaseReference = FirebaseDatabase.getInstance().getReference("Bookings/$emailKey")

    databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val bookingsList = mutableListOf<Booking>()
            for (donationSnapshot in snapshot.children) {
                val donation = donationSnapshot.getValue(Booking::class.java)
                donation?.let { bookingsList.add(it) }
            }

            Log.e("Test","Bookings Fetched - ${bookingsList.size}")
            callback(bookingsList)
        }

        override fun onCancelled(error: DatabaseError) {
            println("Error: ${error.message}")
            callback(emptyList())
        }
    })
}