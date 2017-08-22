package besteburhan.artibir;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextName,editTextLastName,editTextEmail,editTextPassword;
    Button buttonSignUp;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Kayıt Ol");

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) findViewById(R.id.editTextEMail);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        mAuth = FirebaseAuth.getInstance();



    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignUp:
                if(!isEditTextEmpty()){
                    String inputName = editTextName.getText().toString();
                    String inputLastName = editTextLastName.getText().toString();
                    String inputEmail = editTextEmail.getText().toString();
                    String inputPassword = editTextPassword.getText().toString();
                    mAuth.createUserWithEmailAndPassword(inputEmail,inputPassword).addOnCompleteListener(
                            new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else{
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });


                }


                break;

        }
    }

    private boolean isEditTextEmpty() {
        String inputName = editTextName.getText().toString();
        String inputLastName = editTextLastName.getText().toString();
        String inputEmail = editTextEmail.getText().toString();
        String inputPassword = editTextPassword.getText().toString();

        String show ="";

        if(inputName == null || inputName.trim().equals("")){
            show = show+"İsim ";
        }
        if(inputLastName == null || inputLastName.trim().equals("")){
            show = show+"Soy İsim ";
        }
        if(inputEmail == null || inputEmail.trim().equals("")){
            show = show+"E posta adresi ";
        }
        if(inputPassword == null || inputPassword.trim().equals("")){
            show = show+"Şifre ";
        }
        if (show.equals("")){
           return false;
        }
        else{
            Toast.makeText(SignUpActivity.this, show+"boş geçilemez!", Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
