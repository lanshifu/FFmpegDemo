package org.litepal.tablemanager.typechange;

public class BooleanOrm extends OrmChange {
    public String object2Relation(String str) {
        return (str == null || !(str.equals("boolean") || str.equals("java.lang.Boolean"))) ? null : "integer";
    }
}
