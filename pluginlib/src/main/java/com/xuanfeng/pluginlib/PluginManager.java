package com.xuanfeng.pluginlib;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

//
public class PluginManager {

    private Context mContext;
    private static PluginManager instance = null;
    private PluginApk mPluginApk;


    public static PluginManager getInstance() {
        if (instance == null) {
            instance = new PluginManager();
        }
        return instance;
    }


    public void init(Context context) {
        mContext = context.getApplicationContext();
    }

    //加载apk文件
    public void loadApk(String apkPath) {
        PackageInfo packageInfo = mContext.getPackageManager().getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES | PackageManager.GET_SERVICES);
        if (packageInfo == null) {
            return;
        }
        DexClassLoader dexClassLoader = createDexClassLoader(apkPath);
        AssetManager assetManager = createAssetManager(apkPath);
        Resources resources = createResource(assetManager);
        mPluginApk = new PluginApk(dexClassLoader, resources, packageInfo);
    }

    public PluginApk getPluginApk() {
        return mPluginApk;
    }

    private Resources createResource(AssetManager am) {
        Resources resources = mContext.getResources();
        return new Resources(am, resources.getDisplayMetrics(), resources.getConfiguration());
    }

    private AssetManager createAssetManager(String apkPath) {
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            method.invoke(assetManager, apkPath);
            return assetManager;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DexClassLoader createDexClassLoader(String apkPath) {
        File file = mContext.getDir("dex", Context.MODE_PRIVATE);
        return new DexClassLoader(apkPath, file.getAbsolutePath(), null, mContext.getClassLoader());
    }
}
