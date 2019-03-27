package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;

import static org.Gedcom.BirthBeforeDeath.birthBeforeDeath;
import static org.junit.Assert.*;

public class BirthBeforeDeathTest {

    @Test
    public void birthBeforeDeathTest() throws IOException, GedcomParserException {
        assertEquals(true,birthBeforeDeath("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
}