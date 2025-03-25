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
                        context.startActivity(Intent(context, CustomerDataActivity::class.java))
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
                text = restaurantData.name, fontSize = 18.sp, fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.iv_location),
                    contentDescription = "Location"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = restaurantData.address, fontSize = 16.sp)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.baseline_local_phone_24),
                    contentDescription = "Phone"
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = restaurantData.phone, fontSize = 16.sp, textAlign = TextAlign.Justify)

//                Spacer(modifier = Modifier.weight(1f).height(2.dp))
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()

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
                        shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                    )
                    .padding(horizontal = 6.dp, vertical = 4.dp),
                text = "View Details",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
                )
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
            "Thai Terminal 1",
            R.drawable.thai_terminal,
            "Thai Basil Chicken",
            "100 Northenden Rd, Sale M33 3HD, United Kingdom",
            "+44 161 962 1010",
            "Thai Terminal 1 is a cozy and authentic Thai restaurant located in Sale, Greater Manchester. Known for its warm ambiance and traditional décor, this restaurant brings the essence of Thailand to the UK with its carefully crafted menu and friendly service. The restaurant takes pride in serving fresh, flavorful, and aromatic Thai cuisine, using high-quality ingredients and traditional cooking techniques.\n" +
                    "\n" +
                    "The menu offers a variety of classic Thai dishes, from fragrant curries and stir-fries to flavorful noodles and street food-inspired appetizers. Popular choices include Pad Thai, Massaman Curry, Tom Yum Soup, and Thai Green Curry. Whether you're looking for a spicy kick or a mild, comforting dish, there's something for every palate.\n" +
                    "\n" +
                    "The restaurant is ideal for casual dining, family gatherings, or even takeaway orders for those who prefer to enjoy Thai food at home. The service is known to be friendly and attentive, ensuring a welcoming atmosphere for both new and returning guests.\n" +
                    "\n" +
                    "Located conveniently in Sale, it’s a great spot for locals and visitors alike to experience an authentic taste of Thailand. Whether you’re craving a quick lunch, a romantic dinner, or a meal with friends, Thai Terminal 1 delivers a delightful culinary journey through Thailand's rich flavors.",
            LatLng(54.5145285, -1.4304957),
            listOf(
                MenuItem("Green Curry (Chicken/Beef/Tofu) ", "11.95", true, "0"),
                MenuItem("Pad Thai", "10.95", false, "20"),
                MenuItem("Tom Yum Soup", "6.50", false, "0"),
                MenuItem("Massaman Curry", "12.50", false, "10"),
                MenuItem("Thai Basil Chicken", "10.95", false, "0"),
                MenuItem("Spring Rolls", "5.50", false, "0"),
                MenuItem("Sticky Rice with Mango", "6.95", false, "0"),
                MenuItem("Thai Iced Tea", "3.95", false, "15")


            )
        ),
        RestaurantItemData(
            2,
            "Rio Brazilian Steakhouse",
            R.drawable.rio,
            "Rio Special Cuts",
            "93-101 Albert Rd, Middlesbrough TS1 2PX, United Kingdom",
            "+44 1642 262288",
            "Located on Albert Road in Middlesbrough, Rio Brazilian Steakhouse offers an authentic Brazilian Churrasco dining experience. The restaurant's ambiance is inspired by the Tijuca Urban Forest surrounding Rio de Janeiro, featuring planted walls and flowered ceilings that create a vibrant and immersive atmosphere. Since its opening, Rio has quickly become the top-rated restaurant in Middlesbrough on TripAdvisor and has gained national recognition, being named the third-best in the UK in TripAdvisor's Everyday Eats category. \u200B\n" +
                    "\n" +
                    "Diners at Rio are treated to a continuous tableside service of various grilled meats, carved by specially trained Gaucho chefs in the traditional Rodizio style. The restaurant also boasts a gourmet salad bar featuring fresh salads, imported cheeses, cured meats, and traditional Brazilian dishes. Complementing the dining experience is a range of unique cocktails with a South American flair, including caipirinhas, margaritas, and Rio's signature 'Old Duarte.' \u200B\n" +
                    "We are Middlesbrough\n" +
                    "\n" +
                    "Strategically situated opposite Middlesbrough's famous Centre Square, Rio Brazilian Steakhouse is an ideal choice for both intimate dinners and larger gatherings. The restaurant's commitment to quality food and exceptional service has solidified its reputation as a must-visit dining destination in the area.",
            LatLng(54.5754356, -1.2350008),
            listOf(
                MenuItem("Picanha", "14.09", true, "10"),
                MenuItem("Alcatra", "10.05", true, "0"),
                MenuItem("Picanha Ao Alha", "12.95", true, "0"),
                MenuItem("Maminha", "6.50", true, "15"),
                MenuItem("Fraldinha", "9.45", true, "0"),
                MenuItem("Cordeiro", "11.25", true, "0"),
                MenuItem("Spicy Chicken Wings", "14.28", true, "0"),
                MenuItem("Rio Special Cuts", "15.25", true, "20")
            )
        ),
        RestaurantItemData(
            3,
            "Turtle Bay Middlesbrough",
            R.drawable.turtle_bay,
            "Passion Martini",
            "32 Corporation Rd, Middlesbrough TS1 2RX, United Kingdom",
            "+44 1642 246070",
            "Turtle Bay Middlesbrough brings the vibrant flavors and lively atmosphere of the Caribbean to the heart of Middlesbrough. Located at 32 Corporation Road, this restaurant offers a unique dining experience that combines authentic Caribbean cuisine with a relaxed, island-inspired ambiance. The interior is adorned with colorful décor, creating an inviting space that reflects the warmth and energy of the Caribbean.\u200B\n" +
                    "\n" +
                    "The menu features a diverse array of dishes, from traditional jerk chicken to innovative fusion creations, catering to various dietary preferences including vegetarian and vegan options. Complementing the food is an extensive selection of handcrafted cocktails, with the rum-based drinks being particularly popular among patrons. Turtle Bay is also known for its \"Happy Hour\" deals, offering guests the opportunity to enjoy their favorite beverages at discounted prices during specific times.\u200B\n" +
                    "\n" +
                    "Beyond its culinary offerings, Turtle Bay Middlesbrough serves as a social hub where guests can enjoy live music and themed events, enhancing the overall dining experience. The friendly and attentive staff contribute to the welcoming environment, ensuring that each visit is memorable. Whether you're seeking a casual meal with friends, a family gathering, or a lively night out, Turtle Bay Middlesbrough provides a taste of the Caribbean that transports you to sun-drenched beaches and bustling island markets.\u200B",
            LatLng(54.5761686, -1.2349541),
            listOf(
                MenuItem("Strawberry Daiquiri", "9.2", true, "5"),
                MenuItem("Kingston 62 White", "4.5", true, "0"),
                MenuItem("ElderFlower Cooler", "4.5", true, "0"),
                MenuItem("Red Stripe", "4.6", true, "10"),
                MenuItem("Koko Kanu Coconut", "8.0", true, "0"),
                MenuItem("Pineapple Daiquiri", "23.5", true, "0"),
                MenuItem("Passion Martini", "20.5", true, "15"),
                MenuItem("Cockspur", "5.5", true, "0")
            )
        ),

        RestaurantItemData(
            4,
            "Pomegranate Persian Tea Room",
            R.drawable.pomegranate_persian,
            "Shawarma Hummus",
            "5 Park Square, Great Ayton, Middlesbrough TS9 6BP, United Kingdom",
            "+44 1642 958764",
            "Nestled in the heart of Great Ayton, Pomegranate Persian Tea Room offers a delightful fusion of traditional Persian cuisine and contemporary culinary artistry. Established with a passion for sharing the rich flavors of Persia, the tea room provides an inviting atmosphere where guests can embark on a gastronomic journey through authentic dishes crafted with love and precision.\u200B\n" +
                    "\n" +
                    "The interior exudes warmth and charm, with décor that reflects Persian culture, creating a serene environment perfect for both casual gatherings and special occasions. The menu boasts a diverse selection of Persian specialties, each prepared using fresh, high-quality ingredients to ensure an authentic taste experience. From aromatic teas to delectable main courses, every item is designed to tantalize the taste buds and introduce patrons to the rich culinary heritage of Persia.\u200B\n" +
                    "\n" +
                    "In addition to its culinary offerings, Pomegranate Persian Tea Room is renowned for its specialty teas, providing a wide array of options that cater to various palates. The establishment also hosts events and themed evenings, further immersing guests in Persian traditions and hospitality.\u200B\n" +
                    "\n" +
                    "With a commitment to exceptional service and a menu that celebrates the depth of Persian cuisine, Pomegranate Persian Tea Room has become a cherished destination for both locals and visitors seeking a unique and enriching dining experience.",
            LatLng(54.4888545, -1.1325834),
            listOf(
                MenuItem("Pickled Vegetable", "4.50", true, "5"),
                MenuItem("Falafel Sandwich", "11.95", true, "0"),
                MenuItem("Seer Torshi", "4", true, "10"),
                MenuItem("Shawarma Hummus", "11", true, "0"),
                MenuItem("King Prawn ", "15.95", true, "0"),
                MenuItem("Chicken Shawarma", "12.95", true, "20"),
                MenuItem("Baklava", "2", true, "0"),
                MenuItem("Fresh Cream Cake", "5", true, "12")
            )
        ),
        RestaurantItemData(
            5,
            "Rose & Potter",
            R.drawable.rose_potter,
            "Mixed Platter",
            "98 High St, Marske-by-the-Sea, Redcar TS11 7BA, United Kingdom",
            "+44 7585 268721",
            "Situated on the High Street of Marske-by-the-Sea, Rose & Potter is a charming bar and deli renowned for its carefully curated selection of wines and delectable small plates. Established seven years ago, the establishment prides itself on offering a range of wines from Cellar Espelt, notable suppliers to the famed Michelin-starred restaurant 'El Bulli'. This connection adds a touch of prestige to their wine offerings, appealing to both connoisseurs and casual enthusiasts alike. \u200B\n" +
                    "\n" +
                    "The interior of Rose & Potter exudes a cozy and welcoming ambiance, making it an ideal spot for both intimate gatherings and casual outings. Patrons often commend the establishment for its excellent food, friendly staff, and inclusive atmosphere that caters to various dietary preferences, including vegan options. Additionally, the venue is dog-friendly, allowing guests to enjoy their experience alongside their canine companions.",
            LatLng(54.5910268, -1.0192556),
            listOf(
                MenuItem("Bruschetta", "5.95", true, "20"),
                MenuItem("Halloumi Fries", "5.85", true, "0"),
                MenuItem("Cheese Board", "13.95", true, "0"),
                MenuItem("Fish Crab Platter", "34.50", true, "10"),
                MenuItem("Warm Goats Cheese", "8.95", true, "0"),
                MenuItem("Mixed Platter", "12.95", true, "0"),
                MenuItem("Greek Salad", "8.95", true, "7"),
                MenuItem("Homemade Spanish Beans", "6.75", true, "0")
            )
        ),
        RestaurantItemData(
            6,
            "Sadlers Cafe & Bistro",
            R.drawable.sadlers_cafe,
            "Chicken Supreme",
            "59-61 High St, Stokesley, Middlesbrough TS9 5AB, United Kingdom",
            "+44 1642 956066",
            "Located in the charming market town of Stokesley, Sadlers Cafe & Bistro offers a warm and inviting atmosphere for both locals and visitors. The establishment is renowned for its beautifully crafted bistro menu, which is refreshed seasonally to incorporate the freshest ingredients. Whether you're seeking a hearty breakfast, a leisurely lunch, or a delightful dinner, Sadlers provides a diverse range of options to cater to various tastes. The bistro also boasts a selection of vegetarian, vegan, and gluten-free dishes, ensuring inclusivity for all dietary preferences. With its commitment to quality and a cozy ambiance, Sadlers Cafe & Bistro has become a beloved spot in Stokesley for both casual dining and special occasions.\u200B",
            LatLng(554.4695942, -1.1940059),
            listOf(
                MenuItem("Crab and Crayfish Cocktail", "9.95", true, "0"),
                MenuItem("Smoked Chicken", "3.45", true, "0"),
                MenuItem("Chicken Supreme", "8.25", true, "10"),
                MenuItem("Crispy Slow Roasted Pork", "15.35", true, "15"),
                MenuItem("Chocolate Tart", "19.95", true, "0"),
                MenuItem("Baked Vanilla Cheesecake", "5.55", true, "0"),
                MenuItem("Sweet Potato Wedges", "4.65", true, "5"),
                MenuItem("Chunky Chips", "7.55", true, "0")

            )
        ),
        RestaurantItemData(
            7,
            "The Zetland",
            R.drawable.zetland,
            "Bolognese Pasta",
            "9 Zetland Rd, Middlesbrough TS1 1EH, United Kingdom",
            "+44 1642 246777",
            "Situated in the coastal village of Marske-by-the-Sea, The Zetland Hotel is a traditional inn that exudes charm and hospitality. Established around 1860, the hotel stands on a prominent corner near the railway station, offering both locals and visitors a welcoming retreat. The establishment prides itself on friendly service and a warm atmosphere, making it a favored spot for various occasions. \u200B\n" +
                    "\n" +
                    "The Zetland Hotel features a range of amenities designed to enhance the guest experience. Patrons can enjoy delicious, home-cooked meals served in both the bar and restaurant areas six days a week. The menu is thoughtfully prepared, catering to diverse tastes and preferences. During the warmer months, guests can relax in the hotel's hidden beer garden—a serene spot perfect for unwinding with a drink. The establishment is also dog-friendly, ensuring that four-legged companions are well taken care of with provided water bowls. \u200B\n" +
                    "\n" +
                    "For those seeking entertainment, The Zetland Hotel offers facilities such as a dartboard, pool table, and jukebox. Sports enthusiasts can catch live events broadcasted on BT Sport and Sky Sports. Additionally, the hotel provides free Wi-Fi, allowing guests to stay connected during their visit. Accommodation is available in homely letting rooms equipped with tea and coffee-making facilities and Freeview TV, making it an ideal choice for travelers exploring the area or attending local events. ",
            LatLng(54.5440074, -1.2612637),
            listOf(
                MenuItem("Saddle of Lamb", "14.00", true, "5"),
                MenuItem("Straks", "18.00", true, "0"),
                MenuItem("Duo of Pork", "13.50", true, "0"),
                MenuItem("Pan Fired Trout", "13.50", true, "7"),
                MenuItem("Potato Gnocchi", "11.00", true, "0"),
                MenuItem("Moules Frites", "11.50", true, "0"),
                MenuItem("Bolognese Pasta", "14.50", true, "10"),
                MenuItem("Banana Split", "10.25", true, "0")
            )
        ),
        RestaurantItemData(
            8,
            "Portofino",
            R.drawable.portofino,
            "Chicken Goujon",
            "Maritime Ave, Hartlepool TS24 0XZ, United Kingdom",
            "+44 1429 266166",
            "Portofino Restaurant, located in the heart of Newcastle upon Tyne, offers an authentic Italian dining experience in a warm and inviting atmosphere. The restaurant is known for its diverse menu, featuring classic Italian dishes such as pasta, pizza, and a variety of international cuisines. They cater to various dietary needs, providing vegetarian, vegan, and gluten-free options. \u200B\n" +
                    "\n" +
                    "The establishment operates with flexible hours, opening daily from 12:00 PM until late into the evening, and until 9:00 PM on Sundays. Portofino Restaurant also offers special menus during events like Valentine's Day and provides a 'Happy Hour' on weekdays between 12:00 PM and 7:00 PM, and on Saturdays from 12:00 PM to 5:00 PM, excluding December and Bank Holidays.",
            LatLng(54.6905741, -1.2071731),
            listOf(
                MenuItem("Potato Skin", "5.95", true, "12"),
                MenuItem("Garlic Mushrooms", "9.25", true, "0"),
                MenuItem("Chicken Goujon", "15.25", true, "0"),
                MenuItem("Lasagna", "11.25", true, "5"),
                MenuItem("Pepperoni Pizza", "8.55", true, "0"),
                MenuItem("Chicken Bellini", "14.95", true, "8"),
                MenuItem("Greek Salad", "7.55", true, "0"),
                MenuItem("Biscoff Cheesecake", "3.55", true, "0")
            )
        ),
        RestaurantItemData(
            9,
            "Glady's Vintage Tea Room",
            R.drawable.glady_vintage,
            "Tiramisu",
            "35 The Front, Seaton Carew, Hartlepool TS25 1BS, United Kingdom",
            "+44 1429 861941",
            "Glady's Vintage Tea Room is a charming establishment located in Seaton Carew, Hartlepool, themed around the 1940s era. The tea room boasts authentic fixtures and fittings that transport visitors back in time, offering a nostalgic and cozy atmosphere. The interior decor reflects the vintage theme, providing an immersive experience for guests. \u200B\n" +
                    "\n" +
                    "The culinary offerings at Glady's are crafted by three highly qualified chefs with backgrounds in reputable restaurants. They specialize in traditional English and Italian home-cooked food, with a particular emphasis on afternoon teas. The menu is extensive, featuring freshly prepared meals ranging from hearty breakfasts to delectable lunches and, of course, their signature afternoon teas. Each dish is made with care, showcasing homemade ingredients that elevate the dining experience. \u200B\n" +
                    "\n" +
                    "Patrons frequently highlight the generous portion sizes and the quality of the food, noting items such as the highly praised scones and creamy homemade chips. The welcoming staff, often dressed in period attire, enhance the overall charm of the tea room, contributing to a warm and inviting atmosphere. Customer reviews consistently commend the friendly service, with many noting how the team goes above and beyond to ensure a pleasant dining experience. ",
            LatLng(54.6588941, -1.1872912),
            listOf(
                MenuItem("A Variety of Scones", "1.80", true, "0"),
                MenuItem("Cakes", "3.50", true, "4"),
                MenuItem("Tea Cakes", "1.8", true, "0"),
                MenuItem("Knicker Bocker Glory", "4.25", true, "0"),
                MenuItem("Banana Split", "4.25", true, "2"),
                MenuItem("Tiramisu", "5.45", true, "0"),
                MenuItem("Chicken Sandwich", "4.25", true, "0"),
                MenuItem("Tuna Melt", "7.95", true, "5")
            )
        ),
        RestaurantItemData(
            10,
            "Cluck & Moo",
            R.drawable.cluck_moo,
            "Mac Amazing Fries",
            "3 Esplanade, Redcar TS10 3AA, United Kingdom",
            "+44 1642 483332",
            "Cluck & Moo is a vibrant eatery located in the heart of Redcar's Northern Quarter, offering a relaxed and friendly atmosphere ideal for dining with friends or family. The restaurant specializes in a diverse menu that caters to various dietary preferences, including vegetarian, vegan, and gluten-free options. Whether you're in the mood for a hearty breakfast, a leisurely lunch, or a satisfying dinner, Cluck & Moo provides a range of choices to suit your cravings.\u200B\n" +
                    "\n" +
                    "The establishment prides itself on its commitment to quality and customer satisfaction. The interior is designed to be both comfortable and stylish, creating an inviting space for patrons to enjoy their meals. In addition to its dine-in services, Cluck & Moo offers takeaway options, allowing customers to enjoy their favorite dishes at home. The restaurant's convenient location near the seafront makes it a popular spot for both locals and visitors seeking a delightful dining experience in Redcar.",
            LatLng(54.619554, -1.068207),
            listOf(
                MenuItem("Cheesy Nachos", "6.95", true, "0"),
                MenuItem("Mac Amazing Fries", "13", true, "5"),
                MenuItem("Chili Tastic Fries", "15", true, "0"),
                MenuItem("Sticky Coconut Rice", "4", true, "0"),
                MenuItem("Cheesy Chick", "13", true, "0"),
                MenuItem("Sizzling Chick", "17", true, "10"),
                MenuItem("Roast Chicken", "17", true, "0"),
                MenuItem("Steak Me Baby", "20", true, "7")
            )
        ),
        RestaurantItemData(
            11,
            "The Pepper Mill Bistro",
            R.drawable.pepper_mill_bistro,
            "Chicken Parmesan",
            " 42 Station Rd, Stockton-on-Tees, Billingham TS23 1AB, United Kingdom",
            "+44 1642 551934",
            "The Peppermill Bistro is a family-run restaurant situated in the heart of Billingham. Known for its fresh, seasonal, and unique menu, the bistro offers a modern, friendly, and welcoming atmosphere for all guests. The establishment prides itself on delivering high-quality dishes that cater to a variety of tastes and dietary requirements. \u200B\n" +
                    "\n" +
                    "The bistro's menu features a diverse range of options, including British cuisine with vegetarian-friendly choices. Diners have praised the early bird menu for its excellent range of flavorful options at competitive prices. The Peppermill Bistro also accommodates various dietary needs, ensuring that everyone can enjoy a delightful meal without compromising on quality or taste.",
            LatLng(54.5967917, -1.2936023),
            listOf(
                MenuItem("Pork Tenderloin", "14.95", true, "5"),
                MenuItem("Belly Pork", "13.55", true, "7"),
                MenuItem("Squash", "11.25", true, "0"),
                MenuItem("Gnocchi", "9.95", true, "0"),
                MenuItem("Pulled Pork", "8.55", true, "0"),
                MenuItem("Artisan Cheese Board", "12.45", true, "10"),
                MenuItem("Chicken Parmesan", "10.55", true, "0"),
                MenuItem("Affogato", "5.95", true, "0")
            )
        )

    )
}
