package pids.service;

import pids.core.Data;
import pids.data.DataConverter;
import pids.data.PerimeterData;
import pids.model.OMapProperties;
public abstract class AbstractService implements Service {
    protected abstract PerimeterData getData() throws Exception;
    @Override
    public final Data get() throws Exception {
        OMapProperties prop = new OMapProperties();
        prop.image(getImageService().getURL(), getImageService().getStream());
        return DataConverter.convertFrom(getData(), prop);
    }
}