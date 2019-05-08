package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.*;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarriageLimit {
    /**
     * Yi jing
     * User Story 10
     * Marriage should be at least 14 years after birth of both spouses (parents must be at least 14 years old)
     *
     * @param file
     * @return
     */
    public static boolean marriageLimit(String file) throws IOException, GedcomParserException {
        ArrayList<Integer> list = marriageTime(file);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i) < 14){
                return false;
            }
        }
        return true;
    }

    static ArrayList<Integer> marriageTime(String file) throws IOException, GedcomParserException {
        ArrayList<Integer> marriageTime = new ArrayList<Integer>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()) {
            List events = family.getEvents();
            if (events != null && events.size() > 0 && ((FamilyEvent) events.get(0)).getDate() != null) {
                String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
                if (family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0) {
                    addMarriageTime(marriageTime, marriage, family.getHusband(), family);
                }
                if (family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0) {
                    addMarriageTime(marriageTime, marriage, family.getWife(), family);
                }
            }
        }

        return marriageTime;
    }

    private static void addMarriageTime(ArrayList<Integer> marriageTime, String marriage, IndividualReference husband, Family family) {
        String husbandbirth = husband.getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
        marriageTime.add(Integer.parseInt(marriage.substring(marriage.length()-4)) - Integer.parseInt(husbandbirth.substring(husbandbirth.length()-4)));
    }
}
