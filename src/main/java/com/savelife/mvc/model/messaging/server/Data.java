package com.savelife.mvc.model.messaging.server;

import com.savelife.mvc.model.routing.NodeEntity;

import java.util.List;

/**
 * Nested object data of the @ServerMassage
 * consisting of the different options to be sent as a massageBody body
 */
public class Data {

    /*
    * nested option displaying a massageBody body
    * */
    private String massageBody;

    private List<NodeEntity> path;

    public Data() {
    }

    public String getMassageBody() {
        return massageBody;
    }

    public void setMassageBody(String massageBody) {
        this.massageBody = massageBody;
    }

    public List<NodeEntity> getPath() {
        return path;
    }

    public void setPath(List<NodeEntity> path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (massageBody != null ? !massageBody.equals(data.massageBody) : data.massageBody != null) return false;
        return path != null ? path.equals(data.path) : data.path == null;

    }

    @Override
    public int hashCode() {
        int result = massageBody != null ? massageBody.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Data{");
        sb.append("massageBody='").append(massageBody).append('\'');
        sb.append(", path=").append(path);
        sb.append('}');
        return sb.toString();
    }
}
