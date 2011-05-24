package gitoscope.exception;

public class TreeNotFoundException extends GitoscopeException {

	public TreeNotFoundException(String projectName, String treeId) {
		super();
		this.projectName = projectName;
		this.treeId = treeId;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public String getTreeId() {
		return this.treeId;
	}

	public TreeNotFoundException initCause(Throwable t) {
		super.initCause(t);
		return this;
	}

	private String projectName;
	private String treeId;
}
