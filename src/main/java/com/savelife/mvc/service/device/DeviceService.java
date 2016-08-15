package com.savelife.mvc.service.device;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anton on 15.08.16.
 */
public interface DeviceService<ID extends Serializable, T> {

    List<T> getAll();

    T getByToken(String token);

    void save(T device);

    void update(T newDevice);

    void delete(T device);

    boolean exist(String token);
}
