package com.neo.entity;

import java.util.Map;
import java.util.TreeMap;

public enum Type {
    ADMIN(0),
    NORMAL(2),
    SUPER(1),
    UNKNOWN(-99);


    private Integer code;

    Type(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    private static Map<Integer , Type> lookupMap = new TreeMap<>();
    static {
        for(Type t:Type.values()) {
            lookupMap.put(t.getCode(), t);
        }
    }

    static public Type fromCode(Integer code) {
        Type res = lookupMap.get(code);
        if (res==null) {
            return UNKNOWN;
        } else {
            return res;
        }
    }
}
