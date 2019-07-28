package com.tomatolive.library.ui.view.task;

import com.tomatolive.library.model.TaskBoxEntity;
import java.util.ArrayList;
import java.util.List;

public class TaskConstance {
    public boolean isPushInBackground = false;
    private List<TaskBoxEntity> mData = new ArrayList();
    private String opentime = "0";

    private static class LazyHolder {
        private static final TaskConstance INSTANCE = new TaskConstance();

        private LazyHolder() {
        }
    }

    public enum State {
        INIT(0),
        DURING(1),
        RECEIVED(2);
        
        private final int value;

        private State(int i) {
            this.value = i;
        }

        public int value() {
            return this.value;
        }
    }

    public String getOpentime() {
        return this.opentime;
    }

    public void setOpentime(String str) {
        this.opentime = str;
    }

    public List<TaskBoxEntity> getmData() {
        return this.mData;
    }

    public void setmData(List<TaskBoxEntity> list) {
        this.mData = list;
    }

    public void clear() {
        clearList();
        setOpentime("0");
    }

    public void clearList() {
        this.mData.clear();
    }

    public static TaskConstance getInstance() {
        return LazyHolder.INSTANCE;
    }
}
