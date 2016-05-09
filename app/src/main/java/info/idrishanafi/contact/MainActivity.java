package info.idrishanafi.contact;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public List<Contacts> myContacts = new ArrayList<Contacts>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            populateContactsList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        populateListView();
        registerClickCallbad();
    }

    private void populateContactsList() throws ExecutionException, InterruptedException {

        // Call PHP file to execute query to set the values here
       String[] values = new viewContactActivity(this).execute().get();
        for(int i = 0; i < values.length; i++) {
            Log.d("values", values[i]);
            String[] splitted = values[i].split("\\s+");
            myContacts.add(new Contacts(Integer.parseInt(splitted[0]), splitted[1], splitted[2], splitted[3], splitted[4], splitted[5], R.drawable.defaultpicture));
        }
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
                final int deletedPosition = position;
                final Contacts clickContact = myContacts.get(position);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                builder1.setMessage("Are you sure you want to delete " + clickContact.getFirstName() + " " + clickContact.getLastName());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(MainActivity.this, "Deleting " + clickContact.getFirstName() + " " + clickContact.getLastName(), Toast.LENGTH_SHORT).show();
                                new removeContactActivity(MainActivity.this, clickContact.getId()).execute();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

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
            // Set Image Below
            new DownloadImage(currentContacts.getImageName(), imageView).execute();
            //imageView.setImageResource(currentContacts.getIcon());
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