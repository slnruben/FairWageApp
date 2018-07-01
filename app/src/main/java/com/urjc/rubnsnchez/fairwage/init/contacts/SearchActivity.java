package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.init.LoginActivity;
import com.urjc.rubnsnchez.fairwage.init.MyApplication;

public class SearchActivity extends Activity {
    Button profileButton, editButton, sendButton, logoutButton;
    EditText wageInput, jobInput, areaInput, sectorInput, knowledgesInput;

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
        areaInput = findViewById(R.id.areaInput);
        sectorInput = findViewById(R.id.sectorInput);
        knowledgesInput = findViewById(R.id.knowledgesInput);
        profileButton= findViewById(R.id.profileButton);
        editButton= findViewById(R.id.editButton);
        sendButton = findViewById(R.id.sendButton);
        logoutButton = findViewById(R.id.logoutButton);
        profileButton.setOnClickListener(new profileButtonListener());
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
            Intent sendIntent = new Intent(SearchActivity.this, ContactListActivity.class);
            startActivity(sendIntent);
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

