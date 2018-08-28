package com.chan.xcene;

import java.net.URL;

/**
 * Resource Loader
 * load resource files under resources
 */
public class ResLoader {
    /**
     * Get resource file of given path
     * @param path String path
     * @return URL of requested resource file
     */
    public static URL getResource(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}
