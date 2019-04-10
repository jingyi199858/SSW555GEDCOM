package org.Gedcom;

import junit.framework.TestCase;
import org.gedcom4j.exception.GedcomParserException;

import java.io.IOException;

import static org.Gedcom.ChildBeforeMarriage.childBeforeMarriage;
import static org.Gedcom.MultiBirth.multiBirth;
import static org.Gedcom.ParentsAndMale.maleLastName;
import static org.Gedcom.ParentsAndMale.parentsNotTooOld;
import static org.junit.Assert.*;

public class Sprint3Test extends TestCase {
    public void test1() throws IOException, GedcomParserException {
        assertEquals(true,parentsNotTooOld("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
    public void test2() throws IOException, GedcomParserException{
        assertEquals(false,parentsNotTooOld("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
    }
    public void test3() throws IOException, GedcomParserException{
        assertEquals(false,parentsNotTooOld("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronteus12.ged"));
    }
    public void test4() throws IOException, GedcomParserException{
        assertEquals(true,parentsNotTooOld("src/resources/GEDCOMsourcefile/shakespeare.ged"));
    }
    public void test5() throws IOException, GedcomParserException{
        assertEquals(false,maleLastName("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
    public void test6() throws IOException, GedcomParserException{
        assertEquals(false,maleLastName("src/resources/GEDCOMsourcefile/shakespeare.ged"));
    }
    public void test7() throws IOException, GedcomParserException{
        assertEquals(true,childBeforeMarriage("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
    public void test8() throws IOException, GedcomParserException{
        assertEquals(false,childBeforeMarriage("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged") );
    }
    public void test9() throws IOException, GedcomParserException{
        assertEquals(true,multiBirth("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged") );
    }
    public void test10() throws IOException, GedcomParserException{
        assertEquals(true,multiBirth("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte3.ged") );
    }
    public void test11() throws IOException, GedcomParserException{
        assertEquals(true,multiBirth("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte5.ged") );
    }
    public void test12() throws IOException, GedcomParserException{
        assertEquals(true,multiBirth("src/resources/GEDCOMsourcefile/shakespeare.ged") );
    }
}