package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.List;

public class MarriageBeforeDeath {
    /**
     * Marriage should occur before death of either spouse
     * User Story 05
     * @param file
     * @return
     */
    public static boolean marriageBeforeDeath(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()) {
            List events = family.getEvents();
            if (events != null && events.size() > 0 && ((FamilyEvent) events.get(0)).getDate() != null) {
                String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
                if (family.getHusband().getIndividual().getEventsOfType(IndividualEventType.DEATH).size() > 0) {
                    String husbanddeath = family.getHusband().getIndividual().getEventsOfType(IndividualEventType.DEATH).get(0).getDate().toString();
                    if (Integer.parseInt(marriage.substring(marriage.length() - 4)) > Integer.parseInt(husbanddeath.substring(husbanddeath.length() - 4))) {
                        return false;
                    }
                }
                if (family.getWife().getIndividual().getEventsOfType(IndividualEventType.DEATH).size() > 0) {
                    String wifedeath = family.getWife().getIndividual().getEventsOfType(IndividualEventType.DEATH).get(0).getDate().toString();
                    if (Integer.parseInt(marriage.substring(marriage.length() - 4)) > Integer.parseInt(wifedeath.substring(wifedeath.length() - 4))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
