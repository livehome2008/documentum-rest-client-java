/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.model;

import java.util.List;

/**
 * the object which is linkable
 */
public interface Linkable {
    /**
     * @return all the links provided by the resource
     */
    List<Link> getLinks();
    
    /**
     * @param rel the link relation
     * @return specified link by relation
     */
    String getHref(LinkRelation rel);
    
    /**
     * whether has the link relation
     * @param rel the link relation
     * @return whether has the link relation
     */
    boolean hasHref(LinkRelation rel);
    
    /**
     * @param rel the link relation
     * @param title the link title
     * @return specified link by relation and title
     */
    String getHref(LinkRelation rel, String title);
    
    /**
     * @return self link
     */
    String self();
}
