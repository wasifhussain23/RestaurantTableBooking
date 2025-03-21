package s3301912tablebooking.wasif.app

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.Calendar


class BookTableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TableBookingScreen()
        }
    }
}


@Composable
fun TableBookingScreen() {

    val context = LocalContext.current as Activity


    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var guests by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    val timeSlots = listOf(
        "5 PM - 6 PM", "6 PM - 7 PM", "7 PM - 8 PM",
        "8 PM - 9 PM", "9 PM - 10 PM", "10 PM - 11 PM",
        "11 PM - 12 AM"
    )

    val calendar = Calendar.getInstance()

    // Date Picker
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = "$dayOfMonth/${month + 1}/$year"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Time Picker
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    //tables

    val rows = 5  // Number of rows in the restaurant layout
    val columns = 3  // Number of columns
    val totalTables = rows * columns

    var selectedTables by remember { mutableStateOf(setOf<Int>()) }

    var selectedSlot by remember { mutableStateOf<String?>(null) }

    var bookedTables by remember { mutableStateOf(listOf<Int>()) }


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
                text = "Book Table",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


        }


        Column(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone Number") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = guests,
                onValueChange = { guests = it },
                label = { Text("Number of Guests") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 8.dp)
                        .height(50.dp)
                        .clickable {
                            // Handle the click event, e.g., show a date picker
                        }
                        .background(Color.LightGray, MaterialTheme.shapes.medium)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = selectedDate.ifEmpty { "Select Date" },
                        color = if (selectedDate.isEmpty()) Color.Gray else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange, // Replace with your desired icon
                        contentDescription = "Calendar Icon",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(24.dp)
                            .clickable {
                                datePickerDialog.show()
                            },
                        tint = Color.DarkGray
                    )
                }

//                Spacer(modifier = Modifier.width(16.dp))

//                Box(
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(vertical = 8.dp)
//                        .height(50.dp)
//                        .clickable {
//                            // Handle the click event, e.g., show a date picker
//                        }
//                        .background(Color.LightGray, MaterialTheme.shapes.medium)
//                        .padding(horizontal = 16.dp),
//                    contentAlignment = Alignment.CenterStart
//                ) {
//                    Text(
//                        text = selectedTime.ifEmpty { "Select Time" },
//                        color = if (selectedTime.isEmpty()) Color.Gray else Color.Black
//                    )
//                    Icon(
//                        imageVector = Icons.Default.DateRange, // Replace with your desired icon
//                        contentDescription = "Calendar Icon",
//                        modifier = Modifier
//                            .align(Alignment.CenterEnd)
//                            .size(24.dp)
//                            .clickable {
//                                timePickerDialog.show()
//                            },
//                        tint = Color.DarkGray
//                    )
//                }

            }

            Text(
                text = "Select Time Slot",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            //time slots
            LazyVerticalGrid(
                columns = GridCells.Fixed(3), // 3 columns in each row
                modifier = Modifier
                    .wrapContentSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(timeSlots.size) { slot ->
                    TimeSlotItem(
                        timeSlot = timeSlots[slot],
                        isSelected = timeSlots[slot] == selectedSlot,
                        onClick = {
                            selectedSlot = timeSlots[slot]
//                            onSlotSelected(slot)
                        }
                    )
                }
            }


//            SeatBookingScreen()
            Text(
                text = "Select Your Table",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (selectedDate.isNotEmpty() && selectedSlot != null) {
                getBookedTables(
                    SelectedRestaurant.restaurantData.restaurantId.toString(),
                    selectedDate,
                    selectedSlot!!,
                    onResult = { it ->
                        bookedTables = it
                        Log.e("Test", "Booked Tables : $it")
                    }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
//                .verticalScroll(rememberScrollState())  // Enables scrolling if needed
            ) {
                items(rows) { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (col in 0 until columns) {
                            val tableNumber = (row * columns) + col + 1
                            val isSelected = tableNumber in selectedTables

                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .padding(horizontal = 3.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(
                                            if (isSelected) Color.Green else Color.LightGray
                                        )
                                        .clickable {
                                            if (tableNumber in bookedTables) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Already Booked",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            } else {
                                                selectedTables = if (isSelected) {
                                                    selectedTables - tableNumber
                                                } else {
                                                    selectedTables + tableNumber
                                                }
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {

                                    var tableText = ""

                                    tableText = if (tableNumber in bookedTables)
                                        "Table $tableNumber\nBooked"
                                    else
                                        "Table $tableNumber"

                                    Text(
                                        text = tableText,
                                        fontSize = 14.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = {
                    if (name.isNotEmpty() && phone.isNotEmpty() && guests.isNotEmpty() && selectedDate.isNotEmpty() && selectedSlot!!.isNotEmpty()) {

                        val customerMail = CustomerPreferences.fetchUserMail(context)

                        saveTableBooking(
                            customerMail,
                            SelectedRestaurant.restaurantData.restaurantId.toString(),
                            name,
                            phone,
                            guests.toInt(),
                            selectedDate,
                            selectedSlot!!,
                            selectedTables.toList()
                        )

                        Toast.makeText(context, "Table booked successfully!", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            context,
                            "Please fill all details",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Book Table")
            }
        }
    }
}

@Composable
fun TimeSlotItem(timeSlot: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .background(
                if (isSelected) Color.Blue else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timeSlot,
            color = Color.White,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTableBookingScreen() {
    TableBookingScreen()
}


fun saveTableBooking(
    userEmail: String,
    restaurantId: String,
    customerName: String,
    phoneNumber: String,
    noOfGuests: Int,
    date: String,
    time: String,
    selectedTables: List<Int>
) {
    val database = FirebaseDatabase.getInstance().reference
    val userBookingsRef = database.child("Bookings").child(userEmail.replace(".", "_"))

    // Generate a unique Booking ID
    val bookingId = userBookingsRef.push().key ?: return

    val bookingData = mapOf(
        "restaurantId" to restaurantId,
        "name" to customerName,
        "phoneNumber" to phoneNumber,
        "noOfGuests" to noOfGuests,
        "date" to date,
        "time" to time,
        "selectedTables" to selectedTables
    )

    userBookingsRef.child(bookingId).setValue(bookingData)
        .addOnSuccessListener {
            Log.d("Firebase", "Booking saved successfully")
        }
        .addOnFailureListener {
            Log.e("Firebase", "Failed to save booking", it)
        }
}


fun getBookedTables(
    restaurantId: String,
    date: String,
    time: String,
    onResult: (List<Int>) -> Unit
) {
    val database = FirebaseDatabase.getInstance().reference
    val bookingsRef = database.child("Bookings")

    val bookedTables = mutableListOf<Int>()

    bookingsRef.addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            for (userSnapshot in snapshot.children) {
                for (bookingSnapshot in userSnapshot.children) {
                    val booking = bookingSnapshot.getValue(Booking::class.java)
                    if (booking != null && booking.restaurantId == restaurantId &&
                        booking.date == date && booking.time == time
                    ) {
                        bookedTables.addAll(booking.selectedTables)
                    }
                }
            }
            onResult(bookedTables)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.e("Firebase", "Failed to get booked tables", error.toException())
        }
    })
}

data class Booking(
    val restaurantId: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val noOfGuests: Int = 0,
    val date: String = "",
    val time: String = "",
    val selectedTables: List<Int> = emptyList()
)
