package com.zzz.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Permissions {


    private static String[] adminPermsList = new String[]{
            "druid",
    };
    public static Set<String> adminPerms = new HashSet<>(Arrays.asList(adminPermsList));

}
