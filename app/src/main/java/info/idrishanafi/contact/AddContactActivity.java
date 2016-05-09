package info.idrishanafi.contact;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Idris on 5/6/16.
 */
public class addContactActivity extends AsyncTask<String, Void, Boolean> {

    private Context context;

    String firstName;
    String lastName;
    String phone;
    String email;
    String imageName;
    String image;

    private ProgressDialog dialog;
    private String echo;

    public addContactActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){
        dialog = new ProgressDialog(context);
        dialog.setMessage("Please wait...");
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... arg0) {
        try{
            this.firstName = (String)arg0[0];
            this.lastName = (String)arg0[1];
            this.phone = (String)arg0[2];
            this.email = (String)arg0[3];
            this.imageName = (String)arg0[4];
            this.image = (String)arg0[5];
            String link="http://50.112.144.161/addContact.php";  //This is the IP/Domain name of the server with the PHP
            String data  = URLEncoder.encode("firstName", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8");
            data  += "&" +URLEncoder.encode("lastName", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8");
            data  += "&" +URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
            data  += "&" +URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
            data  += "&" + URLEncoder.encode("imageName", "UTF-8") + "=" + URLEncoder.encode(imageName, "UTF-8");
            data  += "&" + URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(image, "UTF-8");
            Log.d("CONNECTING", "CONNECTING........");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            Log.d("CONNECTED", "SUCCESSFUL");

            //  This reads the data coming from the PHP and puts it into a single string
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                sb.append(line.toString());
            }
            this.echo = sb.toString();
            String result = sb.toString();
            Log.d("outcome", result);
            JSONObject json = new JSONObject(result);
            int success = json.getInt("success");
            if(success == 1) {
                return true;
            } else {
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    @Override  // This method occurs after data from the PHP has been returned
    protected void onPostExecute(final Boolean result){
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        if(result) {
            new AlertDialog.Builder(context)
                    .setTitle("All Set")
                    .setMessage("Added Contact Successfully")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(context, MainActivity.class)
                                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("Uh Oh")
                    .setMessage("There was an Error, User cannot be added!")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        super.onPostExecute(result);
    }
}
