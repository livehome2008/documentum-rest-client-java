/*
 * Copyright (c) 2016. EMC Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

import com.emc.documentum.rest.client.sample.model.RestObject;
import com.emc.documentum.rest.client.sample.model.xml.XMLNamespace;

@XmlRootElement(name="product-info", namespace=XMLNamespace.DM_NAMESPACE)
public class JaxbProductInfo extends JaxbObject {
    public JaxbProductInfo() {
        super();
    }

    public JaxbProductInfo(RestObject object) {
        super(object);
    }

    @Override
    public String getName() {
        return "product-info";
    }
}
