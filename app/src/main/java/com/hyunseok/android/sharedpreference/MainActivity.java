package com.hyunseok.android.sharedpreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    EditText et_email;
    Switch switch_shuffle;
    RelativeLayout relative2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email = (EditText) findViewById(R.id.et_email);
        switch_shuffle = (Switch) findViewById(R.id.switch_shuffle);
        relative2 = (RelativeLayout) findViewById(R.id.relative2);
    }

    public void closeHelp(View view) {
        relative2.setVisibility(View.GONE);

    }

    public void saveSetting(View view) {

    }
}
