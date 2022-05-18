package id.ac.ui.cs.advprog.soulcatcher.main.core.factory.personafactory;

import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Classes;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.Knight;
import id.ac.ui.cs.advprog.soulcatcher.main.core.persona.PersonaType;

public class PersonaFactory {
    public static Classes createPersona(String personaType){
        if(personaType.equalsIgnoreCase(PersonaType.KNIGHT.toString())){
            return new Knight();
        }
        return null;
    }
}
