package io.noizwaves.batchplay.naivejdbc.etl.destination;

public class GadgetRecord {
    private final String name;
    private final String price;

    public GadgetRecord(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GadgetRecord that = (GadgetRecord) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GadgetRecord{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
