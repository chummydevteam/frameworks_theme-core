/*
 * Copyright (C) 2017 SlimRoms Project
 * Copyright (C) 2017 Victor Lapin
 * Copyright (C) 2017 Griffin Millender
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package com.slimroms.themecore;

import android.support.annotation.Nullable;
import android.util.ArraySet;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;


public class ThemePrefs {

    private JSONObject mPrefs;
    private File mFile;

    public ThemePrefs(String path) {
        mFile = new File(path);
        if (!mFile.exists()) {
            if (!mFile.getParentFile().exists()) {
                try {
                    FileUtils.forceMkdirParent(mFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //if (!mFile.getParentFile().mkdirs()) {
                    //IOException e = new IOException("Unable to create directory - " + mFile.getParent());
                    //throw new RuntimeException(e);
                //}
            }
            try {
                FileUtils.write(mFile, "{}", Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String json = FileUtils.readFileToString(mFile, Charset.defaultCharset());
            mPrefs = new JSONObject(json);
        } catch (IOException|JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public String getString(String key, String defValue) {
        return mPrefs.optString(key, defValue);
    }

    @Nullable
    public Set<String> getStringSet(String s, Set<String> set) {
        Set<String> ret = new ArraySet<>();
        try {
            JSONArray jsonArray = mPrefs.getJSONArray(s);
            if (jsonArray == null || jsonArray.length() == 0) {
                return set;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                ret.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return set;
        }
        return ret;
    }

    public int getInt(String s, int i) {
        return mPrefs.optInt(s, i);
    }

    public long getLong(String s, long l) {
        return mPrefs.optLong(s, l);
    }

    public float getFloat(String s, float v) {
        String f = mPrefs.optString(s, String.valueOf(v));
        if (f != null && !f.isEmpty()) {
            return Float.valueOf(f);
        }
        return v;
    }

    public boolean getBoolean(String s, boolean b) {
        return mPrefs.optBoolean(s, b);
    }

    public boolean contains(String key) {
        return mPrefs.has(key);
    }

    public void putString(String s, String s1) {
        try {
            mPrefs.put(s, s1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putStringSet(String s, Set<String> set) {
        JSONArray jsonArray = new JSONArray();
        String[] temp = set.toArray(new String[0]);
        for (int i = 0; i < temp.length; i++) {
            try {
                jsonArray.put(i, temp[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            mPrefs.put(s, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putInt(String s, int i) {
        try {
            mPrefs.put(s, i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putLong(String s, long l) {
        try {
            mPrefs.put(s, l);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putFloat(String s, float v) {
        try {
            mPrefs.put(s, String.valueOf(v));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putBoolean(String s, boolean b) {
        try {
            mPrefs.put(s, b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void remove(String s) {
        mPrefs.remove(s);
    }

    public void clear() {
        String key;
        while ((key = mPrefs.keys().next()) != null && mPrefs.keys().hasNext()) {
            mPrefs.remove(key);
        }
    }
}