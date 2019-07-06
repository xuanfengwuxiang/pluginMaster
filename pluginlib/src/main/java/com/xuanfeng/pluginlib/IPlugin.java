package com.xuanfeng.pluginlib;

import android.app.Activity;
import android.os.Bundle;

//生命周期约束
public interface IPlugin {
    int FROM_INTERNAL = 0;
    int FROM_EXTERNAL = 1;

    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstance);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

}
