//package com.mycompany.aem.mycustomproject.core.beans;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class NavigationItem {
//    private String title;
//    private String path;
//    private List<NavigationItem> children = new ArrayList<>();
//    private boolean hideInNav;
//
//    public NavigationItem() {}
//
//    public NavigationItem(String title, String path, List<NavigationItem> children) {
//        this.title = title;
//        this.path = path;
//        this.children = children;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPath() {
//        return path;
//    }
//
//    public void setPath(String path) {
//        this.path = path;
//    }
//
//    public List<NavigationItem> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<NavigationItem> children) {
//        this.children = children;
//    }
//
//    public boolean hasChildren() {
//        return children != null && !children.isEmpty();
//    }
//
//    public void addChild(NavigationItem child) {
//        if (this.children == null) {
//            this.children = new ArrayList<>();
//        }
//        this.children.add(child);
//    }
//
//    public boolean isHideInNav() {
//        return hideInNav;
//    }
//
//    public void setHideInNav(boolean hideInNav) {
//        this.hideInNav = hideInNav;
//    }
//}



package com.mycompany.aem.mycustomproject.core.beans;

import java.util.List;

public class NavigationItem {
    private String title;
    private String path;
    private List<NavigationItem> children;

    public NavigationItem(String title, String path, List<NavigationItem> children) {
        this.title = title;
        this.path = path;
        this.children = children;
    }

    public String getTitle() { return title; }
    public String getPath() { return path; }
    public List<NavigationItem> getChildren() { return children; }
}
