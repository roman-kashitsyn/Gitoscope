package gitoscope.service;

import java.util.List;
import gitoscope.domain.*;

public interface TreeBrowserService {

	List<BrowseableObject> listObjects(Project project, String treeId);

	String getBlobAsText(Project project, String blobId);

}
