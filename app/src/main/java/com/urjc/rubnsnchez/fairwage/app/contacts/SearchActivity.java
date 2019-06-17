package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.init.LoginActivity;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.negotiations.NegotationListActivity;

import java.util.HashMap;
import java.util.Objects;

import static com.urjc.rubnsnchez.fairwage.app.MyApplication.REFUSED;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_CONTACTS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH_NEGOTIATIONS;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SUCCESS;
import static com.urjc.rubnsnchez.fairwage.app.common.BBDDUtil.tableRowCount;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.isNetworkAvailable;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.sendDataPOST;

public class SearchActivity extends Activity {
    EditText wageInput, jobInput, sector1Input, sector2Input, knowledgesInput;

    private void searchContacts() {
        String wage = wageInput.getText().toString();
        String job = jobInput.getText().toString();
        String sector1 = sector1Input.getText().toString();
        String sector2 = sector2Input.getText().toString();
        String knowledges = knowledgesInput.getText().toString();

        if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
            final Context context = getApplicationContext();
            String user = ((MyApplication) context).getUser();
            final String urlServer = ((MyApplication) context).getUrlServer();
            final HashMap<String, String> postDataParams = new HashMap<>();
            postDataParams.put(context.getString(R.string.user), user);
            postDataParams.put(context.getString(R.string.wage), wage);
            postDataParams.put(context.getString(R.string.job), job);
            postDataParams.put(context.getString(R.string.sector1), sector1);
            postDataParams.put(context.getString(R.string.sector2), sector2);
            postDataParams.put(context.getString(R.string.knowledges), knowledges);

            Thread tr = new Thread() {
                @Override
                public void run() {
                    Intent sendIntent = new Intent(SearchActivity.this, ContactListActivity.class);
                    String success = sendDataPOST(context, urlServer, SEARCH_CONTACTS, postDataParams);
                    if (String.valueOf(SUCCESS).equals(success)) {
                        startActivity(sendIntent);
                    } else {
                        runOnUiThread(new Thread(new Runnable() {
                            public void run() {
                                Toast.makeText(context, getString(R.string.searchError), Toast.LENGTH_LONG).show();
                            }
                        }));
                    }
                }
            };
            tr.start();
        } else {
            Toast.makeText(SearchActivity.this, getString(R.string.searchInternetRequired), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity
     * y activa los listener de los botones.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        final Context context = getApplicationContext();
        ((MyApplication) context).createBBDDHelper();

        wageInput = findViewById(R.id.wageInput);
        jobInput = findViewById(R.id.jobInput);
        sector1Input = findViewById(R.id.sector1Input);
        sector2Input = findViewById(R.id.sector2Input);
        knowledgesInput = findViewById(R.id.knowledgesInput);
        Button profileButton = findViewById(R.id.profileButton);
        Button negotiationButton = findViewById(R.id.negotationButton);
        Button editButton = findViewById(R.id.editButton);
        Button sendButton = findViewById(R.id.sendButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        profileButton.setOnClickListener(new profileButtonListener());
        negotiationButton.setOnClickListener(new negotationButtonListener());
        editButton.setOnClickListener(new editButtonListener());
        sendButton.setOnClickListener(new sendButtonListener());
        logoutButton.setOnClickListener(new logoutButtonListener());
    }

    private class profileButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(SearchActivity.this, ProfileActivity.class);
            startActivity(sendIntent);
        }
    }

    private class negotationButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (isNetworkAvailable((ConnectivityManager) Objects.requireNonNull(getSystemService(Context.CONNECTIVITY_SERVICE)))) {
                final Context context = getApplicationContext();
                String user = ((MyApplication) context).getUser();
                final String urlServer = ((MyApplication) context).getUrlServer();
                final HashMap<String, String> postDataParams = new HashMap<>();
                postDataParams.put(context.getString(R.string.user), user);
                postDataParams.put(context.getString(R.string.state), REFUSED);
                Thread tr = new Thread() {
                    @Override
                    public void run() {
                        Intent sendIntent = new Intent(SearchActivity.this, NegotationListActivity.class);
                        String success = sendDataPOST(context, urlServer, SEARCH_NEGOTIATIONS, postDataParams);
                        if (String.valueOf(SUCCESS).equals(success)) {
                            final int numRows = tableRowCount(context, BBDDHelper.DataSearchNegotation.TABLE_NAME);
                            if (numRows > 0) {
                                startActivity(sendIntent);
                            } else {
                                runOnUiThread(new Thread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(context, getString(R.string.noNegotiations), Toast.LENGTH_LONG).show();
                                    }
                                }));
                            }
                        } else {
                            runOnUiThread(new Thread(new Runnable() {
                                public void run() {
                                    Toast.makeText(context, getString(R.string.searchNegotationsError), Toast.LENGTH_LONG).show();
                                }
                            }));
                        }
                    }
                };
                tr.start();
            } else {
                Toast.makeText(SearchActivity.this, getString(R.string.searchInternetRequired), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class editButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(SearchActivity.this, EditActivity.class);
            startActivity(sendIntent);
        }
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            searchContacts();
        }
    }

    /**
     * Desconecta al usuario actual y lanza la pantalla de login.
     */
    private class logoutButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(SearchActivity.this, LoginActivity.class);
            startActivity(sendIntent);
            finish();
        }
    }
}

