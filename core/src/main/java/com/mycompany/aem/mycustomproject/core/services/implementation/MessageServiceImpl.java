package com.mycompany.aem.mycustomproject.core.services.implementation;


import com.mycompany.aem.mycustomproject.core.configs.MessageConfig;
import com.mycompany.aem.mycustomproject.core.services.MessageService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = MessageService.class, immediate = true)
@Designate(ocd = MessageServiceImpl.ServiceConfig.class)
public class MessageServiceImpl implements MessageService {

    @ObjectClassDefinition(name="My new - OSGi Configuration",
            description = "OSGi Configuration demo.")
    public @interface ServiceConfig {

        @AttributeDefinition(
                name = "Service Name",
                description = "Enter service name.",
                type = AttributeType.STRING)
        public String message() default "My New Osgi service";
    }

    private String message;

    @Activate
    protected void activate(MessageConfig config) {
        this.message = config.displayMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}