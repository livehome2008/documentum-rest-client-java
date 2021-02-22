/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.xml.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.ValueAssistant;

@XmlRootElement(name = "assist-values")
public class JaxbValueAssistance extends JaxbDmLinkableBase implements ValueAssistant {
    
    private List<Attribute> properties;
    private ValueAssistantProperties valueAssistantProperties;

    @Override
    @XmlTransient
    public List<Attribute> getProperties() {
        if(properties == null) {
            properties = new ArrayList<Attribute>();
            if(valueAssistantProperties != null && valueAssistantProperties.getElements() != null) {
                for(Element e : valueAssistantProperties.getElements()) {
                    JaxbAssistantAttribute a = new JaxbAssistantAttribute(e.getLocalName());
                    if(e.hasAttribute("allow-user-values")) {
                        a.allowUserValues(Boolean.parseBoolean(e.getAttribute("allow-user-values")));
                    }
                    NodeList values = e.getElementsByTagName("value");
                    for(int i=0;i<values.getLength();++i) {
                        Node node = values.item(i);
                        a.addValue(new JaxbValue(node.getTextContent(), node.getAttributes().getNamedItem("label").getTextContent()));
                    }
                    properties.add(a);
                }
            }
        }
        return properties;
    }

    @XmlElement(name="properties")
    protected ValueAssistantProperties getValueAssistantProperties() {
        return valueAssistantProperties;
    }

    protected void setValueAssistantProperties(ValueAssistantProperties valueAssistantProperties) {
        this.valueAssistantProperties = valueAssistantProperties;
    }

    @XmlRootElement(name = "properties")
    static class ValueAssistantProperties {
        private List<Element> elements;
        
        @XmlAnyElement
        public List<Element> getElements() {
            return elements;
        }

        public void setElements(List<Element> elements) {
            this.elements = elements;
        }
    }
    
    private static class JaxbAssistantAttribute implements Attribute {
        private final String name;
        private boolean allowUserValues;
        private List<Value> values = new ArrayList<>();

        JaxbAssistantAttribute(String name) {
            this.name = name;
        }
        
        @Override
        public String name() {
            return name;
        }
        
        void allowUserValues(boolean allowUserValues) {
            this.allowUserValues = allowUserValues;
        }
        
        @Override
        public boolean allowUserValues() {
            return allowUserValues;
        }
        
        @Override
        public List<Value> values() {
            return values;
        }
        
        private void addValue(Value value) {
            values.add(value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, allowUserValues, values);
        }

        @Override
        public boolean equals(Object obj) {
            JaxbAssistantAttribute that = (JaxbAssistantAttribute)obj;
            return Equals.equal(name, that.name)
                && Equals.equal(allowUserValues, that.allowUserValues)
                && Equals.equal(values, that.values);
        }
    }
    
    private static class JaxbValue implements Value {
        private final String value;
        private final String label;
        
        public JaxbValue(String value, String label) {
            this.value = value;
            this.label = label;
        }
        
        @Override
        public String value() {
            return value;
        }
        
        @Override
        public String label() {
            return label;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, label);
        }

        @Override
        public boolean equals(Object obj) {
            JaxbValue that = (JaxbValue)obj;
            return Equals.equal(value, that.value)
                && Equals.equal(label, that.label);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        JaxbValueAssistance that = (JaxbValueAssistance) obj;
        return Equals.equal(properties, that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
