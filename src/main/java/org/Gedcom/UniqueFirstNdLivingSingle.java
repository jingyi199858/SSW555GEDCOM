package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * US25 & US31
 */
public class UniqueFirstNdLivingSingle {
    /**
     * No more than one child with the same name and birth date should appear in a family
     * @param file
     * @return
     */
    public static boolean uniqueFirstName(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for(Family family : g.getFamilies().values()) {
            HashSet<String> childrenbirth = new HashSet<String>();
            HashSet<String> childrennames = new HashSet<String>();
            String birth = "";
            String name = "";
            if(family.getChildren() != null){
                if (family.getChildren().size() != 0) {
                    for (int i = 0; i < family.getChildren().size(); i++) {
                        Individual child = family.getChildren().get(i).getIndividual();
                        if (child.getEventsOfType(IndividualEventType.BIRTH).size() != 0){
                            birth = child.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                        }
                        if(child.getNames() != null){
                            name = child.getNames().toString();
                        }
                        childrenbirth.add(birth);
                        childrennames.add(name);
                    }
                }
                if(childrenbirth.size() < family.getChildren().size() || childrennames.size() < family.getChildren().size()){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * List all living people over 30 who have never been married in a GEDCOM file
     * @param file
     * @return
     */
    public static List livingSingle(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        HashSet spouse = new HashSet();
        for (Family family : g.getFamilies().values()) {
            if(family.getHusband() != null){
                spouse.add(family.getHusband().getIndividual());
            }
            if(family.getWife() != null){
                spouse.add(family.getWife().getIndividual());
            }
        }
        everybody.removeAll(spouse);
        return everybody;
    }
}
