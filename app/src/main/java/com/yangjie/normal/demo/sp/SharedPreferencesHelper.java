package com.yangjie.normal.demo.sp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.List;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/14 11:47 PM
 */
public class SharedPreferencesHelper {

    private static Application application;

    public SharedPreferencesHelper(Application application){
        this.application = application;
    }

    private static final String SP_NAME_USER = "sp_name_user";//用户相关的SP


    public static SharedPreferences getUserSp() {
        return application.getSharedPreferences(SP_NAME_USER, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getDefaultSp() {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }


    //sharedPreferences 是否为空
    public static boolean isEmpty(SharedPreferences sp) {
        return sp == null || sp.getAll() == null || 0 == sp.getAll().size();
    }

    /**
     * 默认值为""
     */
    public static String getString(SharedPreferences sp, String key) {
        return sp.getString(key, "");
    }

    /**
     * 具有默认值
     *
     * @param defValue 默认值
     */
    public static String getString(SharedPreferences sp, String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * 默认值为0L
     */
    public static long getLong(SharedPreferences sp, String key) {
        return sp.getLong(key, 0L);
    }

    /**
     * 默认值为0
     */
    public static int getInt(SharedPreferences sp, String key) {
        return sp.getInt(key, 0);
    }

    public static void setPreference(SharedPreferences sp, String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static void setPreference(SharedPreferences sp, String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static void setPreference(SharedPreferences sp, String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    //批量put数据
    public static void setPreferenceWithList(SharedPreferences sp, List<SpItem> spItemList) {
        if (sp == null || spItemList == null || spItemList.isEmpty()) {
            return;
        }
        SharedPreferences.Editor spEditor = sp.edit();
        for (SpItem item : spItemList) {
            setSpItem(spEditor, item);
        }
        spEditor.apply();
    }

    public static void setPreference(SharedPreferences sp, String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    /**
     * 默认值为false
     */
    public static boolean getBoolean(SharedPreferences sp, String key) {
        return sp.getBoolean(key, false);
    }


    /**
     * 自定义默认值
     */
    public static boolean getBoolean(SharedPreferences sp, String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }


    /**
     * 清除某一个key
     */
    public static void remove(SharedPreferences sp, String key) {
        if (sp == null || TextUtils.isEmpty(key)) {
            return;
        }
        sp.edit().remove(key).apply();
    }

    //值类型（String：0，int：1，long：2，float：3，boolean：4）
    private static final int VALUE_TYPE_STRING = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_LONG = 2;
    private static final int VALUE_TYPE_FLOAT = 3;
    private static final int VALUE_TYPE_BOOLEAN = 4;

    //Sp提交多个时使用，用于描述每个提交项
    public static class SpItem<T> {
        private String mKey;
        private T mValue;
        private int mValueType;

        public SpItem(String key, T value) {
            this.mKey = key;
            this.mValue = value;
            this.mValueType = initValueType(value);
        }

        private int initValueType(T t) {
            int typ = -1;
            if (t instanceof String) {
                typ = VALUE_TYPE_STRING;
            } else if (t instanceof Integer) {
                typ = VALUE_TYPE_INT;
            } else if (t instanceof Long) {
                typ = VALUE_TYPE_LONG;
            } else if (t instanceof Float) {
                typ = VALUE_TYPE_FLOAT;
            } else if (t instanceof Boolean) {
                typ = VALUE_TYPE_BOOLEAN;
            }
            return typ;
        }
    }

    //按类型put数据
    private static void setSpItem(SharedPreferences.Editor spEditor, SpItem spItem) {
        if (spItem == null || spEditor == null) {
            return;
        }
        switch (spItem.mValueType) {
            case VALUE_TYPE_STRING:
                spEditor.putString(spItem.mKey, (String) spItem.mValue);
                break;
            case VALUE_TYPE_INT:
                spEditor.putInt(spItem.mKey, (Integer) spItem.mValue);
                break;
            case VALUE_TYPE_LONG:
                spEditor.putLong(spItem.mKey, (Long) spItem.mValue);
                break;
            case VALUE_TYPE_FLOAT:
                spEditor.putFloat(spItem.mKey, (Float) spItem.mValue);
                break;
            case VALUE_TYPE_BOOLEAN:
                spEditor.putBoolean(spItem.mKey, (Boolean) spItem.mValue);
                break;
        }
    }
}
