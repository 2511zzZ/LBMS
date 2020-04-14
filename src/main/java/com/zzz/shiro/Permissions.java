package com.zzz.shiro;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Permissions {

    // admin
    private static String[] adminPermsList = new String[]{
            "druid",
    };
    public static Set<String> adminPerms = new HashSet<>(Arrays.asList(adminPermsList));

    //team
    private static String[] teamPermsList = new String[]{
            "team",
    };
    public static Set<String> teamPerms = new HashSet<>(Arrays.asList(teamPermsList));

    //group
    private static String[] groupPermsList = new String[]{
            "group",
    };
    public static Set<String> groupPerms = new HashSet<>(Arrays.asList(groupPermsList));

    //branch
    private static String[] branchPermsList = new String[]{
            "branch",
    };
    public static Set<String> branchPerms = new HashSet<>(Arrays.asList(branchPermsList));

    //total
    private static String[] totalPermsList = new String[]{
            "total",
            "alarm",
    };
    public static Set<String> totalPerms = new HashSet<>(Arrays.asList(totalPermsList));
}
