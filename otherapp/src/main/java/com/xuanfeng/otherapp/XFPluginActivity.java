package com.xuanfeng.otherapp;

import android.os.Bundle;
import android.util.Log;

import com.xuanfeng.pluginlib.PluginActivity;

public class XFPluginActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("XFPluginActivity","onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("XFPluginActivity","onDestroy");
    }
}
