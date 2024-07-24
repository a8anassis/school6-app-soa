package gr.aueb.cf.schoolapp.dto;

public class TeacherInsertDTO extends BaseDTO {

    public TeacherInsertDTO() {}

    public TeacherInsertDTO(String firstname, String lastname) {
        super(firstname, lastname);
    }

    @Override
    public String toString() {
        return  this.getFirstname() + ", " + this.getLastname();
    }
}
