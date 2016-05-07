package info.idrishanafi.contact;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddContactScreen extends AppCompatActivity implements View.OnClickListener {

    EditText firstNameTextField, lastNameTextField, phoneTextField, emailTextField;
    Button addContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_layout);

        Fragment frag = getFragmentManager().findFragmentById(R.id.add_toolbar);
        ((TextView) frag.getView().findViewById(R.id.textView)).setText("Add Contact");

        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        lastNameTextField = (EditText) findViewById(R.id.lastNameTextField);
        phoneTextField = (EditText) findViewById(R.id.phoneTextField);
        emailTextField = (EditText) findViewById(R.id.emailTextField);
        addContactButton = (Button) findViewById(R.id.addContactButton);

        addContactButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String newFirstName = firstNameTextField.getText().toString();
        String newLastName = lastNameTextField.getText().toString();
        String newPhone = phoneTextField.getText().toString();
        String newEmail = emailTextField.getText().toString();

        if(newFirstName.matches("") || newLastName.matches("") ||
                newPhone.matches("") || newEmail.matches("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Sorry")
                    .setMessage("You Left a Required Field Blank")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else {
            /**new addContactActivity(this).execute(newFirstName,
                    newLastName, newPhone, newEmail);*/

        }
    }
}