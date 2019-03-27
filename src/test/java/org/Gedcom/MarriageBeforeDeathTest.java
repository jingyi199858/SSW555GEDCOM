package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;

import static org.Gedcom.MarriageBeforeDeath.marriageBeforeDeath;
import static org.junit.Assert.*;

public class MarriageBeforeDeathTest {

    @Test
    public void marriageBeforeDeathTest() throws IOException, GedcomParserException {
        assertEquals(true, marriageBeforeDeath("src/resources/GEDCOMsourcefile/bronte.ged"));
        assertEquals(false, marriageBeforeDeath("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte2.ged"));
    }
}