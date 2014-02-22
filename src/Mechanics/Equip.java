package Mechanics;

import java.util.Arrays;



public abstract class Equip {
    public static final String BODY = "body";
    public static final String HELMET = "helmet";
    public static final String LEGS = "legs";
    public static final String WEAPON = "weapon";
    public static final String ACCESSORY_1 = "accessory1";
    public static final String ACCESSORY_2 = "accessory2";
    static final String[] types =
        {BODY, HELMET, LEGS, WEAPON, ACCESSORY_1, ACCESSORY_2};

    private String name;
    private String type;
    private String ability;

    private int str_mod;
    private int int_mod;
    private int dex_mod;
    private int hp_mod;
    private int mp_mod;
    private String otherEffects;

    public Equip(String name, String type) {
        this.name = name;
        if (Arrays.binarySearch(types, type) != -1) {
            this.type = type;
        }
        ability = null;
    }

    public Equip(String name, String type, String ability) {
        this.name = name;
        if (Arrays.binarySearch(types, type) != -1) {
            this.type = type;
        }
        this.ability = ability;
    }

    /** @return the str modifier. */
    public int getStr_mod() { return str_mod; }

    /** Set str modifier. */
    public void setStr_mod(int str_mod) { this.str_mod = str_mod; }

    /** @return the int modifier. */
    public int getInt_mod() { return int_mod; }

    /** Set int modifier. */
    public void setInt_mod(int int_mod) { this.int_mod = int_mod; }

    /** @return the dex modifier. */
    public int getDex_mod() { return dex_mod; }

    /** Set dex modifier. */
    public void setDex_mod(int dex_mod) { this.dex_mod = dex_mod; }

    /** @return the hp modifier.*/
    public int getHp_mod() { return hp_mod; }

    /** Set hp modifier. */
    public void setHp_mod(int hp_mod) { this.hp_mod = hp_mod; }

    /** @return the mp modifier. */
    public int getMp_mod() { return mp_mod; }

    /** Set mp modifier. */
    public void setMp_mod(int mp_mod) { this.mp_mod = mp_mod; }

    /** @return the otherEffects. */
    public String getOtherEffects() { return otherEffects; }

    /** Set other effects. */
    public void setOtherEffects(String otherEffects) { this.otherEffects = otherEffects; }

    /** @return the name. */
    public String getName() { return name; }

    /** @return the type. */
    public String getType() { return type; }

    /** @return the abilities. */
    public String getAbility() { return ability; }


}
