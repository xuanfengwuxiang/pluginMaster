package com.xuanfeng.pluginlib;

import android.content.pm.PackageInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;

import dalvik.system.DexClassLoader;

//插件的实体对象
public class PluginApk {

    public DexClassLoader mDexClassLoader;
    public Resources mResources;
    public PackageInfo mPackageInfo;//apk包信息
    public AssetManager mAssetManager;

    public PluginApk(DexClassLoader dexClassLoader, Resources resources, PackageInfo packageInfo) {
        mDexClassLoader = dexClassLoader;
        mResources = resources;
        mPackageInfo = packageInfo;
        mAssetManager = mResources.getAssets();
    }


}
