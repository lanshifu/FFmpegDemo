package com.tomatolive.library.model.event;

public class SearchEvent extends BaseEvent {
    public String keyword;

    public SearchEvent(String str) {
        this.keyword = str;
    }
}
