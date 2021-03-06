package gitoscope.service.impl;

import gitoscope.domain.BrowseableObject;
import gitoscope.domain.Project;
import gitoscope.exception.BlobNotFoundException;
import gitoscope.exception.TreeNotFoundException;
import gitoscope.service.TreeBrowserService;
import gitoscope.util.FileModeFormatter;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TreeWalkBrowserService implements TreeBrowserService {

    private static final Logger LOG =
            LoggerFactory.getLogger(TreeWalkBrowserService.class);

    public List<BrowseableObject> listObjects(Project project, String treeId) {
        AnyObjectId treeObjId;
        try {
            treeObjId = ObjectId.fromString(treeId);
        } catch (Exception e) {
            LOG.error("Invalid tree id [{}]", treeId);
            throw new TreeNotFoundException(project.getName(), treeId).initCause(e);
        }

        TreeWalk walk = new TreeWalk(project.getRepository());
        List<BrowseableObject> objects = new ArrayList<BrowseableObject>();

        try {
            walk.addTree(treeObjId);

            ObjectId objId;
            ObjectLoader objLoader;

            while (walk.next()) {
                objId = walk.getObjectId(0);
                objLoader = walk.getObjectReader().open(objId);
                objects.add(
                        new BrowseableObject(
                                ObjectId.toString(objId),
                                walk.getNameString(),
                                new Date(),
                                FileModeFormatter.format(walk.getFileMode(0)),
                                objLoader.getSize(),
                                walk.isSubtree()
                        )
                );
            }
        } catch (Exception e) {
            LOG.error("Tree [{}] not found in project {}", treeId, project.getName());
            throw new TreeNotFoundException(project.getName(), treeId).initCause(e);
        }

        return objects;
    }

    public String getBlobAsText(Project project, String blobId) {
        AnyObjectId blob;
        try {
            blob = ObjectId.fromString(blobId);
        } catch (Exception e) {
            LOG.error("Invalid blob id [{}]", blobId);
            throw new BlobNotFoundException(project.getName(), blobId).initCause(e);
        }

        Repository repo = project.getRepository();

        try {
            byte[] bytes = repo.open(blob).getCachedBytes();
            return new String(bytes);
        } catch (Exception e) {
            LOG.error("Blob [{}] not found in project {}", blobId, project.getName());
            throw new BlobNotFoundException(project.getName(), blobId).initCause(e);
        }
    }
}
