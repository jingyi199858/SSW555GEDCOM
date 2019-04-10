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
     * US12
     * Mother should be less than 60 years older than her children and father should be less than 80 years older than his children
     * @param file
     * @return
     */
    public static boolean parentsNotTooOld(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()){
            if(family.getHusband() != null && family.getWife() != null){
                Individual dad = family.getHusband().getIndividual();
                Individual mom = family.getWife().getIndividual();
                if(dad.getEventsOfType(IndividualEventType.BIRTH).size() != 0 && mom.getEventsOfType(IndividualEventType.BIRTH).size() != 0){
                    if( family.getChildren() != null) {
                        if(family.getChildren().size() != 0) {
                            for (int i = 0; i < family.getChildren().size(); i++) {
                                Individual child = family.getChildren().get(i).getIndividual();
                                if (child.getEventsOfType(IndividualEventType.BIRTH).size() != 0) {
                                    String dadbirth = dad.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                                    String mombirth = mom.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                                    String childbirth = child.getEventsOfType(IndividualEventType.BIRTH).get(0).getDate().toString();
                                    int daddifference = Integer.parseInt(dadbirth.substring(dadbirth.length() - 4)) - Integer.parseInt(childbirth.substring(childbirth.length() - 4));
                                    int momdifference = Integer.parseInt(mombirth.substring(mombirth.length() - 4)) - Integer.parseInt(childbirth.substring(childbirth.length() - 4));
                                    if (daddifference > 80 || momdifference > 60) {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * US16
     * All male members of a family should have the same last name
     * @param file
     * @return
     */
    public static boolean maleLastName(String file) throws IOException, GedcomParserException {
        GedcomParser gp = new GedcomParser();
        gp.load(file);
        Gedcom g = gp.getGedcom();
        for (Family family : g.getFamilies().values()) {
            if (family.getHusband() != null) {
                String lastname = family.getHusband().getIndividual().getSurnames().toString();
                if (family.getChildren() != null) {
                    if (family.getChildren().size() != 0) {
                        for (int i = 0; i < family.getChildren().size(); i++) {
                            Individual child = family.getChildren().get(i).getIndividual();
                            String childlastname = child.getSurnames().toString();
                            if(!childlastname.equals(lastname)){
                                System.out.println(lastname + "    " +  childlastname);
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException, GedcomParserException {
        /*System.out.println(parentsNotTooOld("src/resources/GEDCOMsourcefile/bronte.ged"));
        System.out.println(parentsNotTooOld("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
        System.out.println(parentsNotTooOld("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronteus12.ged"));
        System.out.println(parentsNotTooOld("src/resources/GEDCOMsourcefile/shakespeare.ged"));*/
        System.out.println(maleLastName("src/resources/GEDCOMsourcefile/bronte.ged"));
        //System.out.println(maleLastName("src/resources/GEDCOMsourcefile/shakespeare.ged"));
    }
}
