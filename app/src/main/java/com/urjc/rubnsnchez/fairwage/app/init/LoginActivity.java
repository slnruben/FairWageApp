package com.urjc.rubnsnchez.fairwage.app.init;

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
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;
import java.security.PublicKey;
import java.util.HashMap;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.LOGIN;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.LOGIN_SERVER;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.getUserData;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.byteArrayToPrivateKey;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.byteArrayToPublicKey;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.encryptBase64;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.checkEditTexts;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;

public class LoginActivity extends Activity {
    Button sendButton, cancelButton;
    EditText userInput, passwordInput;
    TextView askRegister;

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

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
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
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
            final Contact user = new Contact(new TextView[]{userInput, passwordInput}, LOGIN);
            ((MyApplication) context).setUser(userInput.getText().toString());

            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.user), user.getUser());
            Thread tr = new Thread() {
                @Override
                public void run() {
                    String success = sendDataPOST(context, urlServer, LOGIN_SERVER, postDataParams);
                    if (String.valueOf(SUCCESS).equals(success)) {
                        Contact contact = getUserData(context, user.getUser(), LOGIN);
                        PublicKey publicKey = byteArrayToPublicKey(contact.getPublicKey());
                        ((MyApplication) context).setPrivateKey(byteArrayToPrivateKey(contact.getPrivateKey()));
                        ((MyApplication) context).setPublicKey(publicKey);
                        String encryptedPassword = encryptBase64(publicKey, user.getPassword());

                        if (contact.getPassword().equals(encryptedPassword)) {
                            ((MyApplication) context).setUser(userInput.getText().toString());
                            Intent sendIntent = new Intent(LoginActivity.this, SearchActivity.class);
                            startActivity(sendIntent);
                            finish();
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.loginError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    } else {
                        runOnUiThread(new Thread(new Runnable() {
                            public void run() {
                                Toast.makeText(context, getString(R.string.loginError), Toast.LENGTH_LONG).show();
                            }
                        }));
                    }
                }
            };
            tr.start();
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
