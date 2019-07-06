package com.xuanfeng.pluginlib;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ProxyActivity extends Activity {

    private String mClassName;
    private PluginApk mPluginApk;
    private IPlugin mIPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClassName = getIntent().getStringExtra("className");
        mPluginApk = PluginManager.getInstance().getPluginApk();

        launchPluginActivity();
    }

    private void launchPluginActivity() {

        if (mPluginApk == null) {
            Log.i("===>", "loading apk file first please");
        }

        try {
            Class<?> clazz = mPluginApk.mDexClassLoader.loadClass(mClassName);
            Object object = clazz.newInstance();
            if (object instanceof IPlugin) {
                mIPlugin = (IPlugin) object;
                mIPlugin.attach(this);
                Bundle bundle = new Bundle();
                bundle.putInt("FROM", IPlugin.FROM_EXTERNAL);
                mIPlugin.onCreate(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Resources getResources() {
        return mPluginApk != null ? mPluginApk.mResources : super.getResources();
    }

    public AssetManager getAssets() {
        return mPluginApk != null ? mPluginApk.mAssetManager : super.getAssets();
    }

    public ClassLoader getClassLoader() {
        return mPluginApk != null ? mPluginApk.mDexClassLoader : super.getClassLoader();
    }

    @Override
    protected void onStart() {
        mIPlugin.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        mIPlugin.onResume();
        super.onResume();
        Log.e("ProxyActivity","onResume");

    }

    @Override
    protected void onPause() {
        mIPlugin.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mIPlugin.onStop();
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        mIPlugin.onDestroy();
        super.onDestroy();
        Log.e("ProxyActivity","onDestroy");
    }
}
