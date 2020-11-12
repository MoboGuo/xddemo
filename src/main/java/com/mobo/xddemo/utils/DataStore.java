package com.mobo.xddemo.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Mobo
 * @create 2020-11-12-16:37
 */
public class DataStore extends TreeMap<String, Object> {
    public static final String COMMA = ",";
    private static final long serialVersionUID = 1L;
    ArrayList<String> multi = new ArrayList();
    JSONObject varJson = new JSONObject();
    JSONObject toJson = new JSONObject();
    private int idx = 0;

    public DataStore() {
    }

    public void addWithComma(String key, String value) {
        if (!StringUtils.isBlank(key)) {
            if (this.containsKey(key)) {
                String item = "," + value;
                this.put(key, this.get(key) + item);
            } else {
                this.put(key, value);
            }

        }
    }

    public void addWithBracket(String key, String left, String right) {
        this.addWithComma(key, left + "<" + right + ">");
    }

    public void addWithJson(String key, String jKey, String jValue) {
        if (!StringUtils.isBlank(key)) {
            JSONObject json = null;
            if (this.containsKey(key)) {
                Object val = this.get(key);
                json = JSONObject.fromObject(val);
            } else {
                json = new JSONObject();
            }

            if (json != null) {
                json.put(jKey, jValue);
                this.put(key, json.toString());
            }

        }
    }

    public JSONObject getVarJson(String key1, String val1, String key2, String val2) {
        this.varJson.put(key1, val1);
        this.varJson.put(key2, val2);
        return this.varJson;
    }

    public void setVarJson(JSONObject json) {
        this.varJson = json;
    }

    public void addMulti(String Vkey, String tokey, String toval, String mkey) {
        this.toJson.put(Vkey, this.varJson);
        this.toJson.put(tokey, toval);
        this.multi.add(this.toJson.toString());
        if (!this.multi.isEmpty()) {
            this.put(mkey, this.multi.toString());
        }

    }

    public void addWithIncrease(String key, Object value) {
        this.put(key + "[" + this.idx++ + "]", value);
    }
}