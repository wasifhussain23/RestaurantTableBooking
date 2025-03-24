package s3301912tablebooking.wasif.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng

class BookingHomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookingHomeActivityScreen()
        }
    }
}


@Composable
fun BookingHomeActivityScreen() {

    val context = LocalContext.current as Activity

    val restuarants = getRestaurants()

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
//                        context.finish()
                    },
                painter = painterResource(id = R.drawable.iv_profile),
                contentDescription = "Profile"
            )

            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .padding(12.dp),
                text = "Table Booking App",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                modifier = Modifier
                    .size(36.dp)
                    .clickable {
                        context.startActivity(Intent(context, MyBookingsActivity::class.java))
                    },
                painter = painterResource(id = R.drawable.table_booking),
                contentDescription = "Table Booking"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LazyColumn {
                items(restuarants) { restaurant ->
                    RestaurantItem(restaurant)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }


    }
}


@Preview(showBackground = true)
@Composable
fun BookingHomeActivityScreenPreview() {
    BookingHomeActivityScreen()
}

@Composable
fun RestaurantItem(restaurantData: RestaurantItemData) {

    val context = LocalContext.current as Activity

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
                painter = painterResource(id = restaurantData.profilePic),
                contentDescription = "ThumbNail"
            )
            Text(
                modifier = Modifier.padding(start = 12.dp),
                text = restaurantData.name, fontSize = 24.sp, fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.iv_special_dish),
                    contentDescription = "ThumbNail"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = restaurantData.specials, fontSize = 18.sp)

//                Spacer(modifier = Modifier.weight(1f))
//
//                Text(
//                    modifier = Modifier
//                        .clickable {
//                            SelectedRestaurant.restaurantData = restaurantData
//                            context.startActivity(Intent(context, MenuActivity::class.java))
//
//                        }
//                        .background(
//                            color = colorResource(id = R.color.black),
//                            shape = RoundedCornerShape(6.dp)
//                        )
//                        .padding(horizontal = 6.dp, vertical = 4.dp),
//                    text = "See Menu",
//                    color = Color.White,
//                    fontSize = 16.sp
//                )

            }

            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.iv_location),
                    contentDescription = "ThumbNail"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = restaurantData.address, fontSize = 16.sp)

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    modifier = Modifier
                        .clickable {
                            SelectedRestaurant.restaurantData = restaurantData
                            context.startActivity(
                                Intent(
                                    context,
                                    RestaurantDetailsActivity::class.java
                                )
                            )

                        }
                        .background(
                            color = colorResource(id = R.color.firsthome_color),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 4.dp),
                    text = "Book Table",
                    color = Color.White,
                    fontSize = 16.sp
                )

            }
        }
    }
}


data class RestaurantItemData(
    var restaurantId: Int = 0,
    var name: String = "",
    var profilePic: Int = 0,
    var specials: String = "",
    var address: String = "UK,Tessidee",
    var phone: String = "+1 490 423 234",
    var description: String = "You have registered for Hackerearth Machine Learning Challenge: World Water Day currently happening on HackerEarth. Hackerearth Machine Learning Challenge: World Water Day is a rated contest that helps you to increase your HackerEarth rating and stand up among your peers.",
    var location: LatLng = LatLng(54.5706759, -1.2329553),
    var menuItems: List<MenuItem> = emptyList()
)

data class MenuItem(
    var itemName: String = "",
    var price: String = "",
    var isPopular: Boolean = false,
    var offer: String = "",
    var imageUrl: Int = R.drawable.dish
)

object SelectedRestaurant {
    var restaurantData = RestaurantItemData()
}

fun getRestaurants(): List<RestaurantItemData> {
    return listOf(
        RestaurantItemData(
            1,
            "Naatu 1",
            R.drawable.rest1,
            "Chicken Biryani",
            "United Kingdom",
            "+1 9097 908 879",
            "Description",
            LatLng(54.5145285,-1.4304957),
            listOf(
                MenuItem("Thai Pawn Cracker","2.50",true,"0"),
                MenuItem("Apple Juice","119",false,"0")

            )
        ),
        RestaurantItemData(
            2,
            "Naatu 2",
            R.drawable.rest1,
            "Chicken Biryani",
            "United Kingdom",
            "+1 9097 908 879",
            "Description",
            LatLng(54.5706759, -1.2329553),
            listOf(
                MenuItem("Sambar Rice","149",true,"0"),
                MenuItem("Mutton Dalcha","149",true,"0")
            )
        ),
        RestaurantItemData(
            3,
            "Naatu 3",
            R.drawable.rest1,
            "Chicken Biryani",
            "United Kingdom",
            "+1 9097 908 879",
            "Description",
            LatLng(54.5706759, -1.2329553),
            listOf(
                MenuItem("Apple MilkShake","149",true,"0")
            )
        )

    )
}
