package info.idrishanafi.contact;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
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
public class fragmentMain extends Fragment implements View.OnClickListener {

    ImageButton addContactButton;
    ImageButton settingButton;
    ImageButton removeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_toolbar, container, false);
        addContactButton = (ImageButton) view.findViewById(R.id.addContactButton);
        settingButton = (ImageButton) view.findViewById(R.id.settingButton);
        addContactButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.addContactButton:
                //Toast.makeText(getActivity(), "You Clicked add Contact Button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddContactScreen.class);
                startActivity(intent);
                break;

            case R.id.settingButton:
                //Toast.makeText(getActivity(), "You Clicked Settings Button", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getActivity(), settingsScreen.class);
                startActivity(intent2);
                break;
        }
    }
}
