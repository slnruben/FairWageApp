package com.urjc.rubnsnchez.fairwage.app.negotiations;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.urjc.rubnsnchez.fairwage.R;
import com.urjc.rubnsnchez.fairwage.app.MyApplication;
import com.urjc.rubnsnchez.fairwage.app.common.BBDDHelper;
import com.urjc.rubnsnchez.fairwage.app.contacts.SearchActivity;

import java.util.ArrayList;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.createFullNegotiationBundle;

public class NegotationListActivity extends Activity {
    private ArrayList<Negotiation> negotiationList = new ArrayList<>();
    private ListView listView;

    private void readTable() {
        BBDDHelper bbddHelper = ((MyApplication) getApplicationContext()).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();
        String query = "SELECT * FROM " + BBDDHelper.DataSearchNegotation.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        Negotiation negotiation;
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                negotiation = new Negotiation(c);
                negotiationList.add(negotiation);
                listView.requestLayout();
            } while (c.moveToNext());
        } else {
            String str = getString(R.string.readError) + BBDDHelper.DataSearchNegotation.TABLE_NAME;
            Toast.makeText(NegotationListActivity.this, str, Toast.LENGTH_SHORT).show();
        }
        c.close();
        //sortByName();
        setList(negotiationList);
    }

    /**
     * Inserta en el ListView los valores de una lista.
     * @param showList Lista de contactos que se quiere mostrar.
     */
    private void setList(ArrayList<Negotiation> showList) {
        final Context context = getApplicationContext();
        String user = ((MyApplication) context).getUser();
        MyNegotationAdapter adapter = new MyNegotationAdapter(NegotationListActivity.this, showList, user);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NegotationListActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity,
     * lee todos los contactos de una BBDD local y los muestra en el listado.
     * Ademas activa los listener de los botones y del filtro.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.negotation_listview);
        listView = findViewById(R.id.myListView);
        readTable();
        listView.setOnItemClickListener(new ItemListViewListener());
    }

    /**
     * Lanza la Activity que muestra el perfil del contacto seleccionado.
     */
    private class ItemListViewListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Negotiation negotiation = (Negotiation) listView.getItemAtPosition(position);
            Intent intent = new Intent(NegotationListActivity.this, ProfileReceivedContactNegotiationActivity.class);
            Bundle bundle = createFullNegotiationBundle(negotiation);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
