package com.example.assignment2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {

    private CheckBox CSE, EEE,Bangla, English;
    ArrayList<String> arr = new ArrayList<>();
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextView quantityTextView, priceTextView, department, ratingText;
    private Button increment, decrement, placeOrder;
    private int quantity = 0;
    private int price = 0;
    private AlertDialog.Builder builder;
    private RatingBar ratingBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.check_box);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //For CheckBox
        CSE = findViewById(R.id.CSE);
        EEE = findViewById(R.id.EEE);
        Bangla  = findViewById(R.id.Bangla);
        English = findViewById(R.id.English );
        department = findViewById(R.id.department);
        //For RadioGroup
        radioGroup = findViewById(R.id.radioGroup);
        //For Increment Decrement
        quantityTextView = findViewById(R.id.quantityTextView);
        priceTextView = findViewById(R.id.priceTextView);
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);
        placeOrder = findViewById(R.id.order_btn);
        builder = new AlertDialog.Builder(this);
        //For RatingBar
        ratingBar = findViewById(R.id.ratingBar);
        ratingText = findViewById(R.id.rating);
        CSE.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        EEE.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        Bangla.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        English.setOnCheckedChangeListener((buttonView, isChecked) -> {
            check(buttonView, isChecked);
        });
        increment.setOnClickListener(v -> {
            quantity++;
            price = quantity * 250;
            quantityTextView.setText("" + quantity);
            priceTextView.setText("$" + price);
        });
        decrement.setOnClickListener(v -> {
            if(quantity>0){
                quantity--;
                price = quantity * 250;
                quantityTextView.setText("" + quantity);
                priceTextView.setText("$" + price);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
            }
        });
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText.setText("Rating: " + rating);
            }
        });
        placeOrder.setOnClickListener(v -> {
            try {
                if (arr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select department!!", Toast.LENGTH_SHORT).show();
                }
                String radioValue = radioButton.getText().toString();
                if (quantity == 0) {
                    Toast.makeText(getApplicationContext(), "Please add quantity!!", Toast.LENGTH_SHORT).show();
                } else {
                    builder.setTitle("Order Placed!!")
                            .setMessage("Order Summary:\n" + "Department: " + arr + "\nT-shirt Size: " + radioValue + "\nQuantity: " + quantity + "\nTotal Price: BDT " + price + "\nRating: " + ratingBar.getRating() +"\nThank you!!")
                            .setCancelable(false)
                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(), "Order Placed!!", Toast.LENGTH_SHORT).show();
                                    quantity = 0;
                                    price = 0;
                                    quantityTextView.setText("0");
                                    priceTextView.setText("BDT 0");
                                    department.setText("");
                                    CSE.setChecked(false);
                                    EEE.setChecked(false);
                                    Bangla.setChecked(false);
                                    English.setChecked(false);
                                    radioGroup.clearCheck();
                                    ratingBar.setRating(0);
                                }
                            }).show();
                }
            } catch (Exception e){
                Toast.makeText(getApplicationContext(), "Please Select T-shirt Size!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    void check(CompoundButton buttonView, Boolean isChecked){
        if (isChecked) {
            arr.add(buttonView.getText().toString());
            Log.d("array", String.valueOf(arr));
        } else{
            arr.remove(buttonView.getText().toString());
        }
        department.setText(String.valueOf(arr));
    }

}