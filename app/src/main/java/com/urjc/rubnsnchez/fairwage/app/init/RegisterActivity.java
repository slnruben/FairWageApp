package com.urjc.rubnsnchez.fairwage.app.init;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.contacts.Contact;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Objects;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.ERROR;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REGISTER;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REGISTER_SERVER;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.createContactBBDDValues;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.createUserBBDDValues;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.encryptBase64;
import static com.urjc.rubnsnchez.fairwage.app.common.CipherUtil.getKeyPair;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.checkEditTexts;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;

public class RegisterActivity extends Activity {
    Button sendButton, cancelButton;
    EditText userInput, passwordInput, wageInput;
    TextView askLogin;



    @Override
    public void onBackPressed() {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

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
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
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
            final Context context = getApplicationContext();
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                final BBDDHelper bbddHelper = ((MyApplication) context).getBbddHelper();
                if (checkEditTexts(new EditText[]{userInput, passwordInput, wageInput})) {
                    final Contact user = new Contact(new TextView[]{userInput, passwordInput, wageInput}, REGISTER);
                    final String urlServer = ((MyApplication) context).getUrlServer();
                    try {
                        KeyPair kp = getKeyPair();
                        ((MyApplication) context).setPrivateKey(kp.getPrivate());
                        ((MyApplication) context).setPublicKey(kp.getPublic());
                        user.setPassword(encryptBase64(kp.getPublic(), user.getPassword()));
                        user.setPrivateKey(kp.getPrivate().getEncoded());
                        user.setPublicKey(kp.getPublic().getEncoded());
                    } catch (Exception e) {
                        Log.e("error", "ERROR DE CIFRADO: " + e);
                    }
                    final HashMap<String, String> postDataParams = new HashMap<>();
                    postDataParams.put(context.getString(R.string.user), user.getUser());
                    postDataParams.put(context.getString(R.string.publicKey), Base64.encodeToString(user.getPublicKey(), Base64.NO_WRAP));
                    postDataParams.put(context.getString(R.string.wage), user.getWage());
                    Thread tr = new Thread() {
                        @Override
                        public void run() {
                            String insertedId = sendDataPOST(context, urlServer, REGISTER_SERVER, postDataParams);
                            if (!String.valueOf(ERROR).equals(insertedId)) {
                                final SQLiteDatabase db = bbddHelper.getWritableDatabase();
                                user.setIdServer(Integer.parseInt(insertedId));
                                ContentValues values = createUserBBDDValues(user);
                                db.insert(BBDDHelper.DataUser.TABLE_NAME, null, values);
                                values = createContactBBDDValues(user);
                                db.insert(BBDDHelper.DataContact.TABLE_NAME, null, values);
                                ((MyApplication) context).setUser(userInput.getText().toString());
                                Intent sendIntent = new Intent(RegisterActivity.this, SearchActivity.class);
                                startActivity(sendIntent);
                                finish();
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.duplicatedUser), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        }
                    };
                    tr.start();
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.emptyFields), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, getString(R.string.registerInternetRequired), Toast.LENGTH_SHORT).show();
            }
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
