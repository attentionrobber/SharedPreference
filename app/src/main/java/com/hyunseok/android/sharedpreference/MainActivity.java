package com.hyunseok.android.sharedpreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    EditText et_email;
    Switch switch_shuffle;
    RelativeLayout layoutHelp;

    PropertyUtil propertyUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertyUtil = PropertyUtil.getInstance(this);

        et_email = (EditText) findViewById(R.id.et_email);
        switch_shuffle = (Switch) findViewById(R.id.switch_shuffle);
        layoutHelp = (RelativeLayout) findViewById(R.id.layoutHelp);

        // firstOpen 체크가 되어있으면 도움말 레이아웃을 닫아준다.
        if("false".equals(propertyUtil.getProperty("firstOpen"))) {
            layoutHelp.setVisibility(View.GONE);
        }
    }

    public void closeHelp(View view) {
        layoutHelp.setVisibility(View.GONE);
        propertyUtil.saveProperty("firstOpen", "false");
    }

    public void saveSetting(View view) {

    }

}
