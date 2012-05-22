package gitoscope.service;

import java.io.File;
import java.util.Collection;

/**
 * Interface {@code ConfigurationService} provides basic
 * configuration facilities.
 * @author Roman Kashitsyn
 */
public interface ConfigurationService {

    /**
     * Returns collection of all configured paths where projects
     * can be found.
     * @return collection of all configured project paths
     */
    Collection<File> listConfiguredProjectPaths();

}
