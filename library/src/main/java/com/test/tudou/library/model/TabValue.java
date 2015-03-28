package com.test.tudou.library.model;

/**
 * Created by tudou on 15-3-28.
 */
public class TabValue {

    public int selectIcon;
    public int normalIcon;
    public int selectColor;
    public int normalColor;
    public String title;

    public TabValue() {}

    public TabValue(int selectIcon, int normalIcon, int selectColor, int normalColor, String title) {
        this.selectIcon = selectIcon;
        this.normalIcon = normalIcon;
        this.selectColor = selectColor;
        this.normalColor = normalColor;
        this.title = title;
    }

}
