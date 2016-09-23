package com.savelife.mvc.apis.detection;

import com.google.firebase.internal.Log;
import com.savelife.mvc.model.user.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by anton on 25.08.16.
 */
@Component
public class DetectList extends AbstractDetect implements Detection<Double, List<UserEntity>> {

    private static Logger logger = Logger.getLogger(DetectList.class.getName());

    @Override
    public List<UserEntity> detect(Double radius, Double centerX, Double centerY, List<UserEntity> devices) {
        logger.info("Detecting");
        List<UserEntity> detected = new ArrayList<>();

        devices.forEach((v) -> {
            if (Objects.nonNull(v.getCurrentLatitude()) && Objects.nonNull(v.getCurrentLongitude())) {
                if (detection(radius, centerX, centerY, v.getCurrentLatitude(), v.getCurrentLongitude())) {
                    logger.info("Detected " + v);
                    detected.add(v);
                }
            }
        });
        return detected;
    }
}
