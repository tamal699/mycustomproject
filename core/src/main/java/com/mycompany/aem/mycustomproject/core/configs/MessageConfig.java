package com.mycompany.aem.mycustomproject.core.configs;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "Message Configuration", description = "Configurable message for display")
public @interface MessageConfig {

    @AttributeDefinition(name = "Display Message", description = "Message to show on the page")
    String displayMessage() default "Welcome to Scenic Highlights!";
}