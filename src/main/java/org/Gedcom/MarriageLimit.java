package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.IndividualEvent;
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
        List<Family> everyfamily = new ArrayList<Family>(g.getFamilies().values());
        for (Family family : g.getFamilies().values()) {
            List events = family.getEvents();
            String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
            if(family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0) {
                String husbandbirth = family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                marriageTime.add(Integer.parseInt(marriage.substring(marriage.length()-4)) - Integer.parseInt(husbandbirth.substring(husbandbirth.length()-4)));
            }
            if(family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0) {
                String wifebirth = family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                marriageTime.add(Integer.parseInt(marriage.substring(marriage.length()-4)) - Integer.parseInt(wifebirth.substring(wifebirth.length()-4)));
            }
        }

        return marriageTime;
    }

    /*public static void main(String[] args) throws IOException, GedcomParserException {
        marriageTime("src/resources/GEDCOMsourcefile/bronte.ged");
    }*/
}
