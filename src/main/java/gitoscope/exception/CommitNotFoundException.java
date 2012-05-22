package gitoscope.exception;

public class CommitNotFoundException extends GitoscopeException {

    public CommitNotFoundException(String projectName, String commitId) {
        super();
        this.projectName = projectName;
        this.commitId = commitId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getCommitId() {
        return this.commitId;
    }

    public CommitNotFoundException initCause(Throwable t) {
        super.initCause(t);
        return this;
    }

    private String projectName;
    private String commitId;
}
