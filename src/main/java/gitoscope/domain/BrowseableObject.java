package gitoscope.domain;

import java.util.Date;

public class BrowseableObject {

    public BrowseableObject(String id,
                            String name,
                            Date lastModified,
                            String mode,
                            long sizeInBytes,
                            boolean isTree) {
        super();
        this.id = id;
        this.name = name;
        this.lastModified = lastModified;
        this.mode = mode;
        this.sizeInBytes = sizeInBytes;
        this.isTree = isTree;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMode() {
        return mode;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public boolean isTree() {
        return isTree;
    }

    private String id;
    private String name;
    private String mode;
    private Date lastModified;
    private long sizeInBytes;
    private boolean isTree;

}
