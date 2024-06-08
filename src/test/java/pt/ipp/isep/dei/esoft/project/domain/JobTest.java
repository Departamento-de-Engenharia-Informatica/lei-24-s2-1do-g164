package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    @Test
    void ensureTwoJobsWithSameNameEquals() {
        Job j1 = new Job("name");
        Job j2 = new Job("name");
        assertEquals(j1, j2);
    }
    @Test
    void ensureSameJobEquals() {
        Job j1 = new Job("name");
        assertEquals(j1, j1);
    }

    @Test
    void ensureTwoJobsWithDiferentNameNotEquals() {
        Job j1 = new Job("name");
        Job j2 = new Job("differentName");
        assertNotEquals(j1, j2);
    }

    @Test
    void ensureJobDoesNotEqualNull() {
        Job j1 = new Job("name");
        assertNotEquals(j1, null);
    }

    @Test
    void ensureJobDoesNotEqualOtherObject() {
        Job j1 = new Job("prunner");
        assertNotEquals(j1, new Object());
    }

    @Test
    void ensureHashCodeIsEqualForEqualObjects() {
        String jobName = "new job";
        Job j1 = new Job(jobName);
        Job j2 = new Job(jobName);
        assertEquals(j1.hashCode(), j2.hashCode());
    }

    @Test
    void ensureHashCodeIsNotEqualForDifferentObjects() {

        Job s1 = new Job("aaa");
        Job s2 = new Job("bbb");
        assertNotEquals(s1.hashCode(), s2.hashCode());
    }
    @Test
    public void testToString() {
        Job j1 = new Job("aaa");
        assertEquals("aaa", j1.getJobName().toString());
    }
}
