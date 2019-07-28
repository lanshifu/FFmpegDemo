package net.lucode.hackware.magicindicator;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

/* compiled from: ViewPagerHelper */
public class c {
    public static void a(final MagicIndicator magicIndicator, ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
                magicIndicator.a(i, f, i2);
            }

            public void onPageSelected(int i) {
                magicIndicator.a(i);
            }

            public void onPageScrollStateChanged(int i) {
                magicIndicator.b(i);
            }
        });
    }
}
