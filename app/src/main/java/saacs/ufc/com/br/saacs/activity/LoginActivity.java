package saacs.ufc.com.br.saacs.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.StringTokenizer;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.dao.AcsDAO;
import saacs.ufc.com.br.saacs.model.Acs;
import saacs.ufc.com.br.saacs.other.SessionManager;

/**
 * Created by wallinsondeivesbatistalima on 11/17/16.
 */

public class LoginActivity extends AppCompatActivity{

    Button loginButton, recuperarButton;
    EditText numSUSText, passwordText;
    TextView signUpTextView;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(LoginActivity.this);

        loginButton = (Button)findViewById(R.id.loginButton);
        recuperarButton = (Button)findViewById(R.id.recuperarButton);
        numSUSText = (EditText)findViewById(R.id.numSUSText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        signUpTextView = (TextView)findViewById(R.id.signUpTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String susNumber = numSUSText.getText().toString();
                final String password = passwordText.getText().toString();
                if (!validade()){
                    onLoginFailed();
                    return;
                }
                loginButton.setEnabled(false);
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                // On complete call either onLoginSuccess or onLoginFailed
                                AcsDAO acsDAO = new AcsDAO(LoginActivity.this);
                                Acs a = acsDAO.recuperar(susNumber);
                                if ( a!= null && a.getPassword().equals(password)) {
                                    sessionManager.createLoginSession(susNumber);
                                    onLoginSuccess();
                                } else {
                                    numSUSText.setError("Numero SUS ou senha incorretos!");
                                    onLoginFailed();
                                }
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(i,100);
                //finish();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==100){
                System.out.println("AQUI 2");
                //String susNumber = data.getStringExtra("susNumber");
                if (sessionManager.isLoggedIn()){
                    System.out.println("AQUI 3");
                    onLoginSuccess();
                }
            }
        }

    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(false);
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login Falhou", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validade(){
        boolean valid = true;

        String susNumber = numSUSText.getText().toString();
        String password = passwordText.getText().toString();

        if (susNumber.isEmpty()) {
            numSUSText.setError("Digite um número SUS válido");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8 || password.length() > 16) {
            passwordText.setError("Entre 8 e 16 caracteres");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }

}
