package saacs.ufc.com.br.saacs.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import saacs.ufc.com.br.saacs.R;
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
                String password = passwordText.getText().toString();
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
                                sessionManager.createLoginSession(susNumber);
                                onLoginSuccess();

                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
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
