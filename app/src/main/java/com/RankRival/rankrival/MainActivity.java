package com.RankRival.rankrival;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

    public static String marksWindowUsn = "";
    public String usn = "";
    public static String updateUsn = "";
    public static String updateCie = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView test = findViewById(R.id.add_new_button);
        LinearLayout main_linear = findViewById(R.id.list_student);

//        db.setFirestoreSettings(settings);


        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addNewUser_page.class));
//                startActivity(new Intent(MainActivity.this, test.class));
            }
        });

        TextView leaderBoard = findViewById(R.id.LeaderBoard_btn);
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LeaderBoard_page.class));
            }
        });

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            main_linear.removeAllViews();
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CardView card = new CardView(MainActivity.this);
                                card.setBackground(getDrawable(R.drawable.custom_card));
                                LinearLayout linearLayout1 = new LinearLayout(MainActivity.this);

                                LinearLayout.LayoutParams lll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                lll.setMargins(30, 40, 35, 40);
                                card.setLayoutParams(lll);
                                linearLayout1.setPadding(20, 20, 20, 20);
                                linearLayout1.setBackground(getDrawable(R.drawable.custom_text));
                                linearLayout1.setLayoutParams(lll);
                                linearLayout1.setOrientation(LinearLayout.VERTICAL);

                                LinearLayout.LayoutParams tsl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                tsl.setMargins(50, 10, 50, 30);

                                TextView ts = new TextView(MainActivity.this);
                                ts.setText(document.getString("name"));
                                ts.setGravity(Gravity.CENTER);
                                ts.setLayoutParams(tsl);
                                ts.setBackground(getDrawable(R.drawable.custom_text));
                                ts.setPadding(30, 30, 30, 30);
                                ts.setTextColor(Color.WHITE);
                                ts.setTextSize(25);

                                TextView ts1 = new TextView(MainActivity.this);
                                ts1.setText(document.getString("usn"));
                                ts1.setGravity(Gravity.CENTER);
                                ts1.setLayoutParams(tsl);
                                ts1.setBackground(getDrawable(R.drawable.custom_text));
                                ts1.setPadding(30, 30, 30, 30);
                                ts1.setTextColor(Color.WHITE);
                                ts1.setTextSize(25);

                                // Create a custom button
                                Button beautifulButton = new Button(MainActivity.this);
                                beautifulButton.setText("Update Cie-1");
                                beautifulButton.setTextColor(Color.WHITE);
                                beautifulButton.setTextSize(22);
                                beautifulButton.setAllCaps(false);
                                beautifulButton.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

                                // Create a custom button
                                Button beautifulButton1 = new Button(MainActivity.this);
                                beautifulButton1.setText("Update Cie-2");
                                beautifulButton1.setTextColor(Color.WHITE);
                                beautifulButton1.setTextSize(22);
                                beautifulButton1.setAllCaps(false);
                                beautifulButton1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);

                                // Set padding (left, top, right, bottom)
                                beautifulButton.setPadding(40, 20, 40, 20);
                                beautifulButton1.setPadding(40, 20, 40, 20);

                                // Create background with rounded corners
                                GradientDrawable drawable = new GradientDrawable();
                                drawable.setShape(GradientDrawable.RECTANGLE);
                                drawable.setCornerRadius(50); // round corners
                                drawable.setColor(Color.parseColor("#FF6200EE")); // purple
                                drawable.setStroke(4, Color.parseColor("#FFFFFF")); // white border

                                // Apply background
                                beautifulButton.setBackground(drawable);
                                beautifulButton1.setBackground(drawable);

                                // Set layout parameters
                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT
                                );
                                params.setMargins(50, 30, 50, 30);

                                beautifulButton.setLayoutParams(params);
                                beautifulButton1.setLayoutParams(params);

                                beautifulButton.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        marksWindowUsn = document.getString("usn");
                                        updateUsn = marksWindowUsn;
                                        updateCie = "cie1";
                                        startActivity(new Intent(MainActivity.this, updateUserDetails_page.class));
                                    }
                                });
                                beautifulButton1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        marksWindowUsn = document.getString("usn");
                                        updateUsn = marksWindowUsn;
                                        updateCie = "cie2";
                                        startActivity(new Intent(MainActivity.this, updateUserDetails_page.class));
                                    }
                                });

                                card.addView(linearLayout1);
                                linearLayout1.addView(ts);
                                linearLayout1.addView(ts1);
                                linearLayout1.addView(beautifulButton);
                                linearLayout1.addView(beautifulButton1);


                                main_linear.addView(card);

                                card.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        marksWindowUsn = document.getString("usn");
//                                        updateUsn = marksWindowUsn;
//                                        updateCie = "cie2";
                                        startActivity(new Intent(MainActivity.this, MarksWindow_page.class));
                                    }
                                });

                                card.setOnLongClickListener(new View.OnLongClickListener() {
                                    @Override
                                    public boolean onLongClick(View v) {
                                        usn = ts1.getText().toString();
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("Delete Record")
                                                .setMessage("Are you sure you want to delete this record?")
                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        // Delete the document
                                                        db.collection("users")
                                                                .whereEqualTo("usn", usn)
                                                                .get()
                                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                    @Override
                                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                                                                            document.getReference().delete()
                                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                                        @Override
                                                                                        public void onSuccess(Void unused) {
                                                                                            Toast.makeText(MainActivity.this, "Deleted the document", Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    })
                                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                                        @Override
                                                                                        public void onFailure(@NonNull Exception e) {
                                                                                            Toast.makeText(MainActivity.this, "Error deleting"+ e,Toast.LENGTH_SHORT).show();
                                                                                        }
                                                                                    });
                                                                        }
                                                                    }
                                                                })
                                                                .addOnFailureListener(e -> Log.w("Firestore", "Error getting documents", e));
                                                        main_linear.removeView(v);
                                                    }
                                                })
                                                .setNegativeButton("No", null) // Dismiss dialog
                                                .show();


                                        return true;
                                    }
                                });
                            }
                        } else {
                            Log.w("read1", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}