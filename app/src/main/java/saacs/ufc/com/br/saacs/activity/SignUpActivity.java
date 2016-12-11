package saacs.ufc.com.br.saacs.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import saacs.ufc.com.br.saacs.R;
import saacs.ufc.com.br.saacs.dao.AcsDAO;
import saacs.ufc.com.br.saacs.model.Acs;
import saacs.ufc.com.br.saacs.other.SessionManager;

public class SignUpActivity extends AppCompatActivity {

    EditText nomeText, passwordText, passwordConfirmText, susNumberText;
    TextView loginTextView;
    Button signUpButton;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sessionManager = new SessionManager(SignUpActivity.this);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        nomeText = (EditText) findViewById(R.id.nomeText);
        passwordText = (EditText) findViewById(R.id.passText);
        passwordConfirmText = (EditText) findViewById(R.id.passConfirmText);
        susNumberText = (EditText) findViewById(R.id.textViewSusNumber);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validate()){
                    onSignUpFailed();
                    return;
                }
                signUpButton.setEnabled(false);
                final String susNumber = susNumberText.getText().toString();
                final String nome = nomeText.getText().toString();
                final String pass = passwordText.getText().toString();
                final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Criando conta...");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                AcsDAO acsDAO = new AcsDAO(getApplicationContext());
                                Acs a = new Acs();
                                a.setSusNumber(Long.parseLong(susNumber));
                                a.setNome(nome);
                                a.setPassword(pass);
                                if (acsDAO.inserir(a)) {
                                    sessionManager.createLoginSession(susNumber);
                                    onSignUpSuccess();
                                } else{
                                    onSignUpFailed();
                                }
                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent i = new Intent();
                setResult(RESULT_CANCELED, getIntent());
                finish();
            }
        });

    }

    public boolean validate(){
        boolean valid = true;

        String susNumber = susNumberText.getText().toString();
        String password = passwordText.getText().toString();
        String passwordConfirm = passwordConfirmText.getText().toString();
        String nome = nomeText.getText().toString();
        if (nome.isEmpty()){
            nomeText.setError("Nome não pode ser vazio!");
            valid = false;
        }
        if (susNumber.isEmpty()) {
            susNumberText.setError("Digite um número SUS válido");
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
        if (!password.equals(passwordConfirm)){
            passwordConfirmText.setError("Senha não confere!");
            valid = false;
        }
        if (!susNumber.isEmpty()){
            AcsDAO acsDAO = new AcsDAO(getApplicationContext());
            Acs a = acsDAO.recuperar(susNumber);
            if (a != null){
                susNumberText.setError("Numero SUS informado já está cadastrado!");
                valid = false;
            }
        }
        return valid;
    }

    public void onSignUpSuccess(){
        Toast.makeText(getBaseContext(), "Conta criada com sucesso!", Toast.LENGTH_LONG).show();
        signUpButton.setEnabled(true);
        Intent i = new Intent();
        String susNumber = susNumberText.getText().toString();
        i.putExtra("susNumber", susNumber);
        setResult(RESULT_OK, i);
        finish();
    }

    public void onSignUpFailed(){
        Toast.makeText(getBaseContext(), "Criar conta Falhou!", Toast.LENGTH_LONG).show();

        signUpButton.setEnabled(true);
    }

}
