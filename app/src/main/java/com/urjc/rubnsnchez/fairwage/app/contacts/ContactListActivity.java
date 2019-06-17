package com.urjc.rubnsnchez.fairwage.app.contacts;

import android.app.Activity;
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
import java.util.ArrayList;
import static com.urjc.rubnsnchez.fairwage.app.MyApplication.SEARCH;
import static com.urjc.rubnsnchez.fairwage.app.common.Util.createFullNameBundle;

public class ContactListActivity extends Activity {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private ListView listView;

    /**
     * Ejecuta un SELECT de una base de datos local y añade el resultado a una lista.
     */
    private void readTable() {
        BBDDHelper bbddHelper = ((MyApplication) getApplicationContext()).getBbddHelper();
        SQLiteDatabase db = bbddHelper.getWritableDatabase();
        String query = "SELECT * FROM " + BBDDHelper.DataSearchContact.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        Contact contact;
        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                contact = new Contact(c, SEARCH);
                contactList.add(contact);
                listView.requestLayout();
            } while (c.moveToNext());
        } else {
            String str = getString(R.string.readError) + BBDDHelper.DataContact.TABLE_NAME;
            Toast.makeText(ContactListActivity.this, str, Toast.LENGTH_SHORT).show();
        }
        c.close();
        //sortByName();
        setList(contactList);
    }

    /**
     * Inserta en el ListView los valores de una lista.
     * @param showList Lista de contactos que se quiere mostrar.
     */
    private void setList(ArrayList<Contact> showList) {
        MyAdapter adapter = new MyAdapter(ContactListActivity.this, showList);
        listView.setAdapter(adapter);
    }

    /**
     * Primer metodo que se ejecuta, obtiene los diferentes elementos xml de la activity,
     * lee todos los contactos de una BBDD local y los muestra en el listado.
     * Ademas activa los listener de los botones y del filtro.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_listview);
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
            Contact contact = (Contact) listView.getItemAtPosition(position);
            Intent intent = new Intent(ContactListActivity.this, ProfileContactActivity.class);
            Bundle bundle = createFullNameBundle(contact);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
