package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualAttributeType;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * US14
 * No more than five siblings should be born at the same time
 */
public class MultiBirth {
    public static boolean multiBirth(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        HashSet birth = new HashSet();
        for (Family family : g.getFamilies().values()){
            if (family.getChildren() != null) {
                if (family.getChildren().size() != 0) {
                    for (int i = 0; i < family.getChildren().size(); i++) {
                        Individual child = family.getChildren().get(i).getIndividual();
                        if (child.getEventsOfType(IndividualEventType.BIRTH).size() != 0) {
                            String childbirth = child.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                            birth.add(childbirth);
                        }
                    }
                    if(family.getChildren().size() - birth.size() > 5){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
