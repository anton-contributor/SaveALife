package com.savelife.mvc.model.messaging.device;

import com.savelife.mvc.model.user.UserEntity;

import java.util.Objects;
/**
 * Created by anton on 18.08.16.
 */
public class DeviceMessage {
    /*
    * current token
    * */
    private String currentToken;

    /*
    * old token if exists, to clean connection to device
    * */
    private String oldToken;

    /*
    * current latitude
    * */
    private Double currentLat;

    /*
    * current longitude
    * */
    private Double currentLon;

    /*
    * destination latitude
    * */
    private Double destinationLat;

    /*
    * destination longitude
    * */
    private Double destinationLon;

    /*
    * device role
    * */
    private String role;

    /*
    * help me message
    * */
    private String message;

    /*
    * check ambulance enabling(still in route or completed)
    * */
    private boolean enable;

    public DeviceMessage() {
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }

    public String getOldToken() {
        return oldToken;
    }

    public void setOldToken(String oldToken) {
        this.oldToken = oldToken;
    }

    public Double getCurrentLat() {
        return currentLat;
    }

    public void setCurrentLat(Double currentLat) {
        this.currentLat = currentLat;
    }

    public Double getCurrentLon() {
        return currentLon;
    }

    public void setCurrentLon(Double currentLon) {
        this.currentLon = currentLon;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLon() {
        return destinationLon;
    }

    public void setDestinationLon(Double destinationLon) {
        this.destinationLon = destinationLon;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceMessage that = (DeviceMessage) o;

        if (enable != that.enable) return false;
        if (currentToken != null ? !currentToken.equals(that.currentToken) : that.currentToken != null) return false;
        if (oldToken != null ? !oldToken.equals(that.oldToken) : that.oldToken != null) return false;
        if (currentLat != null ? !currentLat.equals(that.currentLat) : that.currentLat != null) return false;
        if (currentLon != null ? !currentLon.equals(that.currentLon) : that.currentLon != null) return false;
        if (destinationLat != null ? !destinationLat.equals(that.destinationLat) : that.destinationLat != null)
            return false;
        if (destinationLon != null ? !destinationLon.equals(that.destinationLon) : that.destinationLon != null)
            return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;

    }

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

        return user;
    }
}
