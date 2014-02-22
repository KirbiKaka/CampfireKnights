package Mechanics;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import Equipment.Iron_Daggers;
import Equipment.Iron_Sword_Shield;
import Equipment.Wooden_Bow;
import Equipment.Wooden_Restoration_Staff;


public class CampfireKnights extends StateBasedGame {

    public static Hero[] heroes = new Hero[4];

    /** Runs the game. */
    public static void main(String[] args) {
        try {
            CampfireKnights game = new CampfireKnights("Campfire Knights");
            AppGameContainer app = new AppGameContainer(game, 800, 600, false);
            game.init(app);
            game.initStatesList(app);
            app.setVSync(true);
            app.setShowFPS(false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public CampfireKnights(String name) {
        super(name);
        Inventory.addEquipment(new Iron_Daggers(), 4);
        Inventory.addEquipment(new Iron_Sword_Shield(), 4);
        Inventory.addEquipment(new Wooden_Bow(), 4);
        Inventory.addEquipment(new Wooden_Restoration_Staff(), 3);
        heroes[Hero_Names.OPTIMIST_ID] = new Hero(Hero_Names.OPTIMIST, 0,
                "assets/heroes/Kirthas_Stand.png", 100, 200);
        heroes[Hero_Names.RATIONAL_ID] = new Hero(Hero_Names.RATIONAL, 1,
                "assets/heroes/Isabelle_Stand.png", 200, 200);
        heroes[Hero_Names.APATHETIC_ID] = new Hero(Hero_Names.APATHETIC, 2,
                "assets/heroes/Damian_Stand.png", 200, 100);
        heroes[Hero_Names.CYNIC_ID] = new Hero(Hero_Names.CYNIC, 3,
                "assets/heroes/Sky_Stand.png", 100, 100);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        addState(new MainMenu());
        addState(new OverviewMenu());
        addState(new ControlsScreen());
        addState(new CombatState());
        addState(new EquipMenu());
    }

    public Hero[] getHeroes() { return heroes; }

}
