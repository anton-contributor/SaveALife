package com.savelife.mvc.model.messaging.server;

import java.util.Map;

/**
 * @Massage POJO massage for sanding token cloud massaging service
 */
@lombok.Data
public class ServerMessage {

    /*
    * device token
    * */
    private String to;

    /*
    * massage content
    * */
    private Map<String, String> data;


    @Override
    public int hashCode() {
        int result = to != null ? to.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServerMessage{");
        sb.append("to='").append(to).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
