package gr.aueb.cf.schoolapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {

    @Test
    void defaultConstructorGettersAndSetters() {
        Teacher teacher = new Teacher();

        teacher.setId(1);
        assertEquals(1, teacher.getId());

        teacher.setFirstname("Athanassios");
        assertEquals("Athanassios", teacher.getFirstname());

        teacher.setLastname("Androutsos");
        assertEquals("Androutsos", teacher.getLastname());
    }

    @Test
    void overloadedConstructorAndToString() {
        Teacher teacher = new Teacher(1, "Anna", "Giannoutsou");
        assertEquals(1, teacher.getId());
        assertEquals("Anna", teacher.getFirstname());
        assertEquals("Giannoutsou", teacher.getLastname());

        String expected = "id=1, firstname=Anna, lastname=Giannoutsou";
        assertEquals(expected, teacher.toString());
    }
}