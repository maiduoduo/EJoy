package com.ejoy.tool.ui.activity.permission;

import java.util.List;

/**
 * 类  名 :  OnPermissionsResult
 * 权限回调
 */

public interface OnPermissionsResult {
    void onAllow(List<String> allowPermissions);

    void onNoAllow(List<String> noAllowPermissions);

    void onForbid(List<String> noForbidPermissions);

    void onLowVersion();
}
