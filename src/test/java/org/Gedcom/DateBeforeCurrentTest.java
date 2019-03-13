package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.Gedcom.DateBeforeCurrent.beforeCurrent;
import static org.junit.Assert.*;

public class DateBeforeCurrentTest {

    @Test
    public void corretTest() throws IOException, GedcomParserException {
        ArrayList<String> list = new ArrayList<String>();
        assertEquals(list, beforeCurrent("src/resources/GEDCOMsourcefile/bronte.ged"));

    }

    @Test
    public void errorTest() throws IOException, GedcomParserException {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Brontë, Patrick aka Brunty, spouse of Branwell, Maria, child of McClory, Eleanor and Brunty, Hugh, b.17 MAR 1813, d.7 JUN 2077");
        assertEquals(list.toString(),beforeCurrent("src/resources/GEDCOMsourcefile/EditedFamilyTree/bronte1.ged").toString());
    }
}