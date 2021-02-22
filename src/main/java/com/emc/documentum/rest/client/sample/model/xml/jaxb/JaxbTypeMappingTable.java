/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.RestTypeMappingTable;

@XmlRootElement(name = "mapping-table")
public class JaxbTypeMappingTable implements RestTypeMappingTable {
    private String value;
    private String display;
    private String description;

    public void setValue(String value) {
        this.value = value;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDisplay() {
        return display;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean equals(Object obj) {
        JaxbTypeMappingTable that = (JaxbTypeMappingTable)obj;
        return Equals.equal(value, that.value) 
            && Equals.equal(display, that.display)
            && Equals.equal(description, that.description);
    }
}