package gitoscope.service;

import org.eclipse.jgit.lib.*;
import gitoscope.domain.*;

public interface CommitService {

	Commit findById(Project project, String id);

}
