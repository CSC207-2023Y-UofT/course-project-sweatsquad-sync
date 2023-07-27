package ebr;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {
    public final String name;

    public Room(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
