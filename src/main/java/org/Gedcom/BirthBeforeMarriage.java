package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Author Yi Jing
 * User story 2
 */
public class BirthBeforeMarriage {

    /**
     * Birth should occur before marriage of an individual.
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static boolean birthMarri(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()) {
            List events = family.getEvents();
            String husbandbirth = "";
            String wifebirth = "";
            if (events != null && events.size() > 0 && ((FamilyEvent) events.get(0)).getDate() != null) {
                String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
                if (family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0 && family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0) != null){
                    husbandbirth = family.getHusband().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                }
                if (family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).size() > 0 && family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0) != null){
                    wifebirth = family.getWife().getIndividual().getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                }
                if(!marriage.equals("") && !marriage.equals(null)) {
                    int marriagedate = Integer.parseInt(marriage.substring(marriage.length() - 4));
                    if(!husbandbirth.equals("") && !husbandbirth.equals(null)){
                        int husbanddate = Integer.parseInt(husbandbirth.substring(husbandbirth.length() - 4));
                        if(husbanddate > marriagedate){
                            return false;
                        }
                    }
                    if(!wifebirth.equals("") && !wifebirth.equals(null)){
                        int wifedate = Integer.parseInt(wifebirth.substring(wifebirth.length() - 4));
                        if(wifedate > marriagedate){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
