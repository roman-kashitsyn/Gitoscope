package gitoscope.exception;

public class BlobNotFoundException extends GitoscopeException {

    public BlobNotFoundException(String projectName, String blobId) {
        super();
        this.projectName = projectName;
        this.blobId = blobId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getBlobId() {
        return this.blobId;
    }

    public BlobNotFoundException initCause(Throwable t) {
        super.initCause(t);
        return this;
    }

    private String projectName;
    private String blobId;
}
