package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.myapplication.Adapters.BookingAdapter;
import com.example.myapplication.Adapters.BookingAdapter_for_add_booking;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Add_Booking_Activity extends AppCompatActivity {

    Button button1, button2, button3;
    RecyclerView recyclerView;
    BookingAdapter_for_add_booking bookingAdapter;
    ArrayList<Room> roomsList;
    ArrayList<Booking> bookingsList;
    ArrayList<LocalDate> date;
    FirebaseFirestore db;
    LocalDate checkInDate, checkOutDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_booking);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        bookingsList = new ArrayList<Booking>();
        roomsList = new ArrayList<Room>();
        date = new ArrayList<LocalDate>();

        checkInDate = LocalDate.now();
        button1.setText(getFormattedDateTime(checkInDate));
        checkOutDate = LocalDate.now().plusDays(1);
        button2.setText(getFormattedDateTime(checkOutDate));


        bookingAdapter = new BookingAdapter_for_add_booking(this, bookingsList, roomsList, checkInDate, checkOutDate);
        recyclerView.setAdapter(bookingAdapter);

        datePickerFunc();

        db.collection("rooms").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                roomsList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Room room = document.toObject(Room.class);
                    room.setDocumentId(document.getId());
                    roomsList.add(room);
                }
                bookingAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error in getting rooms", Toast.LENGTH_SHORT).show();
            }
        });
        db.collection("bookings").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                bookingsList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Booking booking = document.toObject(Booking.class);
                    bookingsList.add(booking);
                }
                bookingAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Error in getting bookings", Toast.LENGTH_SHORT).show();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingAdapter.updateDates(checkInDate, checkOutDate);
            }
        });
    }

    public LocalDateTime convertBtnTextToDateTime(String btnText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy, hh:mm:ss a", Locale.ENGLISH);
        return LocalDateTime.parse(btnText, formatter);
    }

    public String getFormattedDateTime(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        return date.format(formatter);
    }


    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        // default should never happen
        return "JAN";
    }

    public void datePickerFunc() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Booking_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date1 = makeDateString(dayOfMonth, month, year);
                        button1.setText(date1);
                        checkInDate = LocalDate.of(year, month, dayOfMonth);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Booking_Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date1 = makeDateString(dayOfMonth, month, year);
                        button2.setText(date1);
                        checkOutDate = LocalDate.of(year, month, dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}
