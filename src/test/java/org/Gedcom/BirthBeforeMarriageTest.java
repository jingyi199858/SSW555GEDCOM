package org.Gedcom;

import junit.framework.TestCase;
import org.Gedcom.BirthBeforeMarriage;
import org.gedcom4j.exception.GedcomParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.Gedcom.BirthBeforeMarriage.birthMarri;

public class BirthBeforeMarriageTest extends TestCase {
    public void test1() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"),false);
    }

    public void test2() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"),true);
    }

    public void test3() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged"),false);
    }

    public void test4() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte4.ged"),false);
    }

    public void test5() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged"),false);
    }

    public void test6() throws IOException, GedcomParserException {
        assertEquals(birthMarri("src/resources/GEDCOMsourcefile/bronte.ged"),true);
    }
}