package com.example.ifeanyi.bytesencryptor;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {

    Button button1;
    EditText editText1,editText2;
    TextView textView;
    FirebaseAuth firebaseAuth;

    ImageView facebk,google,twitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button1 = (Button) findViewById(R.id.buttonSignin);
        editText1 = (EditText) findViewById(R.id.MyEmailEt);
        editText2 = (EditText) findViewById(R.id.MypasscodeEt);
        textView = (TextView) findViewById(R.id.registrationTv);


        firebaseAuth = FirebaseAuth.getInstance();


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ProgressDialog dialog = new ProgressDialog(Login.this);
//                dialog.setMessage("Verifying details...");
//                dialog.show();
//                dialog.setCancelable(false);
//
//                if(editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty())
//                {
//                    Snackbar.make(getCurrentFocus(),"Email or passcode textbox is empty",Snackbar.LENGTH_LONG).show();
//                }
//                else
//                {
//
//                   firebaseAuth.signInWithEmailAndPassword(editText1.getText().toString(),editText2.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                       @Override
//                       public void onComplete(Task<AuthResult> task) {
//                           if(task.isSuccessful())
//                           {
//                               Intent in = new Intent(Login.this,MainActivity.class);
//                               startActivity(in);
//                           }
//                           else
//                           {
//
//                               Toast.makeText(Login.this, "Failed to sign in, check internet or the email and password entered", Toast.LENGTH_SHORT).show();
//                           }
//
//                       }
//                   }) ;
//
//                }

                Intent in = new Intent(Login.this,MainActivity.class);
                            startActivity(in);

            }
        });





        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);

            }
        });

    }
}
