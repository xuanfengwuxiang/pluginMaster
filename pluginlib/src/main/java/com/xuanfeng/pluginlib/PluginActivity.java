package com.xuanfeng.pluginlib;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PluginActivity extends AppCompatActivity implements IPlugin {

    private int mFrom = FROM_INTERNAL;
    private Activity mProxyActivity;

    @Override
    public void attach(Activity proxyActivity) {
        mProxyActivity = proxyActivity;
    }

    @Override
    public void onCreate(Bundle saveInstance) {
        if (saveInstance != null) {
            mFrom = saveInstance.getInt("FROM");
        }
        if (mFrom == FROM_INTERNAL) {
            super.onCreate(saveInstance);
            mProxyActivity = this;
        }
    }

    @Override
    public void setContentView(int layoutResId) {
        if (mFrom == FROM_INTERNAL) {
            super.setContentView(layoutResId);
        } else {
            mProxyActivity.setContentView(layoutResId);
        }
    }

    @Override
    public void onStart() {
        if (mFrom == FROM_INTERNAL) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (mFrom == FROM_INTERNAL) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (mFrom == FROM_INTERNAL) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (mFrom == FROM_INTERNAL) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (mFrom == FROM_INTERNAL) {
            super.onDestroy();
        }
    }
}
