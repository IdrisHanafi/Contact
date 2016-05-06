package info.idrishanafi.contact;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Idris on 5/5/16.
 */
public class fragmentMain extends Fragment {

    ImageButton addContactButton;
    ImageButton settingButton;
    toolbarClickListener toolbarClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_toolbar, container, false);
        addContactButton = (ImageButton) view.findViewById(R.id.addContactButton);
        settingButton = (ImageButton) view.findViewById(R.id.settingButton);

        return view;
    }

    public void buttonClicked(View view) {
        if (view.getId() == R.id.addContactButton) {
            //Toast.makeText(fragmentMain.this, "You Clicked add Contact Button", Toast.LENGTH_SHORT).show();
            //Toast.makeText(, "Add Contact", Toast.LENGTH_SHORT).show();
            toolbarClickListener.buttonClicked("add");
        } else if (view.getId() == R.id.settingButton) {
            toolbarClickListener.buttonClicked("setting");
            //button2 action
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        toolbarClickListener = (toolbarClickListener) activity;
    }

    public interface toolbarClickListener {
        public void buttonClicked(String btn);
    }
}
