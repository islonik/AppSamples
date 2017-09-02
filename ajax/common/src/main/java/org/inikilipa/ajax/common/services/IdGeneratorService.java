package org.inikilipa.ajax.common.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class IdGeneratorService {

    private static final AtomicLong counter = new AtomicLong();

    public String getPrefixId(String prefix) {
        return Optional.ofNullable(prefix).orElse("Zero") + Long.valueOf(counter.incrementAndGet()).toString();
    }

}
