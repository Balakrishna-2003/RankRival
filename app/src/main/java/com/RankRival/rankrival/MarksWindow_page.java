package com.RankRival.rankrival;

import static com.RankRival.rankrival.MainActivity.marksWindowUsn;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class MarksWindow_page extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build();

    private LinearLayout createCard(String subName, String subValue) {
        LinearLayout.LayoutParams tsl1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        tsl1.setMargins(20, 0, 20, 15);

        LinearLayout linearLayout = new LinearLayout(MarksWindow_page.this);
        linearLayout.setBackground(getDrawable(R.drawable.custom_text));
        linearLayout.setLayoutParams(tsl1);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1.0f); // weight = 1

        TextView subjectname = new TextView(MarksWindow_page.this);
        subjectname.setText(subName);
        subjectname.setLayoutParams(textViewParams);
        subjectname.setGravity(Gravity.CENTER);
        subjectname.setTextSize(25);
        subjectname.setPadding(30, 30, 30, 30);
        subjectname.setBackground(getDrawable(R.drawable.custom_border));

        TextView obmarks = new TextView(MarksWindow_page.this);
        obmarks.setText(subValue);
        obmarks.setLayoutParams(textViewParams);
        obmarks.setGravity(Gravity.CENTER);
        obmarks.setTextSize(25);
        obmarks.setPadding(30, 30, 30, 30);
        obmarks.setBackgroundColor(Color.BLUE);

        linearLayout.addView(subjectname);
        linearLayout.addView(obmarks);

        return linearLayout;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_marks_window_page);

        LinearLayout cie1 = findViewById(R.id.markswindow_linear_cie1);
        LinearLayout cie2 = findViewById(R.id.markswindow_linear_cie2);
        RelativeLayout main = findViewById(R.id.main);

        db.setFirestoreSettings(settings);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);
        db.collection("users")
                .whereEqualTo("usn", marksWindowUsn)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            cie1.removeAllViews();
                            main.removeView(progressBar);

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                //Log.d("TAG", "onComplete: "+document.get("cie1").toString());
                                //Log.d("TAG", "onComplete: "+((Map<?, ?>) document.get("cie1")).size());


                                LinearLayout linearLayout1 = new LinearLayout(MarksWindow_page.this);
                                LinearLayout.LayoutParams lll = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.MATCH_PARENT
                                );
                                lll.setMargins(30, 40, 35, 40);
                                linearLayout1.setPadding(20, 20, 20, 20);
                                linearLayout1.setBackground(getDrawable(R.drawable.custom_text));
                                linearLayout1.setLayoutParams(lll);
                                linearLayout1.setOrientation(LinearLayout.VERTICAL);

                                LinearLayout.LayoutParams tsl = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                );
                                tsl.setMargins(50, 2, 50, 30);

                                TextView ts = findViewById(R.id.UserName);
                                ts.setText("name: " + document.getString("name"));
                                ts.setBackground(getDrawable(R.drawable.custom_text));
                                ts.setPadding(30, 30, 30, 30);
                                ts.setGravity(Gravity.CENTER);
                                ts.setLayoutParams(tsl);

                                TextView ts1 = findViewById(R.id.UserUsn);
                                ts1.setText("Usn: " + document.getString("usn"));
                                ts1.setBackground(getDrawable(R.drawable.custom_text));
                                ts1.setPadding(30, 30, 30, 30);
                                ts1.setLayoutParams(tsl);

                                Map<Object, Object> s = (Map<Object, Object>) document.get("cie1");
                                if (s != null) {
                                    for (Map.Entry<Object, Object> entry : s.entrySet()) {
                                        String key = entry.getKey().toString();
                                        String value = entry.getValue().toString();
                                        //Log.d("CIE1_DATA", "Key: " + key + ", Value: " + value);
                                        cie1.addView(createCard(key, value));
                                    }
                                } else {
                                    //Log.d("CIE1_DATA", "cie1 field is null or doesn't exist.");
                                }

                                Map<Object, Object> s1 = (Map<Object, Object>) document.get("cie2");
                                if (s1 != null) {
                                    for (Map.Entry<Object, Object> entry : s1.entrySet()) {
                                        String key = entry.getKey().toString();
                                        String value = entry.getValue().toString();
                                        cie2.addView(createCard(key, value));
                                    }
                                } else {
                                    //Log.d("CIE2_DATA", "cie2 field is null or doesn't exist.");
                                }

                            }
                        } else {
                            Toast.makeText(MarksWindow_page.this, "Failed to get Data", Toast.LENGTH_SHORT).show();
                            Log.w("read1", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}