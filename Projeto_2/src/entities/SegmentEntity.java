package entities;

import java.util.Objects;

public class SegmentEntity {
    public int id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SegmentEntity segmentEntity = (SegmentEntity) o;
        return id == segmentEntity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
