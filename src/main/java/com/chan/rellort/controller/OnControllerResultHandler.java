package com.chan.rellort.controller;

/**
 * Controller result handler interface
 */
public interface OnControllerResultHandler {
    /**
     * Result handler called before controller close to pass result to parent controller
     * @param results parameter array of Object results.
     */
    void onResult(Object ...results);
}
