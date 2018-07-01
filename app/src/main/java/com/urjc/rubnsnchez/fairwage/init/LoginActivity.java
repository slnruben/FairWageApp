package com.urjc.rubnsnchez.fairwage.init;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.init.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.init.contacts.SearchActivity;
import static com.urjc.rubnsnchez.fairwage.init.MyApplication.LOGIN;
import static com.urjc.rubnsnchez.fairwage.init.common.BBDDUtil.checkLoginUser;
import static com.urjc.rubnsnchez.fairwage.init.common.Util.checkEditTexts;

public class LoginActivity extends Activity {
    Button sendButton, cancelButton;
    EditText userInput, passwordInput;
    TextView askRegister;

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity
     * y activa los listener de los botones.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final Context context = getApplicationContext();
        ((MyApplication) context).createBBDDHelper();
        userInput = findViewById(R.id.userInput);
        passwordInput = findViewById(R.id.passwordInput);
        askRegister = findViewById(R.id.askRegister);
        sendButton = findViewById(R.id.sendButton);
        cancelButton = findViewById(R.id.cancelButton);
        askRegister.setOnClickListener(new startRegisterListener());
        sendButton.setOnClickListener(new sendButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Context context = getApplicationContext();
            if (!checkEditTexts(new EditText[]{userInput, passwordInput})) {
                Toast.makeText(LoginActivity.this, getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
                return;
            }
            Contact user = new Contact(new TextView[]{userInput, passwordInput}, LOGIN);
            if (checkLoginUser(context, user) == 1) {
                ((MyApplication) context).setUser(userInput.getText().toString());
                Intent sendIntent = new Intent(LoginActivity.this, SearchActivity.class);
                startActivity(sendIntent);
                finish();
            } else {
                runOnUiThread(new Thread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, getString(R.string.incorrectLogin), Toast.LENGTH_SHORT).show();
                    }
                }));
            }

        }
    }

    private class startRegisterListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
            finish();
        }
    }
}
