package com.hyunseok.android.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
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

    public static String SHARED_FILE = "prop";

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

        loadSetting(); // 세팅된 값을 가져와 화면에 뿌린다.
    }

    public void closeHelp(View view) {
        layoutHelp.setVisibility(View.GONE);
        propertyUtil.saveProperty("firstOpen", "false");
    }

    public void saveSetting(View view) {
        // 1. Preference 생성하기
        SharedPreferences sharedPref = getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE); // SharedPreferences는 앱 단위로 저장이 된다.
        //SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE); // Activity 단위로 저장이된다(xml파일로)
        // 2. SharedPreference에 값을 입력하기 위해서는 에디터를 통해서만 가능
        SharedPreferences.Editor editor = sharedPref.edit();

        // editor.putInt("키", "값");
        editor.putString("email", et_email.getText().toString());
        editor.putBoolean("shuffle", switch_shuffle.isChecked());

        // 3. 입력된 값을 반영한다.
        editor.commit();
    }

    public void loadSetting() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_FILE, Context.MODE_PRIVATE);

        String email = sharedPref.getString("email", null); // null이면 default값이 들어간다.
        boolean shuffle = sharedPref.getBoolean("shuffle", false);

        et_email.setText(email);
        switch_shuffle.setChecked(shuffle);
    }
}
