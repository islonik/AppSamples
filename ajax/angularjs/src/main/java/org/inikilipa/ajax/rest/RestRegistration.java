package org.inikilipa.ajax.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikilipa on 7/21/17.
 */
@ApplicationPath("")
public class RestRegistration extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(AjaxResource.class);
        return classes;
    }

}
