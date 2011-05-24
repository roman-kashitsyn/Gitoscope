package gitoscope.domain;

import java.util.Date;
import java.io.File;
import org.eclipse.jgit.lib.*;

public class Project {

	public Project(Repository repository) {
		this.repository = repository;
	}

	public String getName() {
		File baseDirectory = repository.getWorkTree();
		return baseDirectory.getName();
	}

	public Person getOwner() {
		return null;
	}

	public Date getLastModified() {
		return new Date(repository.getDirectory().lastModified());
	}

	public Repository getRepository() {
		return repository;
	}

	private Repository repository;
}
