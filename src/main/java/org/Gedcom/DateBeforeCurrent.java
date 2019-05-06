package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.*;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Author: Yi Jing
 * User story 1
 */
public class DateBeforeCurrent {
    /**
     * Return null if all dates are before current date, return person if dates indicating errors.
     * @param file
     * @throws IOException
     * @throws GedcomParserException
     */
    public static ArrayList<Individual> beforeCurrent(String file) throws IOException, GedcomParserException {
        int currentyear = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<Individual> errorperson = new ArrayList<Individual>();
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        for(Individual individual: everybody){
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0) {
                List BIRTH = individual.getEventsOfType(IndividualEventType.BIRTH);
                addErrorPerson(currentyear, errorperson, individual, BIRTH);
            }
            if(individual.getEventsOfType(IndividualEventType.DEATH).size() != 0) {
                List DEATH = individual.getEventsOfType(IndividualEventType.DEATH);
                addErrorPerson(currentyear, errorperson, individual, DEATH);
            }
        }
        for (Family family : g.getFamilies().values()){
            if (family.getHusband() != null && family.getWife() != null){
                List events = family.getEvents();
                if(events.size() != 0) {
                    addErrorFamily(currentyear, errorperson, family, events, 0);
                    if(events.size() > 1){
                        addErrorFamily(currentyear, errorperson, family, events, 1);
                    }
                }
            }
        }

        return errorperson;
    }

    private static void addErrorFamily(int currentyear, ArrayList<Individual> errorperson, Family family, List events, int i) {
        String marriage = ((FamilyEvent) events.get(i)).getDate().toString();
        if(Integer.parseInt(marriage.substring(marriage.length() - 4)) > currentyear){
            if(!errorperson.contains(family.getHusband()) && !errorperson.contains(family.getWife())){
                errorperson.add(family.getHusband().getIndividual());
                errorperson.add(family.getWife().getIndividual());
            }
        }
    }

    private static void addErrorPerson(int currentyear, ArrayList<Individual> errorperson, Individual individual, List BIRTH) {
        String birth = ((IndividualEvent) BIRTH.get(0)).getDate().toString();
        if(Integer.parseInt(birth.substring(birth.length() - 4)) > currentyear){
            errorperson.add(individual);
        }
    }
}
