package com.linkedin.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestService {

    private List<Guest> checkinList = new ArrayList<>(100);

    public static List<Guest> filterByFavoriteRoom(List<Guest> guests, Room room) {

        /*
         *  1. Returns a new collection that contains guests from the provided collection
         *  who have indicated the provided room as the first preference in their preferred
         *  room list.
         */

		/*My first attempt*/
//        List<Guest> favoriteRoom = new ArrayList<>();
//        for (Guest g : guests) {
//			if(! g.getPreferredRooms().isEmpty() && g.getPreferredRooms().get(0).equals(room)){
//				favoriteRoom.add(g);
//			}
//
//        }
//        return favoriteRoom;

		/*Example of stream*/

		return guests.stream().filter(g-> g.getPreferredRooms().indexOf(room) == 0).collect(Collectors.toList());

    }

    public void checkIn(Guest guest) {

        /*
         *  2. Adds a guest to the checkinList, placing members of the loyalty program
         *  ahead of those guests not in the program. Otherwise, guests are arranged in the
         *  order they were inserted.
         */

		if(!guest.isLoyaltyProgramMember() || checkinList.isEmpty()){
			checkinList.add(guest);
			return;
		} else {
			for (Guest g: checkinList) {
				if(g.isLoyaltyProgramMember()){
					continue;
				} else {
					checkinList.add(checkinList.indexOf(g), guest);
					return;
				}
			}
		}

    }

    public void swapPosition(Guest guest1, Guest guest2) {

        /*
         *  3.  Swaps the position of the two provided guests within the checkinList.
         *  If guests are not currently in the list no action is required.
         */
		if(!checkinList.contains(guest1) || !checkinList.contains(guest2)){
			return;
		}
		int g1Position = checkinList.indexOf(guest1);
		int g2Position = checkinList.indexOf(guest2);
		checkinList.set(g1Position, guest2);
		checkinList.set(g2Position, guest1);

    }

    public List<Guest> getCheckInList() {
        return List.copyOf(this.checkinList);
    }
}
