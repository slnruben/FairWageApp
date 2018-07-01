package com.urjc.rubnsnchez.fairwage.init.contacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.urjc.rubnsnchez.fairwage.R;

import java.util.ArrayList;

public class ContactListActivity extends Activity {
    private ArrayList<Contact> contactList = new ArrayList<>();
    private ListView listView;

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

        Contact c1 = new Contact("Anónimo1", "10000");
        Contact c2 = new Contact("Anónimo2", "9000");
        Contact c3 = new Contact("Anónimo3", "8000");
        Contact c4 = new Contact("Anónimo4", "7000");
        Contact c5 = new Contact("Anónimo5", "6000");
        Contact c6 = new Contact("Anónimo6", "5000");
        Contact c7 = new Contact("Anónimo7", "4000");
        Contact c8 = new Contact("Anónimo8", "3000");
        Contact c9 = new Contact("Anónimo9", "2000");
        Contact c10 = new Contact("Anónimo10", "1000");
        Contact c11 = new Contact("Anónimo11", "900");
        Contact c12 = new Contact("Anónimo12", "800");
        Contact c13 = new Contact("Anónimo13", "700");
        Contact c14 = new Contact("Anónimo14", "600");
        Contact c15 = new Contact("Anónimo15", "500");
        Contact c16 = new Contact("Anónimo16", "400");
        contactList.add(c1);
        listView.requestLayout();
        contactList.add(c2);
        listView.requestLayout();
        contactList.add(c3);
        listView.requestLayout();
        contactList.add(c4);
        listView.requestLayout();
        contactList.add(c5);
        listView.requestLayout();
        contactList.add(c6);
        listView.requestLayout();
        contactList.add(c7);
        listView.requestLayout();
        contactList.add(c8);
        listView.requestLayout();
        contactList.add(c9);
        listView.requestLayout();
        contactList.add(c10);
        listView.requestLayout();
        contactList.add(c11);
        listView.requestLayout();
        contactList.add(c12);
        listView.requestLayout();
        contactList.add(c13);
        listView.requestLayout();
        contactList.add(c14);
        listView.requestLayout();
        contactList.add(c15);
        listView.requestLayout();
        contactList.add(c16);
        listView.requestLayout();
        setList(contactList);

        listView.setOnItemClickListener(new ItemListViewListener());
    }

    /**
     * Genera un Bundle con los datos de un contacto para pasarselos a otra activity.
     * @param   fullname Contacto con sus datos
     * @return  Un Bundle.
     */
    public static Bundle createFullNameBundle(Contact fullname) {
        Bundle bundle = new Bundle();
        bundle.putString(String.valueOf(R.string.user), fullname.getUser());
        bundle.putString(String.valueOf(R.string.wage), fullname.getWage());
        return bundle;
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
