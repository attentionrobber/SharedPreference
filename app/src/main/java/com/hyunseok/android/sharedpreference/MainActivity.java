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

    // 내부저장소 절대경로 가져오기
    String internalStoragePath;
    final String propertyFiles = "test.properties";

    EditText et_email;
    Switch switch_shuffle;
    RelativeLayout layoutHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internalStoragePath = getFilesDir().getAbsolutePath();

        et_email = (EditText) findViewById(R.id.et_email);
        switch_shuffle = (Switch) findViewById(R.id.switch_shuffle);
        layoutHelp = (RelativeLayout) findViewById(R.id.layoutHelp);

        // firstOpen 체크가 되어있으면 도움말 레이아웃을 닫아준다.
        if("false".equals(getProperty("firstOpen"))) {
            layoutHelp.setVisibility(View.GONE);
        }

    }

    public void closeHelp(View view) {
        layoutHelp.setVisibility(View.GONE);
        saveProperty("firstOpen", "false");
    }

    public void saveSetting(View view) {

    }

    public void saveProperty(String key, String value) {
        Properties prop = new Properties();
        prop.put(key, value);

        try {
            // 앱의 내부저장소/files/test.properties 파일을 저장
            FileOutputStream fos = new FileOutputStream(internalStoragePath + "/" + propertyFiles);
            prop.store(fos, "comment"); // firstOpen == false
            fos.close(); // 저장 후 파일을 닫아준다.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = "";

        Properties prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(internalStoragePath + "/" + propertyFiles);
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        prop.list(System.out); // Property 목록 전체 나열하기
        value = prop.getProperty(key);

        return value;
    }
}
