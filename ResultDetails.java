public class ResultDetails {

    private String courseCode;
    private int creditUnit;
    private String grade;

    public ResultDetails(String courseCode, int creditUnit, String grade) {
        this.courseCode = courseCode;
        this.creditUnit = creditUnit;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCreditUnit() {
        return creditUnit;
    }

    public void setCreditUnit(int creditUnit) {
        this.creditUnit = creditUnit;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}