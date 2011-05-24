package gitoscope.service;

import java.util.*;
import org.slf4j.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.*;
import org.eclipse.jgit.api.*;

import gitoscope.domain.*;
import gitoscope.exception.CommitNotFoundException;

public class DefaultCommitService implements CommitService {

	private static final Logger LOG =
			LoggerFactory.getLogger(DefaultCommitService.class);

	@Override
	public Commit findById(Project project, String id) {
		Commit commit = null;
		AnyObjectId commitId;

		try {
			commitId = ObjectId.fromString(id);
		} catch (Exception e) {
			LOG.error("Invalid commit id [{}]", id);
			throw new CommitNotFoundException(project.getName(), id).initCause(e);
		}

		Git git = new Git(project.getRepository());
		LogCommand log = git.log();

		try {
			log.add(commitId);
			Iterable<RevCommit> commitList = log.call();
			Iterator<RevCommit> it = commitList.iterator();
			if (it.hasNext()) {
				commit = new Commit(it.next());
			} else {
				throw new Exception("No commits found");
			}
		} catch (Exception e) {
			LOG.error("Commit [{}] not found in project {}", id, project.getName());
			throw new CommitNotFoundException(project.getName(), id).initCause(e);
		}
		return commit;
	}

}
