package defpackage;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.luck.picture.lib.permissions.b;
import com.one.tomato.entity.PostList;
import com.one.tomato.entity.db.AdPage;
import com.one.tomato.utils.ab;
import com.one.tomato.utils.ad;
import com.one.tomato.utils.c;
import com.tomatolive.library.websocket.nvwebsocket.ConnectSocketParams;
import io.reactivex.r;
import java.util.List;

/* compiled from: AdUtil */
/* renamed from: ps */
public class ps {
    private Activity a;
    private boolean b;

    public ps(@Nullable Activity activity) {
        this.a = activity;
    }

    public static int a(List<AdPage> list) {
        int i = 0;
        if (list == null || list.size() == 0) {
            return 0;
        }
        Double valueOf = Double.valueOf(0.0d);
        for (AdPage weight : list) {
            double weight2;
            try {
                weight2 = (double) weight.getWeight();
            } catch (Exception e) {
                e.printStackTrace();
                weight2 = 0.0d;
            }
            valueOf = Double.valueOf(valueOf.doubleValue() + weight2);
        }
        double random = Math.random();
        double d = 0.0d;
        double d2 = d;
        int i2 = 0;
        while (i2 < list.size()) {
            double weight3;
            try {
                weight3 = (double) ((AdPage) list.get(i2)).getWeight();
            } catch (Exception e2) {
                e2.printStackTrace();
                weight3 = 0.0d;
            }
            d += weight3 / valueOf.doubleValue();
            if (i2 == 0) {
                d2 = 0.0d;
            } else {
                try {
                    weight3 = (double) ((AdPage) list.get(i2 - 1)).getWeight();
                } catch (Exception e22) {
                    e22.printStackTrace();
                    weight3 = 0.0d;
                }
                d2 += weight3 / valueOf.doubleValue();
            }
            if (d2 <= random && random < d) {
                break;
            }
            i2++;
        }
        i2 = 0;
        if (i2 < list.size()) {
            i = i2;
        }
        return i;
    }

    public static PostList a(AdPage adPage) {
        if (adPage == null) {
            return null;
        }
        PostList postList = new PostList();
        postList.setPage(adPage);
        postList.setPostType(9);
        postList.setId(adPage.getAdId());
        postList.setAvatar(adPage.getAdArticleAvatarSec());
        postList.setName(adPage.getAdArticleName());
        postList.setDescription(adPage.getAdArticleContent());
        postList.setSecImageUrl(adPage.getImageUrlSec());
        postList.setSecVideoCover(adPage.getVideoCoverUrlSec());
        postList.setSecVideoUrl(adPage.getVideoUrlSec());
        postList.setRandom(adPage.getRandom());
        postList.setVideoTime(adPage.getVideoTime());
        postList.setSize(adPage.getSize());
        return postList;
    }

    public void a(boolean z, int i, String str, String str2, String str3, String str4) {
        this.b = z;
        switch (i) {
            case 1:
                if (!"1".equals(str)) {
                    if (ConnectSocketParams.EFFECT_TYPE_BIG.equals(str) && this.a != null) {
                        ab.a(this.a, str2, 6);
                        break;
                    }
                } else if (this.a != null) {
                    ab.a(this.a, str2, 2);
                    break;
                }
                break;
            case 2:
                if (!TextUtils.isEmpty(str4)) {
                    if (!str4.endsWith(".apk")) {
                        c.b(str4);
                        break;
                    } else {
                        a(str4);
                        break;
                    }
                }
                return;
        }
    }

    private void a(final String str) {
        if (this.a != null) {
            new b(this.a).b(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}).subscribe(new r<Boolean>() {
                public void onComplete() {
                }

                public void onError(Throwable th) {
                }

                public void onSubscribe(io.reactivex.disposables.b bVar) {
                }

                /* renamed from: a */
                public void onNext(Boolean bool) {
                    if (bool.booleanValue()) {
                        pr.a().a(str);
                    } else {
                        ad.a(2131756133);
                    }
                }
            });
        }
    }
}
