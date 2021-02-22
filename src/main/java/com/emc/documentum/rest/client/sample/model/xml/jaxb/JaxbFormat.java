/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

import com.emc.documentum.rest.client.sample.model.RestObject;

@XmlRootElement(name="format")
public class JaxbFormat extends JaxbObject {
    public JaxbFormat() {
        super();
    }

    public JaxbFormat(RestObject object) {
        super(object);
    }

    @Override
    public String getName() {
        return "format";
    }
}
