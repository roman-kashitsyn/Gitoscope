package gitoscope.domain;

import org.eclipse.jgit.lib.Repository;

import java.io.File;
import java.util.Date;

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
