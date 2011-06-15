package gitoscope.service;

import java.util.List;
import java.util.Map;
import org.eclipse.jgit.lib.*;
import gitoscope.domain.*;

public interface CommitService {

	Commit findById(Project project, String id);

	List<Commit> searchCommits(Project project,
							   int maxResults,
							   Map<String, String> filter);
}
