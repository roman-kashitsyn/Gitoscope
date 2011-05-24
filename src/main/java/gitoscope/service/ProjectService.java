package gitoscope.service;

import java.util.*;
import gitoscope.domain.*;

public interface ProjectService {

	/**
	 * Returns list of all available projects.
	 *
	 * @return List of all available projects.
	 */
	List<Project> listProjects();

	/**
	 * @return Project with specified name.
	 */
	Project findProjectByName(String projectName);

	/**
	 * Returns list of all available commits in a project with
	 * specified name.
	 */
	List<Commit> listCommits(
		Project project,
		Map<String,	Integer> paginateParams);

}
