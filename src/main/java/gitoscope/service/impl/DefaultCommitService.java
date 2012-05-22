package gitoscope.service.impl;

import gitoscope.domain.Commit;
import gitoscope.domain.Project;
import gitoscope.exception.CommitNotFoundException;
import gitoscope.service.CommitService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.revwalk.filter.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DefaultCommitService implements CommitService {

    private static final Logger LOG =
            LoggerFactory.getLogger(DefaultCommitService.class);

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

    public List<Commit> searchCommits(Project project,
                                      int maxResults,
                                      Map<String, String> filter) {
        try {
            Repository repo = project.getRepository();
            RevWalk revWalk = new RevWalk(repo);
            if (maxResults <= 0) {
                maxResults = Integer.MAX_VALUE;
            }
            setHeadAsStart(repo, revWalk);
            setUpFilter(revWalk, filter);

            Iterator<RevCommit> revIterator = revWalk.iterator();
            List<Commit> results = new LinkedList<Commit>();
            while (revIterator.hasNext() && results.size() < maxResults) {
                results.add(new Commit(revIterator.next()));
            }

            return results;
        } catch (Exception e) {
            LOG.error("error! ", e);
            return Collections.emptyList();
        }
    }

    private void setHeadAsStart(Repository repo, RevWalk rw) {
        try {
            rw.markStart(getHead(repo));
        } catch (Exception e) {
            LOG.error("Unable to get repository HEAD");
        }
    }

    private void setUpFilter(RevWalk rw, Map<String, String> filterMap) {
        if (filterMap == null || filterMap.size() == 0) {
            return;
        }
        Collection<RevFilter> filters = new LinkedList<RevFilter>();
        String token = null;

        token = filterMap.get("start");
        if (token != null) {
            try {
                RevCommit start = getCommit(rw, token);
                rw.markStart(start);
            } catch (Exception e) {
                LOG.error("Unable to mark commit as start", e);
            }
        }

        token = filterMap.get("message");
        if (token != null) {
            filters.add(MessageRevFilter.create(token));
        }

        token = filterMap.get("author");
        if (token != null) {
            filters.add(AuthorRevFilter.create(token));
        }

        token = filterMap.get("committer");
        if (token != null) {
            filters.add(CommitterRevFilter.create(token));
        }

        if (filters.size() == 1) {
            rw.setRevFilter(filters.iterator().next());
        } else if (filters.size() > 1) {
            rw.setRevFilter(AndRevFilter.create(filters));
        }
    }

    private RevCommit getCommit(RevWalk rw, String id) {
        RevCommit commit = null;
        ObjectId commitId = null;
        try {
            LOG.info("Taking commit with id {}", id);
            commitId = ObjectId.fromString(id);
            commit = rw.lookupCommit(commitId);
        } catch (Exception e) {
            LOG.error("Invalid commit id [{}]", id);
        }
        return commit;
    }

    private RevCommit getHead(Repository repo) throws Exception {
        RevWalk rw = new RevWalk(repo);
        ObjectId id = repo.resolve(Constants.HEAD);
        return rw.parseCommit(id);
    }
}
