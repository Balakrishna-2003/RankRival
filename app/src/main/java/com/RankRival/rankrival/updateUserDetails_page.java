package com.RankRival.rankrival;

import static com.RankRival.rankrival.MainActivity.updateCie;
import static com.RankRival.rankrival.MainActivity.updateUsn;

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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class updateUserDetails_page extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    LinearLayout main_linear;
    HashMap<String, String> subjects1 = new HashMap<>();

    public void creatEdit(String subject, String value){
        LinearLayout.LayoutParams ll1 = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);
        ll1.setMargins(80, 0, 8, 0);

        LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                1.0f);
        ll2.setMargins(80, 0, 8, 20);


        LinearLayout outerlinear = new LinearLayout(updateUserDetails_page.this);
        outerlinear.setOrientation(LinearLayout.VERTICAL);

        LinearLayout innerlinear = new LinearLayout(updateUserDetails_page.this);
        innerlinear.setOrientation(LinearLayout.HORIZONTAL);
        innerlinear.setGravity(Gravity.CENTER_VERTICAL);

        LinearLayout innerlinear1 = new LinearLayout(updateUserDetails_page.this);
        innerlinear1.setOrientation(LinearLayout.HORIZONTAL);
        innerlinear1.setGravity(Gravity.CENTER_VERTICAL);


        EditText sub = new EditText(updateUserDetails_page.this);
        sub.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        sub.setHint("Enter subject with course code");
        sub.setText(subject);
        sub.setHintTextColor(Color.GRAY);
        sub.setTextColor(Color.BLACK);

//                Icon img = new I
        ImageButton remsub = new ImageButton(updateUserDetails_page.this);
        remsub.setImageDrawable(getDrawable(R.drawable.baseline_remove_circle_outline_24));
        remsub.setBackground(getDrawable(R.drawable.home_screen_add_button));
        remsub.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
        remsub.setPadding(0, 0, 0, 0);
        remsub.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        remsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent = (View) (v.getParent()).getParent();
                main_linear.removeView(parent);
            }
        });



        EditText sub1 = new EditText(updateUserDetails_page.this);
        sub1.setHint("Enter subject mark");
        sub1.setText(value);
        sub1.setInputType(InputType.TYPE_CLASS_NUMBER);
        sub1.setHintTextColor(Color.GRAY);
        sub1.setTextColor(Color.BLACK);

        ImageButton remsub1 = new ImageButton(updateUserDetails_page.this);
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

    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_user_details_page);

        RelativeLayout main = findViewById(R.id.main);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setIndeterminate(true);

        main_linear = findViewById(R.id.subjects_id_test); // main linear layout which stores the subjects details
        TextView title_textView = findViewById(R.id.title_addNewUser);
        title_textView.setText("Update "+updateCie);


        EditText newUser_name = findViewById(R.id.newUser_name_test);
        newUser_name.setEnabled(false);
        EditText newUser_usn = findViewById(R.id.newUser_usn_test);
        newUser_usn.setEnabled(false);

        Button addSubjectbtn = findViewById(R.id.addSubject_test);
        addSubjectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams ll1 = new LinearLayout.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f);
                ll1.setMargins(80, 0, 8, 0);

                LinearLayout.LayoutParams ll2 = new LinearLayout.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        1.0f);
                ll2.setMargins(80, 0, 8, 20);


                LinearLayout outerlinear = new LinearLayout(updateUserDetails_page.this);
                outerlinear.setOrientation(LinearLayout.VERTICAL);

                LinearLayout innerlinear = new LinearLayout(updateUserDetails_page.this);
                innerlinear.setOrientation(LinearLayout.HORIZONTAL);
                innerlinear.setGravity(Gravity.CENTER_VERTICAL);

                LinearLayout innerlinear1 = new LinearLayout(updateUserDetails_page.this);
                innerlinear1.setOrientation(LinearLayout.HORIZONTAL);
                innerlinear1.setGravity(Gravity.CENTER_VERTICAL);


                EditText sub = new EditText(updateUserDetails_page.this);
                sub.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                sub.setHint("Enter subject with course code");
                sub.setHintTextColor(Color.GRAY);
                sub.setTextColor(Color.BLACK);

//                Icon img = new I
                ImageButton remsub = new ImageButton(updateUserDetails_page.this);
                remsub.setImageDrawable(getDrawable(R.drawable.baseline_remove_circle_outline_24));
                remsub.setBackground(getDrawable(R.drawable.home_screen_add_button));
                remsub.setLayoutParams(new ViewGroup.LayoutParams(140, 140));
                remsub.setPadding(0, 0, 0, 0);
                remsub.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

                remsub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View parent = (View) (v.getParent()).getParent();
                        main_linear.removeView(parent);
                    }
                });



                EditText sub1 = new EditText(updateUserDetails_page.this);
                sub1.setHint("Enter subject mark");
                sub1.setInputType(InputType.TYPE_CLASS_NUMBER);
                sub1.setHintTextColor(Color.GRAY);
                sub1.setTextColor(Color.BLACK);

                ImageButton remsub1 = new ImageButton(updateUserDetails_page.this);
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
            }
        });

        Button submitNewUser = findViewById(R.id.subjectNewUser_test);
        submitNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(updateUserDetails_page.this, "Hello", Toast.LENGTH_SHORT).show();
                int t = main_linear.getChildCount();
                submitNewUser.setText("updating...");
                submitNewUser.setClickable(false);
                submitNewUser.setEnabled(false);

                addSubjectbtn.setClickable(false);
                addSubjectbtn.setEnabled(false);

                if (t == 0)
                    Toast.makeText(updateUserDetails_page.this, "Enter atleast one subject", Toast.LENGTH_SHORT).show();


                HashMap<String, String> subjects = new HashMap<>();



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

                int total_marks_cie1 = 0;
                for (int i = 0; i < t; i++) {
                    LinearLayout s = (LinearLayout) main_linear.getChildAt(i); //outer layer
                    int sk = s.getChildCount();
                    String subject1 = "";
                    String marks = "";
                    for(int j = 0; j < sk; j++) {
                        LinearLayout sn = (LinearLayout) s.getChildAt(j); //inner layer
                        if (j == 0){
                            subject1 = ((EditText) sn.getChildAt(0)).getText().toString();
                        }
                        if(j == 1) {
                            marks = ((EditText) sn.getChildAt(0)).getText().toString();
                            total_marks_cie1+= marks.isBlank() || marks.isEmpty()?0:Integer.parseInt(marks);
                        }

                    }
                    if (!subject1.isEmpty()) {
                        if(!subject1.isBlank()) {
                            subjects.put(subject1, marks);
                        }
                    }
//                    if (((EditText) s).getText().toString().isEmpty() || ((EditText) s).getText().toString().isBlank()) {
//                        Toast.makeText(updateUserDetails_page.this, "empty found", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Log.d("TAG", "onCreate: " + ((EditText) s).getText());
//                        subjects.put(((EditText) s).getText().toString(), ((EditText) m).getText().toString());
//                    }
                }

                int finalTotal_marks_cie = total_marks_cie1;
                db.collection("users")
                        .whereEqualTo("usn", newUser_usn.getText().toString())
                        .get()
                        .addOnSuccessListener(queryDocumentSnapshots -> {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {

                                    String docId = document.getId(); //testing
                                    Map<String, Object> cie2Old = (Map<String, Object>) document.get("cie2");
                                    for (Map.Entry<String, Object> entry: cie2Old.entrySet()) {
                                        Log.d("TAG", "entry: "+entry.getKey());
                                    }
                                    
                                    // Perform the update
                                    db.collection("users").document(docId)
                                            .update(updateCie, subjects, updateCie+"_total", finalTotal_marks_cie)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(updateUserDetails_page.this, "success", Toast.LENGTH_SHORT).show();
                                                    onBackPressed();
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                                      @Override
                                                                      public void onFailure(@NonNull Exception e) {
                                                                          Log.e("TAG", "Update failed", e);
                                                                          submitNewUser.setText("Submit");
                                                                          submitNewUser.setClickable(true);
                                                                          submitNewUser.setEnabled(true);
                                                                          addSubjectbtn.setClickable(true);
                                                                          addSubjectbtn.setEnabled(true);
                                                                      }
                                                                  });

                                }
                            } else {
                                Toast.makeText(updateUserDetails_page.this, "No data found!", Toast.LENGTH_SHORT).show();
                                Log.d("TAG", "No document found with usn = 4so");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                submitNewUser.setText("Submit");
                                submitNewUser.setClickable(true);
                                submitNewUser.setEnabled(true);
                                addSubjectbtn.setClickable(true);
                                addSubjectbtn.setEnabled(true);
                            }
                        });
            }
        });
        
        db.collection("users")
                .whereEqualTo("usn", updateUsn)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot data: task.getResult()){
                                main.removeView(progressBar);
                                //Log.d("data", ""+data.getString("name"));
                                newUser_name.setText(data.getString("name"));
                                //Log.d("data", ""+data.getString("usn"));
                                newUser_usn.setText(data.getString("usn"));
                                Object cie1Obj = data.get("cie1");
                                Map<String, Object> map = (Map<String,Object>) data.get(updateCie);
                                Map<String, Object> map1 = (Map<String,Object>) data.get("cie2"); //testing
                                Toast.makeText(updateUserDetails_page.this, "l "+map.size(), Toast.LENGTH_SHORT).show();

                                if (map != null) {
                                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                                        String key = entry.getKey();       // e.g., "ML", "SSCD", "UIUX"
                                        String value = (String) entry.getValue();   // e.g., 32, 56, 54

                                        //Log.d("TAG", "Key: " + key + ", Value: " + value);
                                        creatEdit(key, value);

                                    }
                                } else {
                                    Log.d("TAG", "cie1 is null or not found");
                                }

                                if (map != null) { //testing
                                    for (Map.Entry<String, Object> entry : map1.entrySet()) {
                                        String key = entry.getKey();       // e.g., "ML", "SSCD", "UIUX"
                                        String value = (String) entry.getValue();   // e.g., 32, 56, 54

                                        //Log.d("TAG", "Key: " + key + ", Value: " + value);
                                        subjects1.put(key, value);

                                    }
                                } else {
                                    Log.d("TAG", "cie1 is null or not found");
                                }

                                //Log.d("data",""+cie1Obj);
                                if (cie1Obj instanceof String) {
                                    String cie1 = (String) cie1Obj;
                                    //Log.d("TAG", "cie1: " + cie1);
                                } else {
                                    Log.w("TAG", "cie1 is not a String: " + cie1Obj);
                                }

//                                Log.d("data", data.getString("cie1"));
//                                Log.d("data", ""+data.getString("ci2").toString());
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}