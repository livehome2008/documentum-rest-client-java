/*
 * Copyright (c) 2018. Open Text Corporation. All Rights Reserved.
 */

package com.emc.documentum.rest.client.sample.model;

public interface Permission extends Linkable {
    public String getAccessor();
    public String getBasicPermission();
    public String getExtendPermissions();
    public String getApplicationPermission();
}
