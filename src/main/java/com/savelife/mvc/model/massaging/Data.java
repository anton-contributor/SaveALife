package com.savelife.mvc.model.massaging;

/**
 * Nested object data of the @Massage
 * consisting of the different options to be sent as a massage body
 */
public class Data {

    /*
    * nested option displaying a massage body
    * */
    private String massage;

    public Data() {
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data Data = (Data) o;

        return massage != null ? massage.equals(Data.massage) : Data.massage == null;

    }

    @Override
    public int hashCode() {
        return massage != null ? massage.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Data{" +
                "massage='" + massage + '\'' +
                '}';
    }
}
