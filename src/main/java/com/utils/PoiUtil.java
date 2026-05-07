package com.utils;

import java.util.ArrayList;
import java.util.List;

public class PoiUtil {
    private PoiUtil() {
    }

    public static List<List<String>> poiImport(String url) {
        return new ArrayList<>();
    }

    public static void poiExport(List<List<String>> list, String url) {
        // 当前答辩版本不启用 Excel 导入导出，保留空实现以兼容旧控制器编译。
    }
}
