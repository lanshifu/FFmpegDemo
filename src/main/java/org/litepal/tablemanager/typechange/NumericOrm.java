package org.litepal.tablemanager.typechange;

public class NumericOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str != null) {
            if (str.equals("int") || str.equals("java.lang.Integer")) {
                return "integer";
            }
            if (str.equals("long") || str.equals("java.lang.Long")) {
                return "integer";
            }
            if (str.equals("short") || str.equals("java.lang.Short")) {
                return "integer";
            }
        }
        return null;
    }
}
