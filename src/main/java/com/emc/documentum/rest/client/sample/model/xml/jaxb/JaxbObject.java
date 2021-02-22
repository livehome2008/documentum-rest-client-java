/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ObjectAspects;
import com.emc.documentum.rest.client.sample.model.ObjectLifecycle;
import com.emc.documentum.rest.client.sample.model.RestObject;
import com.emc.documentum.rest.client.sample.model.xml.XMLNamespace;

public abstract class JaxbObject extends JaxbDmLinkableBase implements RestObject {
    protected String type;
    protected String definition;
    protected Map<String, Object> properties;
    protected ObjectProperties objectProperties;
    protected String href;
    
    protected JaxbObjectAspects objectAspects;
    protected JaxbObjectLifecycle objectLifecycle;
    
    public JaxbObject() {
    }

    public JaxbObject(String href) {
        this.href = href;
    }
    
    public JaxbObject(RestObject object) {
        setType(object.getType());
        setDefinition(object.getDefinition());
        setProperties(object.getProperties());
        setHref(object.getHref());
        if(object.getObjectAspects() != null) {
            this.objectAspects = new JaxbObjectAspects(object.getObjectAspects());
        }
        if(object.getObjectLifecycle() != null) {
            this.objectLifecycle = new JaxbObjectLifecycle(object.getObjectLifecycle());
        }
    }
    
    @Override
    public String getObjectId() {
        return (String)getProperties().get("r_object_id");
    }
       
    @Override
    public String getObjectName() {
        return (String)getProperties().get("object_name");
    }
    
    @Override
    public String getObjectType() {
        return (String)getProperties().get("r_object_type");
    }

    @Override
    @XmlAttribute(namespace = XMLNamespace.SCHEMA_INSTANCE_NAMESPACE)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    @XmlAttribute
    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    @Override
    @XmlAttribute
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    @XmlTransient
    public Map<String, Object> getProperties() {
        if(properties == null) {
            properties = new HashMap<String, Object>();
            if(objectProperties != null && objectProperties.getElements() != null) {
                for(Element e : objectProperties.getElements()) {
                    NodeList nodeList = e.getElementsByTagName("item");
                    int n = nodeList.getLength();
                    if(n > 0) {
                        List<String> list = new ArrayList<String>(n);
                        for(int i=0;i<n;++i) {
                            list.add(nodeList.item(i).getTextContent());
                        }
                        properties.put(e.getLocalName(), list);
                    } else {
                        properties.put(e.getLocalName(), e.getTextContent());
                    }
                }
            }
        }
        return properties;
    }

    @SuppressWarnings("unchecked")
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
        if(properties != null) {
            objectProperties = new ObjectProperties();
            List<Element> list = new ArrayList<Element>(properties.size());
            for(Map.Entry<String, Object> entry : properties.entrySet()) {
                if(entry.getValue() instanceof List) {
                    list.add(new RepeatingPropertyElement(entry.getKey(), (List<String>)entry.getValue()));
                } else {
                    list.add(new SinglePropertyElement(entry.getKey(), (String)entry.getValue()));
                }
            }
            objectProperties.setElements(list);
        }
    }

    @XmlElement(name="properties")
    protected ObjectProperties getObjectProperties() {
        return objectProperties;
    }

    protected void setObjectProperties(ObjectProperties objectProperties) {
        this.objectProperties = objectProperties;
    }
    
    @Override
    public String getPropertiesType() {
        return objectProperties==null?null:objectProperties.getPropertiesType();
    }

    @XmlElement(type = JaxbObjectAspects.class, name = "object-aspects")
    public ObjectAspects getObjectAspects() {
        return objectAspects;
    }

    public void setObjectAspects(JaxbObjectAspects objectAspects) {
        this.objectAspects = objectAspects;
    }

    @XmlElement(type = JaxbObjectLifecycle.class, name = "object-lifecycle")
    public ObjectLifecycle getObjectLifecycle() {
        return objectLifecycle;
    }

    public void setObjectLifecycle(JaxbObjectLifecycle objectLifecycle) {
        this.objectLifecycle = objectLifecycle;
    }

    @XmlRootElement(name = "properties")
    static class ObjectProperties {
        private List<Element> elements;
        private String propertiesType;
        
        @XmlAnyElement
        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }
        
        @XmlAttribute(name="type", namespace=XMLNamespace.SCHEMA_INSTANCE_NAMESPACE)
        public String getPropertiesType() {
            return propertiesType;
        }

        public void setPropertiesType(String propertiesType) {
            this.propertiesType = propertiesType;
        }
        
        @Override
        public boolean equals(Object obj) {
            ObjectProperties that = (ObjectProperties) obj;
            return Equals.equal(propertiesType, that.propertiesType)
                    && Equals.equal(elements, that.elements);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        JaxbObject that = (JaxbObject) obj;
        return Equals.equal(type, that.type)
                && Equals.equal(definition, that.definition)
                && Equals.equal(getProperties(), that.getProperties());
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, definition, properties);
    }
}
