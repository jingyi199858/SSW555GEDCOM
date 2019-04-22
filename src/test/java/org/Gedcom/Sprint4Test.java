package org.Gedcom;

import junit.framework.TestCase;
import org.gedcom4j.exception.GedcomParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.Gedcom.UniqueFirstNdLivingSingle.livingSingle;
import static org.Gedcom.UniqueFirstNdLivingSingle.uniqueFirstName;
import static org.Gedcom.UniquePerNdFam.uniqueFam;
import static org.Gedcom.UniquePerNdFam.uniquePerson;
import static org.junit.Assert.*;

public class Sprint4Test extends TestCase {
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
    //US25 & US31
    public static void test17() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
    }
    public static void test18() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"));
    }
    public static void test19() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged"));
    }
    public static void test20() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte4.ged"));
    }
    public static void test21() throws IOException, GedcomParserException {
        assertEquals(true, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged"));
    }
    public static void test22() throws IOException, GedcomParserException {
        assertEquals(false, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte234.ged"));
    }
    public static void test23() throws IOException, GedcomParserException {
        assertEquals(false, uniqueFirstName("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte6.ged"));
    }
    public static void test24() throws IOException, GedcomParserException {
        List a = new ArrayList();
        a.add("Brontë, Elizabeth, child of Branwell, Maria and Brontë, Patrick, b.8 FEB 1815, d.15 JUN 1825");
        a.add("Brontë, Patrick Branwell, child of Branwell, Maria and Brontë, Patrick, b.26 JUN 1817, d.24 SEP 1848");
        a.add("Brontë, Emily Jane, child of Branwell, Maria and Brontë, Patrick, b.30 JUL 1818, d.19 DEC 1848");
        a.add("Brontë, Maria, child of Branwell, Maria and Brontë, Patrick, b.23 APR 1814, d.6 MAY 1825");
        a.add("Branwell, Elizabeth \"Aunt\", child of Carne, Anne and Branwell, Thomas, b.1776, d.29 OCT 1842");
        a.add("Brontë, Emily Jane, child of Branwell, Maria and Brontë, Patrick, b.17 JAN 1820, d.28 MAY 1849");
        List temp = livingSingle("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte6.ged");
        for(int i = 0; i < temp.size(); i++){
            assertEquals(a.get(i).toString(), temp.get(i).toString());
        }
    }
}