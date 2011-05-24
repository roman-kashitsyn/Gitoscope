package gitoscope.service;

import java.io.File;
import java.util.*;
import org.slf4j.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import gitoscope.domain.*;
import gitoscope.exception.ProjectNotFoundException;

public class FileRepositoryService implements ProjectService {

	public static final Logger LOG =
			LoggerFactory.getLogger(FileRepositoryService.class);

	@Override
	public List<Project> listProjects() {
		if (!isValidDirectory(this.baseDirectory)) {
			LOG.error("Base directory {} is not a valid directory", baseDirecotoryName());
			return (List<Project>)Collections.EMPTY_LIST;
		} else {
			List<Project> projectList = new ArrayList<Project>();

			for (File projectDir : baseDirectory.listFiles()) {
				Project project = makeProject(projectDir);
				if (project != null) {
					projectList.add(project);
				}
			}

			return projectList;
		}
	}

	@Override
	public Project findProjectByName(String projectName) {
		String projectDirPath = baseDirectory.getAbsolutePath() + "/" + projectName;
		File projectDir = new File(projectDirPath);
		Project project = makeProject(projectDir);
		if (project == null) {
			throw new ProjectNotFoundException(projectName);
		} else {
			return project;
		}
	}

	@Override
	public List<Commit> listCommits(
		Project project,
		Map<String, Integer> paginateParams) {
		Repository repo = project.getRepository();
		Git git = new Git(repo);
		LogCommand log = git.log();

		List<Commit> commits = new ArrayList<Commit>();
		try {
			for (RevCommit commit : log.call()) {
				commits.add(new Commit(commit));
			}
		} catch (Exception e) { }

		return commits;
	}

	private boolean isValidDirectory(File dir) {
		return
			dir != null &&
			dir.exists() &&
			baseDirectory.isDirectory() &&
			(baseDirectory.listFiles().length != 0);
	}

	private Project makeProject(File dir) {
		File gitDir = new File(dir.getAbsolutePath() + "/.git");
		if (gitDir.exists() && gitDir.isDirectory()) {
			return makeProjectFromGitDirectory(gitDir);
		} else {
			LOG.warn("{} is not a valid git directory", gitDir.getAbsolutePath());
			return null;
		}
	}

	private Project makeProjectFromGitDirectory(File gitDir) {
		try {
			FileRepositoryBuilder builder = new FileRepositoryBuilder();
			Repository repo = builder.setGitDir(gitDir).build();
			return new Project(repo);
		} catch (Exception e) {
			return null;
		}

	}

	private String baseDirecotoryName() {
		if (baseDirectory != null) {
			return this.baseDirectory.getName();
		} else {
			return "null";
		}
	}

	public void setBaseDirectory(String name) {
		baseDirectory = new File(name);
	}

	public String getBaseDirectory() {
		return baseDirectory.getAbsolutePath();
	}

	private File baseDirectory;
}
