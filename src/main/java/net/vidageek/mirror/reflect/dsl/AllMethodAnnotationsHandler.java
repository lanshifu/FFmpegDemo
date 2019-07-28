package net.vidageek.mirror.reflect.dsl;

import java.lang.annotation.Annotation;
import java.util.List;

public interface AllMethodAnnotationsHandler {
    List<Annotation> withArgs(Class<?>... clsArr);

    List<Annotation> withoutArgs();
}
