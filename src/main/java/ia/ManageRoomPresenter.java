package ia;

import abr.GymManager;
import ebr.Room;

import java.util.List;
import java.util.stream.Collectors;

public class ManageRoomPresenter implements Presenter {
    private GymManager gymManager;

    public ManageRoomPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    public List<String> getCurrentRooms() {
        return gymManager.getGym().getRooms().stream().map(r -> r.name).collect(Collectors.toList());
    }

    public boolean addRoom(String name) {
        for (Room r : gymManager.getGym().getRooms())
            if (r.name.equals(name))
                return false;

        gymManager.getGym().addRoom(new Room(name));
        return true;
    }

    public void removeRoom(String name) {
        for (Room r: gymManager.getGym().getRooms())
            if (r.name.equals(name)) {
                gymManager.getGym().removeRooms(r);
                return;
            }
    }
}
