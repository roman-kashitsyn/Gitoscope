package gitoscope.exception;

public class ProjectNotFoundException extends GitoscopeException {

    public ProjectNotFoundException(String projectName) {
        super();
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public ProjectNotFoundException initCause(Throwable t) {
        super.initCause(t);
        return this;
    }

    private String projectName;
}
