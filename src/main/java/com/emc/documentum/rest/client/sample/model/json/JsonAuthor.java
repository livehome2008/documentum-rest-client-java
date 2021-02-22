/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.json;

import java.util.Objects;

import com.emc.documentum.rest.client.sample.client.util.Equals;
import com.emc.documentum.rest.client.sample.model.Author;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonAuthor implements Author {
    @JsonProperty
    private String name;
    @JsonProperty
    private String uri;
    @JsonProperty
    private String email;
    @Override
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    @Override
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        JsonAuthor that = (JsonAuthor) obj;
        return Equals.equal(name, that.name) && Equals.equal(uri, that.uri)
                && Equals.equal(email, that.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, uri, email);
    }
}
