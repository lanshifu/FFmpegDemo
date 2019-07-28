package com.tomatolive.library.http;

import java.util.List;

public class HttpResultPageModel<T> {
    public List<T> dataList;
    public int pageNumber = 1;
    public int pageSize = 0;
    public String totalGold = "0";
    public int totalPageCount = 1;
    public int totalRowsCount = 0;

    public boolean isMorePage() {
        return this.totalPageCount == 0 || this.pageNumber == 0 || this.pageNumber >= this.totalPageCount;
    }

    public boolean isNoEmptyData() {
        return this.totalRowsCount > 0;
    }
}
