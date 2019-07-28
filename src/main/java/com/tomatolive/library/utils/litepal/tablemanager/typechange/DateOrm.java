package com.tomatolive.library.utils.litepal.tablemanager.typechange;

public class DateOrm extends OrmChange {
    public String object2Relation(String str) {
        return (str == null || !str.equals("java.util.Date")) ? null : "integer";
    }
}
