package id.ac.ui.cs.advprog.soulcatcher.main.core.factory.weaponfactory;

import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword.NapoleonSword;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword.SimitarSword;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword.Sword;
import id.ac.ui.cs.advprog.soulcatcher.main.core.gear.weapons.sword.SwordType;

public class SwordFactory {
    public static Sword createSword(String swordType){
        if(swordType.equalsIgnoreCase(SwordType.NAPOLEON.toString())){
            return new NapoleonSword();
        }
        else if(swordType.equalsIgnoreCase(SwordType.SIMITAR.toString())){
            return new SimitarSword();
        }
        return null;
    }
}
