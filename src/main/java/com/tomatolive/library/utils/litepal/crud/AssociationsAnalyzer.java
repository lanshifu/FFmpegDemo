package com.tomatolive.library.utils.litepal.crud;

import com.tomatolive.library.utils.litepal.crud.model.AssociationsInfo;
import com.tomatolive.library.utils.litepal.exceptions.LitePalSupportException;
import com.tomatolive.library.utils.litepal.util.DBUtility;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

abstract class AssociationsAnalyzer extends DataHandler {
    AssociationsAnalyzer() {
    }

    /* Access modifiers changed, original: protected */
    public Collection<LitePalSupport> getReverseAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(litePalSupport, associationsInfo.getAssociateSelfFromOtherModel());
    }

    /* Access modifiers changed, original: protected */
    public void setReverseAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo, Collection<LitePalSupport> collection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(litePalSupport, associationsInfo.getAssociateSelfFromOtherModel(), collection);
    }

    /* Access modifiers changed, original: protected */
    public Collection<LitePalSupport> checkAssociatedModelCollection(Collection<LitePalSupport> collection, Field field) {
        Collection arrayList;
        if (isList(field.getType())) {
            arrayList = new ArrayList();
        } else if (isSet(field.getType())) {
            arrayList = new HashSet();
        } else {
            throw new LitePalSupportException("The field to declare many2one or many2many associations should be List or Set.");
        }
        if (collection != null) {
            arrayList.addAll(collection);
        }
        return arrayList;
    }

    /* Access modifiers changed, original: protected */
    public void buildBidirectionalAssociations(LitePalSupport litePalSupport, LitePalSupport litePalSupport2, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(litePalSupport2, associationsInfo.getAssociateSelfFromOtherModel(), litePalSupport);
    }

    /* Access modifiers changed, original: protected */
    public void dealsAssociationsOnTheSideWithoutFK(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        if (litePalSupport2 == null) {
            return;
        }
        if (litePalSupport2.isSaved()) {
            litePalSupport.addAssociatedModelWithFK(litePalSupport2.getTableName(), litePalSupport2.getBaseObjId());
        } else if (litePalSupport.isSaved()) {
            litePalSupport2.addAssociatedModelWithoutFK(litePalSupport.getTableName(), litePalSupport.getBaseObjId());
        }
    }

    /* Access modifiers changed, original: protected */
    public void mightClearFKValue(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) {
        litePalSupport.addFKNameToClearSelf(getForeignKeyName(associationsInfo));
    }

    private String getForeignKeyName(AssociationsInfo associationsInfo) {
        return getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }
}
