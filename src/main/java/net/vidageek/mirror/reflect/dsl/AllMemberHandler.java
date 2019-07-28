package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;

public interface AllMemberHandler {
    MirrorList<Annotation> annotations();

    @Deprecated
    List<Annotation> annotationsMatching(Matcher<Annotation> matcher);
}
