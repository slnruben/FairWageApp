package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.urjc.rubnsnchez.fairwage.R;

public class ProfileContactActivity extends Activity {
    private Contact fullName;

    private void setTextViews() {
        TextView user = findViewById(R.id.user);
        TextView wage = findViewById(R.id.wage);
        user.setText(fullName.getUser());
        wage.setText(fullName.getWage());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_profile);
        Intent intent = getIntent();
        Bundle info = intent.getExtras();
        if (info != null) {
            fullName = new Contact(info);
            setTextViews();
        }
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
            Intent sendIntent = new Intent(ProfileContactActivity.this, NegotiationActivity.class);
            startActivity(sendIntent);
        }
    }
}
