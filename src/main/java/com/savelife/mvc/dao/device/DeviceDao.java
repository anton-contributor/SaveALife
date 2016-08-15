package com.savelife.mvc.dao.device;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anton on 15.08.16.
 */
public interface DeviceDao<ID extends Serializable, T>{

    T getDeviceByToken(String token);

    List<T> getAlldevices();

    void save(T device);

    void delete(T device);
}
