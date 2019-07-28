package org.litepal.parser;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.litepal.exceptions.InvalidAttributesException;
import org.litepal.util.BaseUtility;
import org.litepal.util.SharedUtil;

public final class LitePalAttr {
    private static LitePalAttr litePalAttr;
    private String cases;
    private List<String> classNames;
    private String dbName;
    private String extraKeyName;
    private String storage;
    private int version;

    private LitePalAttr() {
    }

    public static LitePalAttr getInstance() {
        if (litePalAttr == null) {
            synchronized (LitePalAttr.class) {
                if (litePalAttr == null) {
                    litePalAttr = new LitePalAttr();
                    loadLitePalXMLConfiguration();
                }
            }
        }
        return litePalAttr;
    }

    private static void loadLitePalXMLConfiguration() {
        if (BaseUtility.isLitePalXMLExists()) {
            LitePalConfig parseLitePalConfiguration = LitePalParser.parseLitePalConfiguration();
            litePalAttr.setDbName(parseLitePalConfiguration.getDbName());
            litePalAttr.setVersion(parseLitePalConfiguration.getVersion());
            litePalAttr.setClassNames(parseLitePalConfiguration.getClassNames());
            litePalAttr.setCases(parseLitePalConfiguration.getCases());
            litePalAttr.setStorage(parseLitePalConfiguration.getStorage());
        }
    }

    public static void clearInstance() {
        litePalAttr = null;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public String getDbName() {
        return this.dbName;
    }

    public void setDbName(String str) {
        this.dbName = str;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String str) {
        this.storage = str;
    }

    public String getExtraKeyName() {
        return this.extraKeyName;
    }

    public void setExtraKeyName(String str) {
        this.extraKeyName = str;
    }

    public List<String> getClassNames() {
        if (this.classNames == null) {
            this.classNames = new ArrayList();
            this.classNames.add("org.litepal.model.Table_Schema");
        } else if (this.classNames.isEmpty()) {
            this.classNames.add("org.litepal.model.Table_Schema");
        }
        return this.classNames;
    }

    public void addClassName(String str) {
        getClassNames().add(str);
    }

    public void setClassNames(List<String> list) {
        this.classNames = list;
    }

    public String getCases() {
        return this.cases;
    }

    public void setCases(String str) {
        this.cases = str;
    }

    public void checkSelfValid() {
        if (TextUtils.isEmpty(this.dbName)) {
            loadLitePalXMLConfiguration();
            if (TextUtils.isEmpty(this.dbName)) {
                throw new InvalidAttributesException(InvalidAttributesException.DBNAME_IS_EMPTY_OR_NOT_DEFINED);
            }
        }
        if (!this.dbName.endsWith(".db")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.dbName);
            stringBuilder.append(".db");
            this.dbName = stringBuilder.toString();
        }
        if (this.version < 1) {
            throw new InvalidAttributesException("the version of database can not be less than 1");
        } else if (this.version < SharedUtil.getLastVersion(this.extraKeyName)) {
            throw new InvalidAttributesException("the version in litepal.xml is earlier than the current version");
        } else if (TextUtils.isEmpty(this.cases)) {
            this.cases = "lower";
        } else if (!this.cases.equals("upper") && !this.cases.equals("lower") && !this.cases.equals("keep")) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(this.cases);
            stringBuilder2.append(" is an invalid value for <cases></cases>");
            throw new InvalidAttributesException(stringBuilder2.toString());
        }
    }
}
