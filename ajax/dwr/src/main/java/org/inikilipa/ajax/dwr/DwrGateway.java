package org.inikilipa.ajax.dwr;

import org.inikilipa.ajax.common.services.IdGeneratorService;
import org.inikilipa.ajax.model.DwrObject;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by nikilipa on 7/11/17.
 */
@ApplicationScoped
public class DwrGateway {

    private IdGeneratorService idGeneratorService = new IdGeneratorService(); // @Inject doesn't work. Rnd?

    public String getPrefixId(String prefix) {
        return idGeneratorService.getPrefixId(prefix);
    }

    public DwrObject getDataObject(String prefix) {
        return new DwrObject(prefix);
    }

}
