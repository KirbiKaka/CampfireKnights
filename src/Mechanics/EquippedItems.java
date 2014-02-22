package Mechanics;

import java.util.HashMap;


public class EquippedItems {
    public static final HashMap<String, Equip> OPTIMIST_EQUIPS =
            new HashMap<String, Equip>();
    public static final HashMap<String, Equip> RATIONAL_EQUIPS =
            new HashMap<String, Equip>();
    public static final HashMap<String, Equip> APATHETIC_EQUIPS =
            new HashMap<String, Equip>();
    public static final HashMap<String, Equip> CYNIC_EQUIPS =
            new HashMap<String, Equip>();

    public static void setOptimistEquip(Equip equip) {
        if (OPTIMIST_EQUIPS.containsKey(equip.getType()))
            OPTIMIST_EQUIPS.remove(equip.getType());
        if (equip != null)
            OPTIMIST_EQUIPS.put(equip.getType(), equip);
    }

    public static void setRationalEquip(Equip equip) {
        if (RATIONAL_EQUIPS.containsKey(equip.getType()))
            RATIONAL_EQUIPS.remove(equip.getType());
        if (equip != null)
            RATIONAL_EQUIPS.put(equip.getType(), equip);
    }

    public static void setApatheticEquip(Equip equip) {
        if (APATHETIC_EQUIPS.containsKey(equip.getType()))
            APATHETIC_EQUIPS.remove(equip.getType());
        if (equip != null)
            APATHETIC_EQUIPS.put(equip.getType(), equip);
    }

    public static void setCynicEquip(Equip equip) {
        if (CYNIC_EQUIPS.containsKey(equip.getType()))
            CYNIC_EQUIPS.remove(equip.getType());
        if (equip != null)
            CYNIC_EQUIPS.put(equip.getType(), equip);
    }

    public static void setEquip(int id, Equip equip) {
        switch (id) {
        case 0:
            setOptimistEquip(equip);
            break;
        case 1:
            setRationalEquip(equip);
            break;
        case 2:
            setApatheticEquip(equip);
            break;
        case 3:
            setCynicEquip(equip);
            break;
        }
    }

    public static Equip getOptimistEquip(String type) {
        return OPTIMIST_EQUIPS.get(type);
    }

    public static Equip getRationalEquip(String type) {
        return RATIONAL_EQUIPS.get(type);
    }

    public static Equip getApatheticEquip(String type) {
        return APATHETIC_EQUIPS.get(type);
    }

    public static Equip getCynicEquip(String type) {
        return CYNIC_EQUIPS.get(type);
    }

    public static Equip getEquip(int id, String type) {
        switch (id) {
        case 0:
            return getOptimistEquip(type);
        case 1:
            return getRationalEquip(type);
        case 2:
            return getApatheticEquip(type);
        case 3:
            return getCynicEquip(type);
        }
        throw new IllegalArgumentException("ID not 0 - 3");
    }
}
