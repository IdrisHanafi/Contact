package info.idrishanafi.contact;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Idris on 5/8/16.
 */
public class viewContactActivity extends AsyncTask<String, Void, String[]> {

    private Context context;
    //public List<Contacts> myContacts = new ArrayList<Contacts>();
    public String echo, firstName, lastName, phone, email;

    public viewContactActivity(Context context) {
        this.context = context;
    }

    protected void onPreExecute(){

    }

    @Override
    protected String[] doInBackground(String... arg0) {

        try {
            String link = "http://50.112.144.161/viewContact.php";  //This is the IP/Domain name of the server with the PHP
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);

            //  This reads the data coming from the PHP and puts it into a single string
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line.toString());
            }
            this.echo = sb.toString();
            String result = sb.toString();
            Log.d("outcome", result);
            JSONArray json = new JSONArray(result);
            List<String> row = new ArrayList<String>();
            for(int i=0; i < json.length(); i++) {
                row.add(json.getString(i));
                Log.d("RESULTS", json.getString(i));
            }

            String[] rowValues = new String[row.size()];
            rowValues = row.toArray(rowValues);

            return rowValues;
        }
        catch(Exception e){
            return new String[0];
        }
    }

    @Override  // This method occurs after data from the PHP has been returned
    protected void onPostExecute(String[] result){
        int newLine = 0;
        /**if (result[0] == "error"){

            notification("Sorry", "No users match that query");
        }else{

        }*/
    }

    // Method for creating pop up notifications.
    protected void notification(String title, String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}