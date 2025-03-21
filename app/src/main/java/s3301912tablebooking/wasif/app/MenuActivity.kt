package s3301912tablebooking.wasif.app

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantMenuScreen()
        }
    }
}

@Composable
fun RestaurantMenuScreen() {
    val menuItems = listOf(
        MenuItemData("Pizza", R.drawable.dish, 299.0),
        MenuItemData("Burger", R.drawable.dish, 199.0),
        MenuItemData("Pasta", R.drawable.dish, 249.0),
        MenuItemData("Salad", R.drawable.dish, 149.0),
        MenuItemData("Sushi", R.drawable.dish, 399.0),
        MenuItemData("Tacos", R.drawable.dish, 179.0)
    )

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
                text = "Restaurant Menu",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp)
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(menuItems.size) { menuItem ->
                    MenuItemCard(menuItems[menuItem])
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItemData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = menuItem.imageUrl),
                contentDescription = "ThumbNail"
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = menuItem.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "₹${menuItem.price}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

data class MenuItemData(val name: String, val imageUrl: Int, val price: Double)
