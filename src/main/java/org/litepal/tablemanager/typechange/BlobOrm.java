package org.litepal.tablemanager.typechange;

public class BlobOrm extends OrmChange {
    public String object2Relation(String str) {
        return (str == null || !str.equals("[B")) ? null : "blob";
    }
}
