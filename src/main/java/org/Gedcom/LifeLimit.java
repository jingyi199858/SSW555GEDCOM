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
     * Yi Jing
     * User Story 07
     * Death should be less than 150 years after birth for dead people, and current date should be less than 150 years after birth for all living people.
     * If detected more than 150 years old or current date more than 150 years, return false. If no failure, return true.
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
                String birth = individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                String death = individual.getEventsOfType(IndividualEventType.DEATH).get(0).getDate().toString();
                int life = Integer.parseInt(death.substring(death.length()-4)) - Integer.parseInt(birth.substring(birth.length()-4));
                System.out.println(life);
                lifetime.add(life);
            }
        }
        return lifetime;
    }
}
