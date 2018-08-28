package com.chan.xcene.test;

import com.chan.xcene.annotations.Layout;
import com.chan.xcene.background.Task;
import com.chan.xcene.controller.BaseController;
import javafx.application.Platform;
import javafx.stage.WindowEvent;

@Layout("TestScene")
public class TestController extends BaseController {
    @Override
    @SuppressWarnings("unchecked")
    public void onShown(WindowEvent event) {
        System.out.println("Hey I have shown");

        Task t = new Task() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public Object doInBackground(Object[] var1) {
                while(true) {
                    System.out.println("hello");

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        break;
                    }
                }
                return null;
            }

            @Override
            public void onPostExecute(Object[] var1) {

            }

            @Override
            public void progressCallback(Object[] var1) {

            }
        };

        t.execute();
    }

    @Override
    public void onClose(WindowEvent event) {
//        Platform.exit();
//        System.exit(0);
    }
}
