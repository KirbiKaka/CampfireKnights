package Mechanics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Inventory {

    static HashMap<Equip, Integer> equipment =
            new HashMap<Equip, Integer>();

    public static EquipmentList getEquipmentList() {
        return new EquipmentList(equipment.keySet());
    }

    public static int getEquipmentAmount(Equip equip) {
        if (equipment.containsKey(equip)) {
            return equipment.get(equip);
        }
        return 0;
    }

    public static void addEquipment(Equip equip) {
        if (!equipment.containsKey(equip)) {
            equipment.put(equip, 0);
        }
        equipment.put(equip, equipment.get(equip) + 1);
    }

    public static void addEquipment(Equip equip, int amount) {
        if (!equipment.containsKey(equip)) {
            equipment.put(equip, 0);
        }
        equipment.put(equip, equipment.get(equip) + amount);
    }

    public static void removeEquipment(Equip equip) {
        if (!equipment.containsKey(equip)) {
            throw new IllegalArgumentException(
                    "Equipment doesn't exist to be removed.");
        }
        equipment.put(equip, equipment.get(equip) - 1);
        if (equipment.get(equip) == 0) {
            equipment.remove(equip);
        }
    }

    public static void removeEquipment(Equip equip, int amount) {
        if (!equipment.containsKey(equip)) {
            throw new IllegalArgumentException(
                    "Equipment doesn't exist to be removed.");
        }
        if (equipment.get(equip) < amount) {
            throw new IllegalArgumentException(
                    "Not enough of that equipment to be removed.");
        }
        equipment.put(equip, equipment.get(equip) - amount);
        if (equipment.get(equip) == 0) {
            equipment.remove(equip);
        }
    }

    /** Returns false if party members have equipped this item such
     * that there are none left to equip. */
    public static boolean isEquippable(Equip equip) {
        int equipped = 0;
        for (int i = 0; i < 4; i++) {
            if (EquippedItems.getEquip(i, equip.getType()).getName().equals(equip.getName()))
                equipped++;
        }
        if (equipment.get(equip) <= equipped)
            return false;
        return true;
    }

    public static class EquipmentList {

        TreeSet<Equip> equipment;

        public EquipmentList(Set<Equip> equips) {
            equipment = new TreeSet<Equip>(new Comparator<Equip>() {
                @Override
                public int compare(Equip e1, Equip e2) {
                    return e1.getName().compareTo(e2.getName());
                }
            });
            for (Equip e : equips) {
                equipment.add(e);
            }
        }

        public Iterator<Equip> iterator() {
            return equipment.iterator();
        }

        public int size() {
            return equipment.size();
        }

        public Equip get(int index) {
            Iterator<Equip> iter = equipment.iterator();
            for (int i = 0; i < index; i++) {
                if (iter.hasNext())
                    iter.next();
                else
                    return null;
            }
            if (iter.hasNext())
                return iter.next();
            return null;
        }

    }

}
