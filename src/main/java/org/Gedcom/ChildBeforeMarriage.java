package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.List;

/**
 * US08
 * Children should be born after marriage of parents (and not more than 9 months after their divorce)
 */
public class ChildBeforeMarriage {
    public static boolean childBeforeMarriage(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()) {
            if (family.getHusband() != null && family.getWife() != null) {
                List events = family.getEvents();
                String marriage = ((FamilyEvent) events.get(0)).getDate().toString();
                if (family.getChildren() != null) {
                    if (family.getChildren().size() != 0) {
                        for (int i = 0; i < family.getChildren().size(); i++) {
                            Individual child = family.getChildren().get(i).getIndividual();
                            if (child.getEventsOfType(IndividualEventType.BIRTH).size() != 0) {
                                String childbirth = child.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                                if(Integer.parseInt(childbirth.substring(childbirth.length() - 4)) < Integer.parseInt(marriage.substring(marriage.length() - 4))){
                                    return false;
                                }

                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
