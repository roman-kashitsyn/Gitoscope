package gitoscope.domain;

import java.util.*;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.*;

public class Commit {

	public Commit(RevCommit commit) {
		this.commit = commit;
	}

	public String getId() {
		return ObjectId.toString(commit.getId());
	}

	public Date getWhen() {
		long when = 1000L * commit.getCommitTime();
		return new Date(when);
	}

	public Person getAuthor() {
		return new Person(commit.getAuthorIdent());
	}

	public Person getCommitter() {
		return new Person(commit.getCommitterIdent());
	}

	public List<Commit> getParents() {
		List<Commit> parents = new ArrayList();
		for (RevCommit c : commit.getParents()) {
			parents.add(new Commit(c));
		}
		return parents;
	}

	public String getTreeId() {
		return ObjectId.toString(commit.getTree());
	}

	public String getShortMessage() {
		return commit.getShortMessage();
	}

	public String getFullMessage() {
		return commit.getFullMessage();
	}

	private RevCommit commit;

}
