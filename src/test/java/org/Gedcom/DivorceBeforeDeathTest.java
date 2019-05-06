package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.Gedcom.DivorceBeforeDeath.beforeDeath;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DivorceBeforeDeathTest {

    @Test
    public void beforeDeathTest() throws IOException, GedcomParserException {
        ArrayList list = new ArrayList();
        assertEquals(list, beforeDeath("src/resources/GEDCOMsourcefile/bronte.ged"));
    }
}