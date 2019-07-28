package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

public interface AllAnnotationsHandler {
    List<Annotation> atClass();

    List<Annotation> atField(String str);

    AllMethodAnnotationsHandler atMethod(String str);
}
