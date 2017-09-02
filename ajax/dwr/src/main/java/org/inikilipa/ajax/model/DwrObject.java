package org.inikilipa.ajax.model;

import java.util.UUID;

/**
 * Created by nikilipa on 7/18/17.
 */
public class DwrObject {

    private String id;
    private String prefix;

    public DwrObject(String prefix) {
        this.id = UUID.randomUUID().toString();
        this.prefix = prefix;
    }

    public String getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public String toString() {
        return "DwrObject{" +
                "id='" + id + '\'' +
                ", prefix='" + prefix + '\'' +
                '}';
    }
}
