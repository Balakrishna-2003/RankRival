package com.RankRival.rankrival;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LeaderBoard1_cie extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView CIE_1;
    int count = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_leader_board1_cie);

        LinearLayout main_linear = findViewById(R.id.addLinear_cie2);
        RelativeLayout main = findViewById(R.id.main);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        CIE_1 = findViewById(R.id.CIE_1);
        CIE_1.setOnClickListener(view -> {
            onBackPressed();
        });

        db.collection("users")
                .orderBy("cie2_total", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            main.removeView(progressBar);
                            main_linear.removeAllViews();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CardView cardView = new CardView(LeaderBoard1_cie.this);
                                cardView.setBackground(getDrawable(R.drawable.custom_card));
                                LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                ll.setMargins(50, 10, 50, 50);
                                cardView.setLayoutParams(ll);

                                LinearLayout linearLayout1 = new LinearLayout(LeaderBoard1_cie.this);
                                LinearLayout.LayoutParams lll = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


                                linearLayout1.setPadding(20, 20, 20, 20);
                                linearLayout1.setBackground(getDrawable(R.drawable.custom_card));
                                linearLayout1.setLayoutParams(lll);
                                linearLayout1.setOrientation(LinearLayout.VERTICAL);


                                TextView ts = new TextView(LeaderBoard1_cie.this);
                                ts.setText(document.getString("name"));
                                ts.setGravity(Gravity.CENTER);
                                LinearLayout.LayoutParams tsl = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                tsl.setMargins(50, 50, 50, 20);
                                ts.setLayoutParams(tsl);
                                ts.setBackground(getDrawable(R.drawable.custom_text));
                                ts.setPadding(30, 30, 30, 30);
                                ts.setTextColor(Color.WHITE);
                                ts.setTextSize(25);

                                LinearLayout.LayoutParams innerll = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                                LinearLayout inner_linear = new LinearLayout(LeaderBoard1_cie.this);
                                inner_linear.setLayoutParams(innerll);
                                inner_linear.setOrientation(LinearLayout.VERTICAL);
                                inner_linear.setGravity(Gravity.CENTER);


                                TextView btn1 = new TextView(LeaderBoard1_cie.this);
                                btn1.setText("Total marks: "+document.get("cie2_total"));
                                LinearLayout.LayoutParams tsl2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                tsl2.setMargins(50, 50, 50, 20);
                                btn1.setLayoutParams(tsl2);
                                btn1.setGravity(Gravity.CENTER);
                                btn1.setBackground(getDrawable(R.drawable.cutom_button));

                                btn1.setTextSize(25);

                                if(count >= 0 && count <= 2){
                                    ImageView img = new ImageView(LeaderBoard1_cie.this);
                                    if(count == 0){
                                        img.setImageDrawable(getDrawable(R.drawable._stleader));
                                    }else if(count == 1){
                                        img.setImageDrawable(getDrawable(R.drawable._ndleader));
                                    }else{
                                        img.setImageDrawable(getDrawable(R.drawable._rdleader));
                                    }

                                    LinearLayout.LayoutParams tsl1 = new LinearLayout.LayoutParams(250, 250);
                                    tsl1.setMarginEnd(15);
                                    img.setLayoutParams(tsl1);

                                    inner_linear.addView(img);
                                    count++;
                                }
                                inner_linear.addView(btn1);

                                main_linear.addView(cardView);
                                cardView.addView(linearLayout1);
                                linearLayout1.addView(ts);
                                linearLayout1.addView(inner_linear);
                            }
                        } else {
                            Log.w("read1", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}