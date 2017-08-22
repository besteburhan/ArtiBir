package besteburhan.artibir;

import android.app.ActionBar;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static besteburhan.artibir.R.id.editTextPassword;
import static besteburhan.artibir.R.id.imageView;
import static besteburhan.artibir.R.id.start;
import static com.bumptech.glide.Glide.with;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iVLoginScreen;
    EditText editTextEmail,editTextPassword;
    Button buttonsignIn;
    Button buttonSignUp;
    TextView textViewForgetPass;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        iVLoginScreen = (ImageView) findViewById(R.id.imageViewLoginScreen);
        editTextEmail = (EditText) findViewById(R.id.editTextEMail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonsignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textViewForgetPass = (TextView) findViewById(R.id.textViewForgetPassword);
        Glide.with(this).load("http://i68.tinypic.com/2nvba5w.jpg").into(iVLoginScreen);


        mAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Intent intent =new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };



    }

    @Override
    protected void onStart() {
        super.onStart();


        mAuth.addAuthStateListener(mAuthStateListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignUp:
                Intent intent= new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(intent);

                break;
            case R.id.buttonSignIn:
                String inputEmail ;
                String inputPassword;
                inputEmail = editTextEmail.getText().toString();
                inputPassword= editTextPassword.getText().toString();
                if(inputEmail == null || inputEmail.trim().equals("") || inputPassword == null || inputPassword.trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"Lütfen e-posta adresinizi ve şifrenizi giriniz.",Toast.LENGTH_LONG).show();
                }
                else{
                    mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        } else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                }
                break;
        }

    }
}
