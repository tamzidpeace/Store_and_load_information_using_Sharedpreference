package com.example.arafat.sharedpreference00;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userName, userPassword;
    private Button saveButon, loadButton;
    private TextView informationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.username_edit_text);
        userPassword = findViewById(R.id.password_edit_text);

        saveButon = findViewById(R.id.save_button);
        loadButton = findViewById(R.id.load_button);

        informationTextView = findViewById(R.id.information_text_view);

        saveButon.setOnClickListener(this);
        loadButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        if (view == saveButon) {

            if (userName.getText().toString().trim().equals("") && userPassword.getText().toString().trim().equals("")) {
                Toast.makeText(this, "Enter Information", Toast.LENGTH_SHORT).show();

            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey", userName.getText().toString().trim());
                editor.putString("passwordKey", userPassword.getText().toString().trim());
                editor.apply();

                Toast.makeText(this, "Information saved successfully.", Toast.LENGTH_SHORT).show();
            }


        } else if (view == loadButton) {
            Toast.makeText(this, "loadButton", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            if(sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")) {
                String getUsername = sharedPreferences.getString("usernameKey", "null");
                String getPassword = sharedPreferences.getString("passwordKey", "null");

                informationTextView.setText(getUsername + "\n" + getPassword);
            }

        }
    }
}
