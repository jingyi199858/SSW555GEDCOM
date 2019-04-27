package org.Gedcom;

import de.vandermeer.asciitable.AsciiTable;
import org.gedcom4j.exception.GedcomParserException;
import org.gedcom4j.model.Family;
import org.gedcom4j.model.FamilyEvent;
import org.gedcom4j.model.Gedcom;
import org.gedcom4j.model.Individual;
import org.gedcom4j.model.enumerations.IndividualEventType;
import org.gedcom4j.parser.GedcomParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains methods to display individuals and families in a format of Ascii table on console.
 * Author: Yi Jing
 */
public class DisplayFamilyTree {
    /**
     * This method reads file directory and create a table showing every individual personal information in a family tree.
     * @param file
     * @throws IOException
     * @throws GedcomParserException
     */
    public static void printEveryOne(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        List<Individual> everybody = new ArrayList<Individual>(g.getIndividuals().values());
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Name", "Gender", "BirthDate", "DeathDate");
        for(Individual individual: everybody){
            String birth = "N/A";
            String death = "N/A";
            if(individual.getEventsOfType(IndividualEventType.BIRTH).size() != 0 && individual.getEventsOfType(IndividualEventType.DEATH).size() != 0) {
                birth = individual.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                death = individual.getEventsOfType(IndividualEventType.DEATH).get(0).getDate().toString();
            }
            table.addRule();
            table.addRow(individual.getNames().toString(),individual.getSex().toString(), birth, death);
        }
        table.addRule();
        String rend = table.render();
        System.out.println(rend);
    }

    /**
     * This method reads a file location and create a table showing every family in a family tree.
     * @param file
     */
    public static void printEveryFamily(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("MarriedDate", "DivorcedDate", "Husband", "Wife", "Children");
        for (Family family : g.getFamilies().values()) {
            List events = family.getEvents();
            String marriage = "N/A";
            String divorce = "N/A";
            String husband = "N/A";
            String wife = "N/A";
            ArrayList children = new ArrayList();
            children.add("N/A");
            if (events != null && events.size() > 1 && ((FamilyEvent) events.get(0)).getDate() != null && ((FamilyEvent) events.get(1)).getDate() != null) {
                divorce = ((FamilyEvent) events.get(1)).getDate().toString();
                marriage = ((FamilyEvent) events.get(0)).getDate().toString();
            }
            if(family.getHusband() != null){
                husband = family.getHusband().getIndividual().getNames().toString();
            }
            if(family.getWife() != null){
                wife = family.getWife().getIndividual().getNames().toString();
            }
            if(family.getChildren() != null){
                children.removeAll(children);
                for(int i = 0; i < family.getChildren().size(); i++){
                    children.add(family.getChildren().get(i).getIndividual().getNames());
                }
            }
            table.addRule();
            table.addRow(marriage, divorce, husband, wife, children.toString());
        }
        table.addRule();
        String rend = table.render();
        System.out.println(rend);
    }
}
