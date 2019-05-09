package org.Gedcom;

import org.gedcom4j.exception.GedcomParserException;

import java.io.IOException;
import java.util.Scanner;

import static org.Gedcom.BirthBeforeDeath.birthBeforeDeath;
import static org.Gedcom.BirthBeforeMarriage.birthMarri;
import static org.Gedcom.ChildBeforeMarriage.childBeforeMarriage;
import static org.Gedcom.DateBeforeCurrent.beforeCurrent;
import static org.Gedcom.DivorceBeforeDeath.beforeDeath;
import static org.Gedcom.LifeLimit.lifeLimit;
import static org.Gedcom.MarriageBeforeDeath.marriageBeforeDeath;
import static org.Gedcom.MarriageBeforeDivorce.beforeDivorce;
import static org.Gedcom.MarriageLimit.marriageLimit;
import static org.Gedcom.MultiBirth.multiBirth;
import static org.Gedcom.ParentsAndMale.maleLastName;
import static org.Gedcom.ParentsAndMale.parentsNotTooOld;
import static org.Gedcom.UniqueFirstNdLivingSingle.livingSingle;
import static org.Gedcom.UniqueFirstNdLivingSingle.uniqueFirstName;
import static org.Gedcom.UniquePerNdFam.uniqueFam;
import static org.Gedcom.UniquePerNdFam.uniquePerson;

public class Main {
    public static void main(String[] args) throws IOException, GedcomParserException {
        String s1;
        String s2;
        System.out.println("1.Birth before marriage\n" +
                "2.Marriage before divorce\n" +
                "3.Dates before current date\n" +
                "4.Divorce before death\n" +
                "5.Birth before death\n" +
                "6.Marriage before death\n" +
                "7.Less then 150 years old\n" +
                "8.Marriage after 14\n" +
                "9.Parents not too old\n" +
                "10.Male last names\n" +
                "11.Birth before marriage of parents\n" +
                "12.Multiple births <= 5\n" +
                "13.Unique name and birth date\n" +
                "14.Unique families by spouses\n" +
                "15.Unique first names in families\n" +
                "16.List living single");
        Scanner sc = new Scanner(System.in);
        System.out.println("Type method:");
        s1 = sc.nextLine();
        System.out.println("Type file directory:");
        s2 = sc.nextLine();
        switch (s1){
            case "1":
                System.out.println(birthMarri(s2));
                break;

            case "2":
                System.out.println(beforeDivorce(s2));
                break;

            case "3":
                System.out.println(beforeCurrent(s2));
                break;

            case "4":
                System.out.println(beforeDeath(s2));
                break;

            case "5":
                System.out.println(birthBeforeDeath(s2));
                break;

            case "6":
                System.out.println(marriageBeforeDeath(s2));
                break;

            case "7":
                System.out.println(lifeLimit(s2));
                break;

            case "8":
                System.out.println(marriageLimit(s2));
                break;

            case "9":
                System.out.println(parentsNotTooOld(s2));
                break;

            case "10":
                System.out.println(maleLastName(s2));
                break;

            case "11":
                System.out.println(childBeforeMarriage(s2));
                break;

            case "12":
                System.out.println(multiBirth(s2));
                break;

            case "13":
                System.out.println(uniquePerson(s2));
                break;

            case "14":
                System.out.println(uniqueFam(s2));
                break;

            case "15":
                System.out.println(uniqueFirstName(s2));
                break;

            case "16":
                System.out.println(livingSingle(s2));
                break;

        }
    }
}
