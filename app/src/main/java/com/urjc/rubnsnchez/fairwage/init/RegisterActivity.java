package com.urjc.rubnsnchez.fairwage.init;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.init.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.init.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.init.contacts.SearchActivity;

import static com.urjc.rubnsnchez.fairwage.init.MyApplication.REGISTER;
import static com.urjc.rubnsnchez.fairwage.init.common.BBDDUtil.createUserBBDDValues;
import static com.urjc.rubnsnchez.fairwage.init.common.Util.checkEditTexts;

public class RegisterActivity extends Activity {
    Button sendButton, cancelButton;
    EditText userInput, passwordInput, wageInput;
    TextView askLogin;

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity
     * y activa los listener de los botones.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userInput = findViewById(R.id.userInput);
        passwordInput = findViewById(R.id.passwordInput);
        wageInput = findViewById(R.id.wageInput);
        askLogin = findViewById(R.id.askLogin);
        sendButton = findViewById(R.id.sendButton);
        cancelButton = findViewById(R.id.cancelButton);
        askLogin.setOnClickListener(new startLoginListener());
        sendButton.setOnClickListener(new sendButtonListener());
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Comprueba si el usuario ha introducido los campos necesarios para ser creados, si estan completos
     * guarda el nombre del usuario en el servidor e inicia la aplicacion.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Context context = getApplicationContext();
            final BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
            if (!checkEditTexts(new EditText[]{userInput, passwordInput, wageInput})) {
                Toast.makeText(RegisterActivity.this, getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
                return;
            }
            final SQLiteDatabase db = bbddHelper.getWritableDatabase();
            final Contact user = new Contact(new TextView[]{userInput, passwordInput, wageInput}, REGISTER);
            ContentValues values = createUserBBDDValues(user);
            db.insert(BBDDHelper.DataUser.TABLE_NAME, null, values);
            //enviarUsuarioServidor
            ((MyApplication) context).setUser(userInput.getText().toString());
            Intent sendIntent = new Intent(RegisterActivity.this, SearchActivity.class);
            startActivity(sendIntent);
            finish();
        }
    }

    private class startLoginListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }
}
