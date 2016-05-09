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
import android.widget.Toast;

public class settingsScreen extends AppCompatActivity implements View.OnClickListener {

    EditText firstNameTextField, lastNameTextField, phoneTextField, emailTextField;
    Button addContactButton;
    TextView changePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        Fragment frag = getFragmentManager().findFragmentById(R.id.add_toolbar);
        ((TextView) frag.getView().findViewById(R.id.textView)).setText("Settings");

        firstNameTextField = (EditText) findViewById(R.id.firstNameTextField);
        lastNameTextField = (EditText) findViewById(R.id.lastNameTextField);
        phoneTextField = (EditText) findViewById(R.id.phoneTextField);
        emailTextField = (EditText) findViewById(R.id.emailTextField);
        addContactButton = (Button) findViewById(R.id.addContactButton);
        changePicture = (TextView) findViewById(R.id.changePicture);

        firstNameTextField.setText(PersonalInfo.getFirstName());
        lastNameTextField.setText(PersonalInfo.getLastName());
        phoneTextField.setText(PersonalInfo.getPhone());
        emailTextField.setText(PersonalInfo.getEmail());

        changePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(settingsScreen.this, "Change Picture", Toast.LENGTH_SHORT).show();
            }
        });

        addContactButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String newFirstName = firstNameTextField.getText().toString();
        String newLastName = lastNameTextField.getText().toString();
        String newPhone = phoneTextField.getText().toString();
        String newEmail = emailTextField.getText().toString();

        PersonalInfo.setFirstName(newFirstName);
        PersonalInfo.setLastName(newLastName);
        PersonalInfo.setPhone(newPhone);
        PersonalInfo.setEmail(newEmail);

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
            firstNameTextField.setText(PersonalInfo.getFirstName());
            lastNameTextField.setText(PersonalInfo.getLastName());
            phoneTextField.setText(PersonalInfo.getPhone());
            emailTextField.setText(PersonalInfo.getEmail());
            new AlertDialog.Builder(this)
                    .setTitle("Finished")
                    .setMessage("Information Saved")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            /**new addContactActivity(this).execute(newFirstName,
             newLastName, newPhone, newEmail);*/

        }
    }
}
