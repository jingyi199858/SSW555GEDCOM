package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;

/**
 * US12 and US16
 * Author: Yi Jing
 */
public class ParentsAndMale {
    /**
     * Mother should be less than 60 years older than her children and father should be less than 80 years older than his children
     * @param file
     * @return
     */
    public static boolean parentsNotTooOld(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()){
            if(family.getHusband() != null && family.getChildren().size() != 0){
                Individual dad = family.getHusband().getIndividual();
                if(dad.getEventsOfType(IndividualEventType.BIRTH).size() != 0){
                    for(int i = 0; i < family.getChildren().size(); i++){
                        Individual child = family.getChildren().get(i).getIndividual();
                        if(child.getEventsOfType(IndividualEventType.BIRTH).size() != 0){
                            String dadbirth = dad.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                            String childbirth = child.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                            int different = Integer.parseInt(dadbirth.substring(dadbirth.length()-4)) - Integer.parseInt(childbirth.substring(childbirth.length()-4));
                            if(different > 80){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * All male members of a family should have the same last name
     * @param file
     * @return
     */
    public static boolean maleLastName(String file){
        return true;
    }

    public static void main(String[] args) {

    }
}
