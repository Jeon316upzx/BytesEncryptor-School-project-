package com.example.ifeanyi.bytesencryptor;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends Activity {


      EditText r_e1,r_e2;
      Button signup;
      FirebaseAuth auth;

      String reg_email;
      String reg_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        r_e1 = (EditText) findViewById(R.id.r_MyEmailEt);
        r_e2 = (EditText) findViewById(R.id.r_MypasscodeEt);

        signup = (Button) findViewById(R.id.buttonSignUp);

        auth = FirebaseAuth.getInstance();

        reg_email = r_e1.getText().toString();
        reg_pass = r_e2.getText().toString();

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Creating new User....");
                dialog.show();
                dialog.setCancelable(false);
                if(r_e1.getText().toString().isEmpty() || r_e2.getText().toString().isEmpty())
                {
                    Snackbar.make(getCurrentFocus(),"One textbox is empty, fill it up and continue",Snackbar.LENGTH_LONG).show();
                }
                else
                {


                    auth.createUserWithEmailAndPassword(r_e1.getText().toString(),r_e2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                dialog.cancel();
                                Toast.makeText(SignUp.this,"User created",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                dialog.cancel();
                                Toast.makeText(SignUp.this,"Failed to create user, make sure you have internet connection, try again",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUp.this,Login.class);
                                intent.putExtra("Intent_Email",reg_email);
                                startActivity(intent);
                            }

                        }
                    });


                }


            }
        });
    }
}
