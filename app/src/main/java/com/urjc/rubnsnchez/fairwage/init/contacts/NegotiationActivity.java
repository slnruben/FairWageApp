package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.urjc.rubnsnchez.fairwage.R;

public class NegotiationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negotiation_request);
        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new sendButtonListener());
    }

    /**
     * Comprueba si el usuario introducido en los campos concuerda con los datos de la BBDD del servidor,
     * si es correcto permite el acceso a la aplicacion y guarda el usuario para futuras sesiones.
     */
    private class sendButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent sendIntent = new Intent(NegotiationActivity.this, ContactListActivity.class);
            startActivity(sendIntent);
        }
    }
}

