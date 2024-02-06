package com.example.assignedproject1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText emailEditText = findViewById(R.id.email_editText);
        EditText passwordEditText = findViewById(R.id.password_editText);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Show password Logic Start
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.show_password_icon);

        if (drawable != null) {
            drawable = DrawableCompat.wrap(drawable);

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

            final boolean[] isPasswordVisible = {false};

            Drawable finalDrawable = drawable;
            passwordEditText.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - finalDrawable.getBounds().width())) {
                        if (isPasswordVisible[0]) {
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                        isPasswordVisible[0] = !isPasswordVisible[0];
                        return true;
                    }
                }
                return false;
            });
        }
        // Show password logic end

        // String Span start
        TextView signUp = findViewById(R.id.sign_up_clickable);
        String signUpText = signUp.getText().toString();
        SpannableString signUpTextSpannable = new SpannableString(signUpText);

        int start_idx = signUpText.indexOf("Sign up");
        int end_idx = start_idx + "Sign up".length();

        signUpTextSpannable.setSpan(new StyleSpan(Typeface.BOLD), start_idx, end_idx, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUpTextSpannable.setSpan(new ForegroundColorSpan(Color.rgb(229, 56, 84)), start_idx, end_idx, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        signUp.setText(signUpTextSpannable);
        // String Span end

        // Intent Defs start
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(view -> {
            firebaseAuth.signInWithEmailAndPassword(emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim()).addOnSuccessListener(authResult -> startActivity(new Intent(MainActivity.this, Dashboard.class))).addOnFailureListener(e -> Toast.makeText(MainActivity.this, "Check your login details", Toast.LENGTH_SHORT).show());
        });

        signUp.setClickable(true);
        signUp.setOnClickListener(view -> {
            startActivity(new Intent(this, SignUpActivity.class));
        });
        // Intent Defs end
    }
}