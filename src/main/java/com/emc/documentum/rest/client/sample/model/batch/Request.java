/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model.batch;

import java.util.List;


public interface Request {
    public String getMethod();
    public String getUri();
    public List<Header> getHeaders();
    public String getEntity();

    /**
     * @deprecated since 7.3
     */
    @Deprecated
    public Attachment getAttachment();
    
    /*
     * since 7.3
     */
    public List<Attachment> getAttachments();
}
