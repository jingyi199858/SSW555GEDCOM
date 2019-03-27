package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;

import static org.Gedcom.LifeLimit.lifeLimit;
import static org.junit.Assert.*;

public class LifeLimitTest {

    @Test
    public void lifeLimitTest() throws IOException, GedcomParserException {
        assertEquals(true, lifeLimit("src/resources/GEDCOMsourcefile/bronte.ged"));
        assertEquals(true, lifeLimit("src/resources/GEDCOMsourcefile/shakespeare.ged"));
        assertEquals(false, lifeLimit("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged"));
    }
}