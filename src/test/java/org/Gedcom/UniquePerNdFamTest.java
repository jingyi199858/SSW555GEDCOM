package org.Gedcom;

import junit.framework.TestCase;
import org.gedcom4j.exception.GedcomParserException;

import java.io.IOException;

import static org.Gedcom.UniquePerNdFam.uniqueFam;
import static org.Gedcom.UniquePerNdFam.uniquePerson;
import static org.junit.Assert.*;

public class UniquePerNdFamTest extends TestCase {
    public static void test1() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
    public static void test2() throws IOException, GedcomParserException {
        assertEquals(false, uniquePerson("src/resources/GEDCOMsourcefile/shakespeare.ged"));
    }
    public static void test3() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
    }
    public static void test4() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"));
    }
    public static void test5() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged"));
    }
    public static void test6() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte4.ged"));
    }
    public static void test7() throws IOException, GedcomParserException {
        assertEquals(true, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged"));
    }
    public static void test8() throws IOException, GedcomParserException {
        assertEquals(false, uniquePerson("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte234.ged"));
    }
    public static void test9() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
    public static void test10() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/shakespeare.ged"));
    }
    public static void test11() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
    }
    public static void test12() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"));
    }
    public static void test13() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged"));
    }
    public static void test14() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte4.ged"));
    }
    public static void test15() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged"));
    }
    public static void test16() throws IOException, GedcomParserException {
        assertEquals(false, uniqueFam("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte234.ged"));
    }

}