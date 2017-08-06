package io.noizwaves.batchplay.naivejdbc.etl.source;

public class GizmoRecord {
    private final int id;
    private final String creatorName;
    private final String creatorType;
    private final int cost;

    public GizmoRecord(int id, String creatorName, String creatorType, int cost) {
        this.id = id;
        this.creatorName = creatorName;
        this.creatorType = creatorType;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GizmoRecord that = (GizmoRecord) o;

        if (id != that.id) return false;
        if (cost != that.cost) return false;
        if (creatorName != null ? !creatorName.equals(that.creatorName) : that.creatorName != null) return false;
        return creatorType != null ? creatorType.equals(that.creatorType) : that.creatorType == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (creatorName != null ? creatorName.hashCode() : 0);
        result = 31 * result + (creatorType != null ? creatorType.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @Override
    public String toString() {
        return "GizmoRecord{" +
                "id=" + id +
                ", creatorName='" + creatorName + '\'' +
                ", creatorType='" + creatorType + '\'' +
                ", cost=" + cost +
                '}';
    }
}
