package defpackage;

import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import com.one.tomato.entity.ImageBean;
import com.one.tomato.entity.PostList;
import com.one.tomato.entity.Tag;
import com.one.tomato.entity.db.UserInfo;
import com.one.tomato.mvp.base.BaseApplication;
import com.one.tomato.utils.c;
import com.one.tomato.utils.g;
import com.one.tomato.utils.j;
import com.one.tomato.utils.k;
import java.io.File;
import java.util.ArrayList;

/* compiled from: PostUtil */
/* renamed from: qc */
public class qc {
    public static ArrayList<ImageBean> a(PostList postList) {
        ArrayList arrayList = new ArrayList();
        if (postList == null) {
            return arrayList;
        }
        String[] strArr;
        int i = 0;
        if (TextUtils.isEmpty(postList.getSecImageUrl())) {
            strArr = new String[0];
        } else {
            strArr = postList.getSecImageUrl().split(";");
        }
        while (i < strArr.length) {
            String str = strArr[i];
            ImageBean imageBean = new ImageBean();
            imageBean.setSecret(true);
            imageBean.setAd(postList.isAd());
            imageBean.setImage(str);
            arrayList.add(imageBean);
            i++;
        }
        return arrayList;
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            int intValue = Integer.valueOf(str.split("\\*")[0]).intValue();
            int intValue2 = Integer.valueOf(str.split("\\*")[1]).intValue();
            if (intValue > intValue2) {
                return 1;
            }
            return intValue * 3 < intValue2 ? 3 : 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean b(PostList postList) {
        if (TextUtils.isEmpty(postList.getSize())) {
            return false;
        }
        int parseInt;
        int parseInt2;
        String[] split = postList.getSize().split("\\*");
        try {
            parseInt = Integer.parseInt(split[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            parseInt = 0;
        }
        try {
            parseInt2 = Integer.parseInt(split[1]);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            parseInt2 = 0;
        }
        if (parseInt >= parseInt2) {
            return true;
        }
        return false;
    }

    public static boolean a(ArrayList<Tag> arrayList) {
        Paint paint = new Paint();
        Rect rect = new Rect();
        int b = (int) (j.b() - j.a(60.0f));
        int a = (int) j.a(20.0f);
        int a2 = (int) j.a(20.0f);
        j.a(40.0f);
        boolean z = false;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            String tagName = ((Tag) arrayList.get(i3)).getTagName();
            paint.getTextBounds(tagName, 0, tagName.length(), rect);
            i += (((int) j.a((float) rect.width())) + a) + a2;
            if (i > b) {
                break;
            }
            i2 = i3;
        }
        z = true;
        return i2 == arrayList.size() - 1 ? true : z;
    }

    public static String a(int i) {
        if (i <= 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(i);
        stringBuilder2.append("");
        stringBuilder.append(pu.a(stringBuilder2.toString()));
        stringBuilder.append(i);
        return stringBuilder.toString();
    }

    public static void c(PostList postList) {
        if (postList != null) {
            File file = new File(k.g(), qc.a(postList.getId()));
            k.a(file.getPath(), BaseApplication.b().toJson(postList));
        }
    }

    public static PostList b(int i) {
        File file = new File(k.g(), qc.a(i));
        if (!file.exists()) {
            return null;
        }
        String c = k.c(file.getPath());
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        return (PostList) BaseApplication.b().fromJson(c, PostList.class);
    }

    public static void b(String str) {
        UserInfo e = g.e();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pd.a().e());
        stringBuilder.append("?inviteCode=");
        stringBuilder.append(e.getInviteCode());
        stringBuilder.append("&id=");
        stringBuilder.append(str);
        str = stringBuilder.toString();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(c.a(2131756291));
        stringBuilder2.append(str);
        c.b(stringBuilder2.toString(), c.a(2131756231));
    }
}
