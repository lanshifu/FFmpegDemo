package defpackage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: IndexBarDataHelperImpl */
/* renamed from: ng */
public class ng implements nf {

    /* compiled from: IndexBarDataHelperImpl */
    /* renamed from: ng$1 */
    class 1 implements Comparator<ne> {
        1() {
        }

        /* renamed from: a */
        public int compare(ne neVar, ne neVar2) {
            if (!neVar.isNeedToPinyin() || !neVar2.isNeedToPinyin()) {
                return 0;
            }
            if (neVar.getBaseIndexTag().equals("#")) {
                return 1;
            }
            if (neVar2.getBaseIndexTag().equals("#")) {
                return -1;
            }
            return neVar.getBaseIndexPinyin().compareTo(neVar2.getBaseIndexPinyin());
        }
    }

    public nf a(List<? extends ne> list) {
        if (list == null || list.isEmpty()) {
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ne neVar = (ne) list.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            if (neVar.isNeedToPinyin()) {
                String target = neVar.getTarget();
                for (int i2 = 0; i2 < target.length(); i2++) {
                    stringBuilder.append(ge.a(target.charAt(i2)).toUpperCase());
                }
                neVar.setBaseIndexPinyin(stringBuilder.toString());
            }
        }
        return this;
    }

    public nf b(List<? extends ne> list) {
        if (list == null || list.isEmpty()) {
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ne neVar = (ne) list.get(i);
            if (neVar.isNeedToPinyin()) {
                String substring = neVar.getBaseIndexPinyin().toString().substring(0, 1);
                if (substring.matches("[A-Z]")) {
                    neVar.setBaseIndexTag(substring);
                } else {
                    neVar.setBaseIndexTag("#");
                }
            }
        }
        return this;
    }

    public nf c(List<? extends ne> list) {
        if (list == null || list.isEmpty()) {
            return this;
        }
        a(list);
        b(list);
        Collections.sort(list, new 1());
        return this;
    }

    public nf a(List<? extends ne> list, List<String> list2) {
        if (list == null || list.isEmpty()) {
            return this;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String baseIndexTag = ((ne) list.get(i)).getBaseIndexTag();
            if (!list2.contains(baseIndexTag)) {
                list2.add(baseIndexTag);
            }
        }
        return this;
    }
}
