package com.example.myapplication.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.myapplication.Adapters.BookingAdapter;
import com.example.myapplication.R;
import com.example.myapplication.models.Booking;
import com.example.myapplication.models.Room;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class Bookings_fragment extends Fragment {

    Button button2;
    RecyclerView recyclerView;
    ArrayList<Room> roomsList;
    ArrayList<Booking> bookingsList;
    BookingAdapter bookingAdapter;
    LocalDate date;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View bookingsView = inflater.inflate(R.layout.fragment_bookings, container, false);

        button2 = bookingsView.findViewById(R.id.button2);
        recyclerView = bookingsView.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        button2.setText(getTodayDate());

        db = FirebaseFirestore.getInstance();
        bookingsList = new ArrayList<Booking>();
        roomsList = new ArrayList<Room>();
        date = convertBtnTextToDate(button2.getText().toString());


        datePickerFunc();

        bookingAdapter = new BookingAdapter(getActivity(), bookingsList, roomsList, date);
        recyclerView.setAdapter(bookingAdapter);

        db.collection("rooms").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                roomsList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Room room = document.toObject(Room.class);
                    room.setDocumentId(document.getId());
                    roomsList.add(room);
                }
                bookingAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(getActivity(), "Error in getting rooms", Toast.LENGTH_SHORT).show();
            }
        });
        db.collection("bookings").get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                bookingsList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    Booking booking = document.toObject(Booking.class);
                    bookingsList.add(booking);
                }
                bookingAdapter.notifyDataSetChanged();
            }
            else {
                Toast.makeText(getActivity(), "Error in getting bookings", Toast.LENGTH_SHORT).show();
            }
        });


        return bookingsView;
    }

    public LocalDate convertBtnTextToDate(String btnText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(btnText, formatter);
        return date;
    }

    public String getTodayDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
        String formattedDate = date.format(formatter);
        return formattedDate;
    }

    public void datePickerFunc() {
        try {Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                            month = month + 1;
                            String date1 = makeDateString(dayOfMonth, month, year);
                            button2.setText(date1);
                            date = LocalDate.of(year, month, dayOfMonth);
                            bookingAdapter.updateDate(date);
                        }
                    }, year, month,day);
                    datePickerDialog.show();

                }
            });

        }
        catch (Exception e) {
            Toast.makeText(getActivity(), "Error in datePicker", Toast.LENGTH_SHORT).show();
        }

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

        //default should never happen
        return "JAN";
    }
}