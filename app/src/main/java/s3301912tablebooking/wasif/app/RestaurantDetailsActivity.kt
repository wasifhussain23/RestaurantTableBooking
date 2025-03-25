package s3301912tablebooking.wasif.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import s3301912tablebooking.wasif.app.SelectedRestaurant.restaurantData

class RestaurantDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantDetailsScreen()
        }
    }
}

@Composable
fun RestaurantDetailsScreen() {
    val context = LocalContext.current as Activity

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
                text = "Restaurant Details",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


        }


        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())

        ) {
            Spacer(modifier = Modifier.height(8.dp))

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
                            .height(250.dp),
                        contentScale = ContentScale.FillBounds,
                        painter = painterResource(id = restaurantData.profilePic),
                        contentDescription = "ThumbNail"
                    )

                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = restaurantData.name, fontSize = 24.sp, fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = restaurantData.address,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = restaurantData.phone,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        text = restaurantData.description,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    context.startActivity(Intent(context, MenuActivity::class.java))

                                }

                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.black),
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            text = "See Menu",
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            LocateRestaurantActivity::class.java
                                        )
                                    )

                                }
                                .border(
                                    width = 2.dp,
                                    color = colorResource(id = R.color.black),
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 6.dp, vertical = 4.dp),
                            text = "View Location",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))


                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clickable {
                                context.startActivity(
                                    Intent(
                                        context,
                                        BookTableActivity::class.java
                                    )
                                )

                            }
                            .background(
                                color = colorResource(id = R.color.black),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 6.dp, vertical = 4.dp),
                        text = "Click Here To Book",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(64.dp))


                }
            }
        }
    }
}