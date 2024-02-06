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

public class SignUpActivity extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText emailEditText = findViewById(R.id.email_editText_signup);
        EditText passwordEditText = findViewById(R.id.password_editText_signup);
        EditText confirmPasswordEditText = findViewById(R.id.confirm_password_editText_signup);
        Button signupButton = findViewById(R.id.signup_button);
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
            confirmPasswordEditText.setOnTouchListener((v, event) -> {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (confirmPasswordEditText.getRight() - finalDrawable.getBounds().width())) {
                        if (isPasswordVisible[0]) {
                            confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        } else {
                            confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        }
                        isPasswordVisible[0] = !isPasswordVisible[0];
                        return true;
                    }
                }
                return false;
            });
        }
        // Show password logic end

        // String Spanning start
        TextView alreadyHaveAnAccount = findViewById(R.id.already_login_up_clickable);
        String alreadyHaveAnAccountText = alreadyHaveAnAccount.getText().toString();
        SpannableString spannableString = new SpannableString(alreadyHaveAnAccountText);

        int start_idx = alreadyHaveAnAccountText.indexOf("Sign In");
        int end_idx = start_idx + "Sign In".length();

        spannableString.setSpan(new StyleSpan(Typeface.BOLD), start_idx, end_idx, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(229, 56, 84)), start_idx, end_idx, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        alreadyHaveAnAccount.setText(spannableString);
        // String Spanning end

        // Intent defs start
        signupButton.setOnClickListener(view -> {
            String emailText = emailEditText.getText().toString().trim();
            String passwordText = passwordEditText.getText().toString().trim();
            String confirmPasswordText = confirmPasswordEditText.getText().toString().trim();

            if (passwordText.equals(confirmPasswordText)) {
                firebaseAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnSuccessListener(authResult -> {
                    Toast.makeText(SignUpActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                }).addOnFailureListener(e -> Toast.makeText(SignUpActivity.this, "Error creating account", Toast.LENGTH_SHORT).show());
            }
            else {
                Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            }
        });

        alreadyHaveAnAccount.setClickable(true);
        alreadyHaveAnAccount.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        // Intent defs end
    }
}