package com.savelife.mvc.model.messaging.server;

/**
 * @Massage POJO massage for sanding token cloud massaging service
 */
public class ServerMessage {

    /*
    * device token
    * */
    private String to;

    /*
    * massage content
    * */
    private Data data;

    public ServerMessage() {

    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServerMessage serverMessage = (ServerMessage) o;

        if (to != null ? !to.equals(serverMessage.to) : serverMessage.to != null) return false;
        return data != null ? data.equals(serverMessage.data) : serverMessage.data == null;

    }

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
