package ebr;

import java.util.Objects;

public class Room {
    static private int count = 0;
    public final String name;
    private int id;

    public Room(String name) {
        this.id = count++;
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
        return id;
    }
}
