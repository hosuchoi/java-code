package org.lake.javacode.jacoco;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClubTest {

    @Test
    public void isFull() {
        Club club = new Club();
        club.maxNumberOfAttendees = 100;
        club.numberOfEnrollment = 10;
        Assert.assertFalse(club.isEnrollmentFull());

        club.maxNumberOfAttendees = 0;
        Assert.assertFalse(club.isEnrollmentFull());

        club.maxNumberOfAttendees = 1;
        Assert.assertFalse(!club.isEnrollmentFull());
    }

}