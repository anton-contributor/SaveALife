package com.savelife.mvc.dao.device;

import com.savelife.mvc.dao.AbstractDao;
import com.savelife.mvc.model.massaging.device.Device;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by anton on 15.08.16.
 */
@Repository("deviceDao")
public class DeviceDaoImpl extends AbstractDao<Long,Device> implements DeviceDao<Long,Device>{
    @Override
    public Device getDeviceByToken(String token) {

        Criteria c = createEntityCriteria();
        c.add(Restrictions.eq("token",token));
        return (Device) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Device> getAlldevices() {
        Criteria c = createEntityCriteria();
        return (List<Device>) c.list()  ;
    }

    @Override
    public void save(Device device) {
        persist(device);
    }

    @Override
    public void delete(Device device) {
        delete(device);
    }
}
