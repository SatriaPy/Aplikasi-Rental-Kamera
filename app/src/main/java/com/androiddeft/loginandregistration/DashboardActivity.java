package com.androiddeft.loginandregistration;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DashboardActivity extends AppCompatActivity {
    private SessionHandler session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        session = new SessionHandler(getApplicationContext());
        User user = session.getUserDetails();
        TextView welcomeText = findViewById(R.id.welcomeText);

        welcomeText.setText("Welcome "+user.getFullName()+", your session will expire on "+user.getSessionExpiryDate());

        Button logoutBtn = findViewById(R.id.btnLogout);
        Button msk = findViewById(R.id.msk);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                Intent i = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

            }
        });

        msk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
        });

//        Button button_em = findViewById(R.id.button_em);
//
//        button_em.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                session.logoutUser();
////                Intent i = new Intent(DashboardActivity.this, EmpowermentActivity.class);
////                startActivity(i);
////                finish();
//                Intent i = new Intent(getApplicationContext(), EmpowermentActivity.class);
//                startActivity(i);
//                finish();
//
//            }
//        });
//
//        Button button_ch = findViewById(R.id.button_ch);
//
//        button_ch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                session.logoutUser();
////                Intent i = new Intent(DashboardActivity.this, ChildActivity.class);
////                startActivity(i);
////                finish();
//                Intent i = new Intent(getApplicationContext(), ChildActivity.class);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        Button button_di = findViewById(R.id.button_di);
//
//                button_di.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                session.logoutUser();
////                        Intent i = new Intent(DashboardActivity.this, DisasterActivity.class);
////                        startActivity(i);
////                        finish();
//                        Intent i = new Intent(getApplicationContext(), DisasterActivity.class);
//                        startActivity(i);
//                        finish();
//                    }
//                });
    }
}
