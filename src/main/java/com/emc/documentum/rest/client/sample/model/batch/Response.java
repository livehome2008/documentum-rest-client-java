/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.batch;

import java.util.List;


public interface Response {
    public int getStatus();
    public List<Header> getHeaders();
    public String getHeader(String header);
    public String getEntity();
}
