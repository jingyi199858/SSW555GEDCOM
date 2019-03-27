package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;

import static org.Gedcom.MarriageLimit.marriageLimit;
import static org.junit.Assert.*;

public class MarriageLimitTest {

    @Test
    public void marriageLimitTest() throws IOException, GedcomParserException {
        assertEquals(true, marriageLimit("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
}