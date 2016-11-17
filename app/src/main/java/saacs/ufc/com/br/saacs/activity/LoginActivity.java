package saacs.ufc.com.br.saacs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import saacs.ufc.com.br.saacs.R;

/**
 * Created by wallinsondeivesbatistalima on 11/17/16.
 */

public class LoginActivity extends Activity{

    Button loginButton, recuperarButton;
    EditText numSUSText, passwordText;
    TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        loginButton = (Button)findViewById(R.id.loginButton);
        recuperarButton = (Button)findViewById(R.id.recuperarButton);
        numSUSText = (EditText)findViewById(R.id.numSUSText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        signUpTextView = (TextView)findViewById(R.id.signUpTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String susNumber = numSUSText.getText().toString();
                String password = passwordText.getText().toString();
            }
        });
    }

}
