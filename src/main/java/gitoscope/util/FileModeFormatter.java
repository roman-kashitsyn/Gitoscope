package gitoscope.util;

import org.eclipse.jgit.lib.FileMode;

public class FileModeFormatter {

    public static String format(FileMode mode) {
        StringBuilder buf = new StringBuilder();
        int bits = mode.getBits();
        buf.append(getModeChar(bits));
        return buf.toString();
    }

    private static char getModeChar(int bits) {
        switch (bits & FileMode.TYPE_MASK) {
            case FileMode.TYPE_TREE:
                return 'd';
            case FileMode.TYPE_FILE:
                return '-';
            case FileMode.TYPE_SYMLINK:
                return 's';
            case FileMode.TYPE_GITLINK:
                return 'g';
            case FileMode.TYPE_MISSING:
                return 'm';
            default:
                return '?';
        }
    }

}
