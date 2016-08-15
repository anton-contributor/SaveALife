package com.savelife.mvc.model.massaging;

/**
 * @Massage
 * POJO massage for sanding to cloud massaging service
 */
public class Massage {

    /*
    * device token
    * */
    private String to;

    /*
    * massage content
    * */
    private Data Data;

    public Massage() {

    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Data getData() {
        return Data;
    }

    public void setData(Data Data) {
        this.Data = Data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Massage massage = (Massage) o;

        if (to != null ? !to.equals(massage.to) : massage.to != null) return false;
        return Data != null ? Data.equals(massage.Data) : massage.Data == null;

    }

    @Override
    public int hashCode() {
        int result = to != null ? to.hashCode() : 0;
        result = 31 * result + (Data != null ? Data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Massage{" +
                "to='" + to + '\'' +
                ", Data=" + Data +
                '}';
    }
}
