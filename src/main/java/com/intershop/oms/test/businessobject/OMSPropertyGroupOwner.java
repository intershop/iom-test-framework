package com.intershop.oms.test.businessobject;

import java.util.HashMap;
import java.util.Map;

public interface OMSPropertyGroupOwner {

    Map<String, Map<String, String>> getPropertyGroups();

    default void addProperty(String group, String key, String value) {
        getPropertyGroups().computeIfAbsent(group, k -> new HashMap<>()).put(key, value);
    }

    default String getProperyValue(String group, String key) {
        Map<String, String> grp = getPropertyGroups().get(group);
        return grp == null ? null : grp.get(key);
    }
}