package net.vidageek.mirror.dsl;

import net.vidageek.mirror.reflect.dsl.AllMemberHandler;
import net.vidageek.mirror.reflect.dsl.MemberHandler;

public interface MemberController {
    MemberHandler reflect();

    AllMemberHandler reflectAll();
}
