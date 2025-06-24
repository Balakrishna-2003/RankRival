package com.RankRival.rankrival;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        LinearLayout main_linear = findViewById(R.id.subjects_id_test);

        Button addSubjectbtn = findViewById(R.id.addSubject_test);
        addSubjectbtn.setOnClickListener(v -> {
            LinearLayout.LayoutParams ll1 = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f);
            ll1.setMargins(80, 0, 8, 0);

            LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1.0f);
            ll2.setMargins(80, 0, 8, 20);


            LinearLayout outerlinear = new LinearLayout(test.this);
            outerlinear.setOrientation(LinearLayout.VERTICAL);

            LinearLayout innerlinear = new LinearLayout(test.this);
            innerlinear.setOrientation(LinearLayout.HORIZONTAL);
            innerlinear.setGravity(Gravity.CENTER_VERTICAL);

            LinearLayout innerlinear1 = new LinearLayout(test.this);
            innerlinear1.setOrientation(LinearLayout.HORIZONTAL);
            innerlinear1.setGravity(Gravity.CENTER_VERTICAL);


            EditText sub = new EditText(test.this);
            sub.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            sub.setHint("Enter subject with course code");
            sub.setHintTextColor(Color.GRAY);
            sub.setTextColor(Color.BLACK);

//                Icon img = new I
            ImageButton remsub = new ImageButton(test.this);
            remsub.setImageDrawable(getDrawable(R.drawable.baseline_remove_circle_outline_24));
            remsub.setBackground(getDrawable(R.drawable.home_screen_add_button));
            remsub.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
            remsub.setPadding(0, 0, 0, 0);
            remsub.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            remsub.setOnClickListener(v1 -> {
                View parent = (View) (v1.getParent()).getParent();
                main_linear.removeView(parent);
            });



            EditText sub1 = new EditText(test.this);
            sub1.setHint("Enter subject mark");
            sub1.setInputType(InputType.TYPE_CLASS_NUMBER);
            sub1.setHintTextColor(Color.GRAY);
            sub1.setTextColor(Color.BLACK);

            ImageButton remsub1 = new ImageButton(test.this);
//                remsub1.setImageDrawable(getDrawable(R.drawable.baseline_remove_circle_outline_24));
            remsub1.setBackground(getDrawable(R.drawable.home_screen_add_button));
            remsub1.setLayoutParams(new ViewGroup.LayoutParams(140, 120));
            remsub1.setPadding(0, 0, 0, 0);
            remsub1.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

            sub.setLayoutParams(ll1);
            sub1.setLayoutParams(ll2);


            innerlinear.addView(sub);
            innerlinear.addView(remsub);

            innerlinear1.addView(sub1);
            innerlinear1.addView(remsub1);

            outerlinear.addView(innerlinear);
            outerlinear.addView(innerlinear1);

            main_linear.addView(outerlinear);
        });

        EditText newUser_name = findViewById(R.id.newUser_name_test);
        EditText newUser_usn = findViewById(R.id.newUser_usn_test);

        Button submitNewUser = findViewById(R.id.subjectNewUser_test);
        submitNewUser.setOnClickListener(v -> {
            int t = main_linear.getChildCount();

            Toast.makeText(test.this, "length " + t, Toast.LENGTH_SHORT).show();
            if (t == 0)
                Toast.makeText(test.this, "Enter atleast one subject", Toast.LENGTH_SHORT).show();


            HashMap<String, String> subjects = new HashMap<>();
            HashMap<String, String> subjects1 = new HashMap<>();
            HashMap<String, Object> details = new HashMap<>();
            details.put("name", newUser_name.getText().toString());
            details.put("usn", newUser_usn.getText().toString());


//                main_linear
//                        | -> outerlinear    ----> i
//                                  | -> innerlinear        -----> j
//                                             | -> [ sub ] , [ button ]   -----> jk
//                                  | -> innerlinear1
//                                             | -> [ sub1 ], [ button1 ]
//                        | -> outerlinear
//                                  | -> innerlinear
//                                             | -> [ sub ] , [ button ]
//                                  | -> innerlinear1
//                                             | -> [ sub1 ], [ button1 ]

            for (int i = 0; i < t; i++) {
                LinearLayout s = (LinearLayout) main_linear.getChildAt(i); //outer layer
                int sk = s.getChildCount();
                    String subject1 = "";
                    String marks = "";
                for(int j = 0; j < sk; j++) {
                    LinearLayout sn = (LinearLayout) s.getChildAt(j); //inner layer
                        if(j == 0)
                            subject1 = ((EditText)sn.getChildAt(0)).getText().toString();
                        if(j == 1)
                            marks = ((EditText)sn.getChildAt(0)).getText().toString();

                }
                subjects.put(subject1, marks);
                subjects1.put(subject1,"");
//                    if (((EditText) s).getText().toString().isEmpty() || ((EditText) s).getText().toString().isBlank()) {
//                        Toast.makeText(test.this, "empty found", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Log.d("TAG", "onCreate: " + ((EditText) s).getText());
//                        subjects.put(((EditText) s).getText().toString(), ((EditText) m).getText().toString());
//                        subjects1.put(((EditText) s).getText().toString(), "");
//                    }
            }
            details.put("cie1", subjects);
            details.put("cie2", subjects1);
            Log.d("TAG", "onClick: " + details);

            db.collection("users")
                    .add(details)
                    .addOnSuccessListener(documentReference -> {
                        Log.d("TAG1", "DocumentSnapshot added with ID: " + documentReference.getId() + " " + details);
                        startActivity(new Intent(test.this, MainActivity.class));
                        finish();
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("TAG", "Error adding document", e);
                        }
                    });
        });
//        View s = main_linear.getChildAt(0);
//        Log.d("TAG", "onCreate: "+((EditText) s).getText());
    }
}