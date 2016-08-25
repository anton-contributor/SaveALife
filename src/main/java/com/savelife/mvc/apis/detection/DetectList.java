package com.savelife.mvc.apis.detection;

import com.savelife.mvc.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anton on 25.08.16.
 */
@Component
public class DetectList extends AbstractDetect implements Detection<Double, List<UserEntity>> {

    @Override
    public List<UserEntity> detect(Double radius, Double centerX, Double centerY, List<UserEntity> devices) {
        List<UserEntity> detected = new ArrayList<>();

        devices.forEach((v) -> {
            if (detection(radius, centerX, centerY, Double.parseDouble(v.getCurrentLatitude()), Double.parseDouble(v.getCurrentLongitude()))) {
                detected.add(v);
            }
        });

        return detected;
    }
}
