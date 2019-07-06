package com.xuanfeng.plugin;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xuanfeng.pluginlib.PluginManager;
import com.xuanfeng.pluginlib.ProxyActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.getInstance().init(this);

        File file = Environment.getExternalStorageDirectory();
        try {
            FileUtils.doCopy(this, "", file.getAbsolutePath());
            PluginManager.getInstance().loadApk(file.getAbsolutePath() + "/" + "aaa.apk");

        } catch (IOException e) {
            e.printStackTrace();
        }
        findViewById(R.id.tv_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProxyActivity.class);

                intent.putExtra("className", "com.xuanfeng.otherapp.XFPluginActivity");

                startActivity(intent);
            }
        });
    }
}
