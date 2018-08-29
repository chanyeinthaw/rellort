package com.chan.rellort.background;

import javafx.application.Platform;

public abstract class Task<BackgroundParam, ProgressParam, PostExecuteParam> {
    private boolean daemon = true;
    private BackgroundParam[] params;
    public final Thread backGroundThread = new Thread(new Runnable() {
        public void run() {
            PostExecuteParam param = Task.this.doInBackground(Task.this.params);
            Platform.runLater(() -> {
                Task.this.onPostExecute(param);
            });
        }
    });

    public abstract void onPreExecute();

    public abstract PostExecuteParam doInBackground(BackgroundParam... var1);

    public abstract void onPostExecute(PostExecuteParam... var1);

    public abstract void progressCallback(ProgressParam... var1);

    public void publishProgress(ProgressParam... params) {
        Platform.runLater(() -> {
            this.progressCallback(params);
        });
    }

    public void execute(BackgroundParam... params) {
        this.params = params;
        Platform.runLater(() -> {
            this.onPreExecute();
            this.backGroundThread.setDaemon(this.daemon);
            this.backGroundThread.start();
        });
    }

    public void setDaemon(boolean daemon) {
        this.daemon = daemon;
    }

    public final boolean isInterrupted() {
        return this.backGroundThread.isInterrupted();
    }

    public final boolean isAlive() {
        return this.backGroundThread.isAlive();
    }
}
