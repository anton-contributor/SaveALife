package com.savelife.mvc.model.storing;

import com.savelife.mvc.model.massaging.device.Device;

import java.util.HashMap;

/**
 * Created by anton on 15.08.16.
 */
public class DevicesContainer extends HashMap<String,Device> {

    public static class DevicesContainerHolder{

        public static final DevicesContainer holder_instance = new DevicesContainer();

    }

    public static DevicesContainer getInstance(){
       return DevicesContainerHolder.holder_instance;
    }
}
