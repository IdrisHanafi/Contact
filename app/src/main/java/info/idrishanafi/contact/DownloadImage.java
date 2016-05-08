package info.idrishanafi.contact;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Idris on 5/8/16.
 */
public class DownloadImage extends AsyncTask<Void, Void, Bitmap> {
    String fileName;
    ImageView downloadedImage;

    public DownloadImage(String fileName, ImageView downloadedImage) {
        this.fileName = fileName;
        this.downloadedImage = downloadedImage;
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        String url = "http://50.112.144.161/" + "pictures/" + fileName + ".JPG";
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(1000 * 30);
            connection.setReadTimeout(1000 * 30);
            return BitmapFactory.decodeStream((InputStream) connection.getContent(), null, null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap != null) {
            downloadedImage.setImageBitmap(bitmap); // Set ImageView with the bitmap
        }
    }
}
