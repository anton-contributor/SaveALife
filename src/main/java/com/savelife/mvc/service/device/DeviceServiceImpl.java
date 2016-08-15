package com.savelife.mvc.service.device;

import com.savelife.mvc.dao.device.DeviceDao;
import com.savelife.mvc.model.massaging.device.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by anton on 15.08.16.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService<Long,Device>{

    @Autowired
    private DeviceDao dao;


    @Override
    public List<Device> getAll() {
        return dao.getAlldevices();
    }

    @Override
    public Device getByToken(String token) {
        return (Device) dao.getDeviceByToken(token);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(Device device) {
        dao.save(device);
    }

    @Override
    public void update(Device newDevice) {
        Device old = (Device) dao.getDeviceByToken(newDevice.getToken());
        if (old != null ){
            old.setToken(newDevice.getToken());
        }
    }

    @Override
    public void delete(Device device) {
        dao.delete(device.getId_device());
    }

    @Override
    public boolean exist(String token) {
        return getByToken(token) != null;
    }
}
