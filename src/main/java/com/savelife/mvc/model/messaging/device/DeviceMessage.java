package com.savelife.mvc.model.messaging.device;

import com.savelife.mvc.model.user.UserEntity;
import lombok.Data;

import java.util.Objects;
/**
 * Created by anton on 18.08.16.
 */
@Data public class DeviceMessage {


    private String email;
    private String password;
    private String phoneNumber;
    private String currentToken;
    /*
    * old token if exists, to clean connection to device
    * */
    private String oldToken;
    private Double currentLat;
    private Double currentLon;
    private Double destinationLat;
    private Double destinationLon;
    private String role;
    /*
    * help me message
    * */
    private String message;
    /*
    * check ambulance enabling(still in route or completed)
    * */
    private boolean enable = true;

    @Override
    public int hashCode() {
        int result = currentToken != null ? currentToken.hashCode() : 0;
        result = 31 * result + (oldToken != null ? oldToken.hashCode() : 0);
        result = 31 * result + (currentLat != null ? currentLat.hashCode() : 0);
        result = 31 * result + (currentLon != null ? currentLon.hashCode() : 0);
        result = 31 * result + (destinationLat != null ? destinationLat.hashCode() : 0);
        result = 31 * result + (destinationLon != null ? destinationLon.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (enable ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeviceMessage{");
        sb.append("currentToken='").append(currentToken).append('\'');
        sb.append(", oldToken='").append(oldToken).append('\'');
        sb.append(", currentLat=").append(currentLat);
        sb.append(", currentLon=").append(currentLon);
        sb.append(", destinationLat=").append(destinationLat);
        sb.append(", destinationLon=").append(destinationLon);
        sb.append(", role='").append(role).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", enable=").append(enable);
        sb.append('}');
        return sb.toString();
    }

    public UserEntity setUserFieldsFromDeviceMessage(UserEntity user) {
        if (Objects.nonNull(this.currentLat))
            user.setCurrentLatitude(this.currentLat);

        if (Objects.nonNull(this.currentLon))
            user.setCurrentLongitude(this.currentLon);

        if (Objects.nonNull(this.destinationLat))
            user.setDestinationLatitude(this.destinationLat);

        if (Objects.nonNull(this.destinationLon))
            user.setDestinationLongitude(this.destinationLon);

        if (Objects.nonNull(this.enable))
            user.setEnable(this.enable);

        if (Objects.nonNull(this.currentToken))
            user.setToken(this.currentToken);

        if (Objects.nonNull(this.email))
            user.setEmail(this.email);

        if (Objects.nonNull(this.password))
            user.setPassword(this.password);

        if(Objects.nonNull(this.phoneNumber))
            user.setPhoneNumber(this.phoneNumber);

        return user;
    }
}
