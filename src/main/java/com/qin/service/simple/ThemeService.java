package com.qin.service.simple;

import com.github.pagehelper.PageInfo;
import com.qin.model.simple.Theme;
import com.qin.model.simple.ThemeExample;

import java.util.List;

public interface ThemeService {

    public boolean addTheme(Theme theme);

    public boolean editTheme(Theme theme);

    public Theme findThemeById(Integer themeId);

    List<Theme> selectByExample(ThemeExample example);

    List<Theme> select();

    public List<Theme> findThemeByKeywords(String keywords);

    public PageInfo<Theme> findThemeByPage(Integer pageNum, String keywords);

    public PageInfo<Theme> findThemeByPage1(Integer pageNum, String keywords);

    public PageInfo<Theme> findThemeByPage2(Integer pageNum, String keywords);

}