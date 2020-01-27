package com.example.topcricketplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerificationOTPActivity extends AppCompatActivity {


    FirebaseAuth mauth;
    FirebaseUser currentUser;

    EditText etTextOtp;

    ProgressBar progressBarOtp;

    Button btnVerify;


    String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_otp);

        mauth=FirebaseAuth.getInstance();
        currentUser=mauth.getCurrentUser();

        etTextOtp=findViewById(R.id.etOtp);

        verificationID=getIntent().getStringExtra("AuthCredintials");


//        clickListenerVerify=new VerifyButtonClickListener();




        progressBarOtp=findViewById(R.id.progressbarOtp);
        btnVerify=findViewById(R.id.btnVerfiy);



        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp=etTextOtp.getText().toString();

                if (otp.isEmpty()){
                    Toast.makeText(VerificationOTPActivity.this, "Please enter OTP. OTP is empty now", Toast.LENGTH_SHORT).show();
                }else {
                    btnVerify.setEnabled(false);
                    progressBarOtp.setVisibility(View.VISIBLE);

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);


                    mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                                                     @Override
                                                                                     public void onComplete(@NonNull Task<AuthResult> task) {

                             if (task.isSuccessful()){
                                 goToMainActivity();
                             }
                             else {

                                 Toast.makeText(VerificationOTPActivity.this, "Oh Sorry a problem occured while sending otp", Toast.LENGTH_SHORT).show();

                             }

                             progressBarOtp.setVisibility(View.INVISIBLE);
                             btnVerify.setEnabled(true);


                         }


                     }
                    );



                }


            }
        });

    }


    private void goToMainActivity() {


        Intent intent=new Intent(VerificationOTPActivity.this,MainActivity.class);
        startActivity(intent);
        finish();

    }




    @Override
    protected void onStart() {
        super.onStart();


        if (currentUser!=null){
            goToMainActivity();

        }
    }

//
//    public class VerifyButtonClickListener implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//
//        }
//    }

}

