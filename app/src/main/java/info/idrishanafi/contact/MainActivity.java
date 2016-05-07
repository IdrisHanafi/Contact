package info.idrishanafi.contact;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Contacts> myContacts = new ArrayList<Contacts>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateContactsList();
        populateListView();
        registerClickCallbad();
    }

    private void populateContactsList() {
        myContacts.add(new Contacts("Idris", "Hanafi", "203-911", "ifh101@gmail.com", R.drawable.obama));
        myContacts.add(new Contacts("Obama", "normal", "203-911-normal", "obama@normal.com", R.drawable.obamanormal));
        myContacts.add(new Contacts("Obama", "smile", "203-911-smile", "obama@smile.com", R.drawable.obamasmile));
    }

    private void populateListView() {
        ArrayAdapter<Contacts> adapter = new ContactsListAdapter();
        ListView contactList = (ListView) findViewById(R.id.contact_listView);

        contactList.setAdapter(adapter);
    }

    private void registerClickCallbad() {
        ListView list = (ListView) findViewById(R.id.contact_listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Contacts clickContact = myContacts.get(position);
                Toast.makeText(MainActivity.this, "You Clicked " + clickContact.getLastName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Array List Adapter
    private class ContactsListAdapter extends ArrayAdapter<Contacts> {
        public ContactsListAdapter() {
            super(MainActivity.this, R.layout.item_view, myContacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
            }

            Contacts currentContacts = myContacts.get(position);

            // Icon
            ImageView imageView = (ImageView) itemView.findViewById(R.id.contact_Icon);
            imageView.setImageResource(currentContacts.getIcon());
            // First Name
            TextView firstName = (TextView) itemView.findViewById(R.id.contact_firstName);
            firstName.setText(currentContacts.getFirstName());
            // Last Name
            TextView lastName = (TextView) itemView.findViewById(R.id.contact_lastName);
            lastName.setText(currentContacts.getLastName());
            // Phone
            TextView phone = (TextView) itemView.findViewById(R.id.contact_phone);
            phone.setText(currentContacts.getPhone());
            // Email
            TextView email = (TextView) itemView.findViewById(R.id.contact_email);
            email.setText(currentContacts.getEmail());

            return itemView;
        }
    }
}