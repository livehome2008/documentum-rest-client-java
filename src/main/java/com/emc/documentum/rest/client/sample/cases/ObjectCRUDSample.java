/*
 * Copyright (c) 2016. EMC Corporation. All Rights Reserved.
 */
package com.emc.documentum.rest.client.sample.cases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.emc.documentum.rest.client.sample.client.annotation.RestServiceSample;
import com.emc.documentum.rest.client.sample.model.RestObject;
import com.emc.documentum.rest.client.sample.model.plain.PlainRestObject;

import static com.emc.documentum.rest.client.sample.client.util.Debug.print;
import static com.emc.documentum.rest.client.sample.client.util.Debug.printNewLine;
import static com.emc.documentum.rest.client.sample.client.util.Debug.printStep;

@RestServiceSample("Sysobject(s) CRUD")
public class ObjectCRUDSample extends Sample {
    public void crudSysobject() {
        RestObject tempCabinet = client.getCabinet("Temp");
        
        printStep("create an object without binary content under the Temp cabinet");
        RestObject newObjectWithoutContent = new PlainRestObject("object_name", "obj_without_content");
        RestObject createdObjectWithoutContent = client.createObject(tempCabinet, newObjectWithoutContent);
        printHttpStatus();
        print(createdObjectWithoutContent);
        printNewLine();
        
        printStep("update the object with a new name");
        RestObject updateObjectName = new PlainRestObject("object_name", "new_object_name");
        RestObject updatedObjectWithObjectName = client.update(createdObjectWithoutContent, updateObjectName);
        printHttpStatus();
        print(updatedObjectWithObjectName);
        printNewLine();
        
        printStep("delete the created object");
        client.delete(updatedObjectWithObjectName);
        printHttpStatus();
        printNewLine();
        
        printStep("create an object with repeating properties and specify dm_docment type under the Temp cabinet");
        Map<String, Object> newPropertiesMap = new HashMap<String, Object>();
        newPropertiesMap.put("r_object_type", "dm_document");
        newPropertiesMap.put("object_name", "obj_with_repeating_properties");
        newPropertiesMap.put("keywords", Arrays.asList("objects", "repeating", "properties"));
        RestObject newObjectWithRepeatingProperties = new PlainRestObject(newPropertiesMap);
        RestObject createdObjectWithRepeatingProperties = client.createObject(tempCabinet, newObjectWithRepeatingProperties);
        printHttpStatus();
        print(createdObjectWithRepeatingProperties, "r_object_id", "object_name", "r_object_type", "keywords");
        printNewLine();
        
        printStep("delete the created object");
        client.delete(createdObjectWithRepeatingProperties);
        printHttpStatus();
        printNewLine();

        printStep("create an object with specify object type in another place to override the r_object_type");
        RestObject newObjectWithOverwriteType = new PlainRestObject("dm_sysobject", newPropertiesMap);
        RestObject createdObjectWithOverwriteType = client.createObject(tempCabinet, newObjectWithOverwriteType);
        printHttpStatus();
        print(createdObjectWithOverwriteType);
        printNewLine();
        
        printStep("delete the created object");
        client.delete(createdObjectWithOverwriteType);
        printHttpStatus();
        printNewLine();

        printStep("create an object with binary content under the Temp cabinet");
        RestObject newObjectWithContent = new PlainRestObject("object_name", "obj_with_content");
        RestObject createdObjectWithContent = client.createObject(tempCabinet, newObjectWithContent, "I'm the content of the object", "text/plain", "format", "crtext");
        printHttpStatus();
        print(createdObjectWithContent);
        printNewLine();
        
        printStep("delete the created object");
        client.delete(createdObjectWithContent);
        printHttpStatus();        
        printNewLine();
    }
}
