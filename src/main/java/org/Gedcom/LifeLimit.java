package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.IndividualEvent;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LifeLimit {
    /**
     * Death should be less than 150 years after birth for dead people, and current date should be less than 150 years after birth for all living people.
     * @return
     */
    public static boolean lifeLimit(String file) throws IOException, GedcomParserException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list = lifeTime(file);
        for (Integer integer : list) {
            if (integer > 150) {
                return false;
            }
        }
        return true;
    }


     static ArrayList<Integer> lifeTime(String file) throws IOException, GedcomParserException {
        ArrayList<Integer> lifetime = new ArrayList<Integer>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        for (Individual individual : everybody) {
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0 && individual.getEventsOfType(IndividualEventType.DEATH).size() != 0){
                int life = Integer.parseInt(((IndividualEvent) individual.getEventsOfType(IndividualEventType.BIRTH).get(0)).getDate().toString()) - Integer.parseInt(((IndividualEvent) individual.getEventsOfType(IndividualEventType.BIRTH).get(0)).getDate().toString());
                lifetime.add(life);
            }
        }
        return lifetime;
    }
}
