package info.idrishanafi.contact;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddContactScreen extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULT_LOAD_IMAGE = 1;

    EditText firstNameTextField, lastNameTextField, phoneTextField, emailTextField;
    Button addContactButton;
    TextView addPicture;
    ImageView addImage;
    String imageName = "defaultpicture";

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
        addPicture = (TextView) findViewById(R.id.addPicture);
        addImage = (ImageView) findViewById(R.id.addImage);

        addPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddContactScreen.this, "Adding Picture", Toast.LENGTH_SHORT).show();
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        addContactButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String path = selectedImage.getPath();
            imageName = path.substring(path.lastIndexOf("/")+1);
            Log.d("PATH", path);
            Log.d("filename", imageName);
            //addImage.setImageURI(null);
            addImage.setImageURI(selectedImage);
        }
    }

    @Override
    public void onClick(View view) {
        String newFirstName = firstNameTextField.getText().toString();
        String newLastName = lastNameTextField.getText().toString();
        String newPhone = phoneTextField.getText().toString();
        String newEmail = emailTextField.getText().toString();
        Bitmap image = ((BitmapDrawable) addImage.getDrawable()).getBitmap();

        // encode image to String
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
        Log.d("encodedImage", encodedImage);

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
            new addContactActivity(this).execute(newFirstName,
                    newLastName, newPhone, newEmail, imageName, encodedImage);

        }
    }
}