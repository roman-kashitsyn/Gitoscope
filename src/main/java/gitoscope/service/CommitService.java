package gitoscope.service;

import gitoscope.domain.Commit;
import gitoscope.domain.Project;

import java.util.List;
import java.util.Map;

public interface CommitService {

    Commit findById(Project project, String id);

    List<Commit> searchCommits(Project project,
                               int maxResults,
                               Map<String, String> filter);
}
