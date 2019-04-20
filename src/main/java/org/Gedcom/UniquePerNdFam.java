package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * US23 and US24
 * Author: Yi Jing
 */
public class UniquePerNdFam {
    /**
     * No more than one individual with the same name and birth date should appear in a GEDCOM file
     * @param file
     * @return
     */
    public static boolean uniquePerson(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        HashSet<String> birthday = new HashSet<String>();
        HashSet<String> name = new HashSet<String>();
        int count = 0;
        for (Individual individual : everybody) {
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0){
                String birth = individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                String personname = individual.getNames().toString();
                birthday.add(birth);
                name.add(personname);
                count++;
            }
        }
        if(birthday.size() < count && name.size() < count){
            return false;
        }
        return true;
    }

    /**
     * No more than one family with the same spouses by name and the same marriage date should appear in a GEDCOM file
     * @param file
     * @return
     * @throws IOException
     * @throws GedcomParserException
     */
    public static boolean uniqueFam(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        HashSet<String> marriage = new HashSet<String>();
        HashSet<String> name = new HashSet<String>();
        int count = 0;
        for (Family family : g.getFamilies().values()) {
            if(family.getHusband() != null && family.getWife() != null) {
                List events = family.getEvents();
                if (events != null && events.size() > 0 && ((FamilyEvent) events.get(0)).getDate() != null){
                    String marri = ((FamilyEvent) events.get(0)).getDate().toString();
                    String husb = family.getHusband().getIndividual().getNames().toString();
                    String wife = family.getWife().getIndividual().getNames().toString();
                    name.add(husb + wife);
                    marriage.add(marri);
                    count++;
                }
            }
        }
        if(marriage.size() < count && name.size() < count){
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, GedcomParserException {
        System.out.println(uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte234.ged"));
        System.out.println(uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte234.ged"));
    }
}
