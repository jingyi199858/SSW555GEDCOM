package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.Gedcom.DivorceBeforeDeath.beforeDeath;
import static org.Gedcom.MarriageBeforeDivorce.beforeDivorce;
import static org.junit.Assert.*;

public class MarriageBeforeDivorceTest {

    @Test
    public void beforeDivorceTest() throws IOException, GedcomParserException {
        ArrayList list = new ArrayList();
        assertEquals(beforeDivorce("src/resources/GEDCOMsourcefile/bronte.ged"),list);
    }
}