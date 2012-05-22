package gitoscope.service;

import gitoscope.domain.BrowseableObject;
import gitoscope.domain.Project;

import java.util.List;

public interface TreeBrowserService {

    List<BrowseableObject> listObjects(Project project, String treeId);

    String getBlobAsText(Project project, String blobId);

}
