package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BirthBeforeDeath {
    /**
     * Birth should occur before death of an individual
     * User Story 03
     * @param file
     * @return
     */
    public static boolean birthBeforeDeath(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        for (Individual individual : everybody) {
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0 && individual.getEventsOfType(IndividualEventType.DEATH).size() != 0){
                String birth = individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                String death = individual.getEventsOfType(IndividualEventType.DEATH).get(0).getDate().toString();
                if(Integer.parseInt(death.substring(death.length()-4)) < Integer.parseInt(birth.substring(birth.length()-4))){
                    return false;
                }
            }
        }
        return true;
    }

}
