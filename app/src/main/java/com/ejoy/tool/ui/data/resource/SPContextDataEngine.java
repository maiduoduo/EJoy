package com.ejoy.tool.ui.data.resource;

import android.content.Context;
import android.text.TextUtils;

import com.ejoy.tool.scaffold.utils.SharePrefUtil;


/**
 * CN:      SPContextDataEngine
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/10/17
 * Des:    SharedPrefence全局数据辅助
 */
public class SPContextDataEngine {
    private static SPContextDataEngine engine;
    private static Context context;
    public static SPContextDataEngine getInstance(Context paramContext) {
        context = paramContext;
        synchronized(SPContextDataEngine.class) {
            if (engine == null) {
                engine = new SPContextDataEngine();
            }

            return engine;
        }
    }


    /**
     * 保存UserName
     * @return
     */
    public void putUserName(String userName) {
        if (userName != null && !TextUtils.isEmpty(userName))
            SharePrefUtil.putString(context, "UserName", userName);
        else
            SharePrefUtil.putString(context, "UserName", "");
    }

    /**
     * 获取UserName
     * @return
     */
    public String getUserName() {
        String str = SharePrefUtil.getString(context, "UserName", "");
        return str;
    }

    /**
     * 保存UserId
     * @return
     */
    public void putUserId(String userid) {
        if (userid != null && !TextUtils.isEmpty(userid))
            SharePrefUtil.putString(context, "UserId", userid);
        else
            SharePrefUtil.putString(context, "UserId", "");
    }

    /**
     * 获取USERID
     * @return
     */
    public String getUserId() {
        String str = SharePrefUtil.getString(context, "UserId", "");
        return str;
    }




    /**
     * 保存orgType
     * @return
     */
    public void putOrgType(String orgType) {
        if (orgType != null && !TextUtils.isEmpty(orgType))
            SharePrefUtil.putString(context, "OrgType", orgType);
        else
            SharePrefUtil.putString(context, "OrgType", "");
    }

    /**
     * 获取USERID
     * @return
     */
    public String getOrgType() {
        String str = SharePrefUtil.getString(context, "OrgType", "");
        return str;
    }




    /**
     * 保存postname
     * @return
     */
    public void putPostName(String postname) {
        if (postname != null && !TextUtils.isEmpty(postname))
            SharePrefUtil.putString(context, "PostName", postname);
        else
            SharePrefUtil.putString(context, "PostName", "");
    }

    /**
     * 获取postname
     * @return
     */
    public String getPostName() {
        String str = SharePrefUtil.getString(context, "PostName", "");
        return str;
    }


    /**
     * 保存OrgId
     * @return
     */
    public void putOrgId(String orgId) {
        if (orgId != null && !TextUtils.isEmpty(orgId))
            SharePrefUtil.putString(context, "OrgId", orgId);
        else
            SharePrefUtil.putString(context, "OrgId", "");
    }

    /**
     * 获取OrgId
     * @return
     */
    public String getOrgId() {
        String str = SharePrefUtil.getString(context, "OrgId", "");
        return str;
    }


    /**
     * 保存orgName
     * @return
     */
    public void putOrgName(String orgName) {
        if (orgName != null && !TextUtils.isEmpty(orgName))
            SharePrefUtil.putString(context, "OrgName", orgName);
        else
            SharePrefUtil.putString(context, "OrgName", "");
    }

    /**
     * 获取orgName
     * @return
     */
    public String getOrgName() {
        String str = SharePrefUtil.getString(context, "OrgName", "");
        return str;
    }



    /**
     * 保存DirectlyId
     * @return
     */
    public void putDirectlyId(String directlyId) {
        if (directlyId != null && !TextUtils.isEmpty(directlyId))
            SharePrefUtil.putString(context, "DirectlyId", directlyId);
        else
            SharePrefUtil.putString(context, "DirectlyId", "");
    }

    /**
     * 获取DirectlyId
     * @return
     */
    public String getDirectlyId() {
        String str = SharePrefUtil.getString(context, "DirectlyId", "");
        return str;
    }


    /**
     * 保存DirectlyName
     * @return
     */
    public void putDirectlyName(String orgName) {
        if (orgName != null && !TextUtils.isEmpty(orgName))
            SharePrefUtil.putString(context, "DirectlyName", orgName);
        else
            SharePrefUtil.putString(context, "DirectlyName", "");
    }

    /**
     * 获取DirectlyName
     * @return
     */
    public String getDirectlyName() {
        String str = SharePrefUtil.getString(context, "DirectlyName", "");
        return str;
    }



    /**
     * 保存SessionId
     * @return
     */
    public void putSessionId(String sessionId) {
        if (sessionId != null && !TextUtils.isEmpty(sessionId))
            SharePrefUtil.putString(context, "SessionId", sessionId);
        else
            SharePrefUtil.putString(context, "SessionId", "");
    }

    /**
     * 获取SessionId
     * @return
     */
    public String getSessionId() {
        String str = SharePrefUtil.getString(context, "SessionId", "");
        return str;
    }



    /**
     * 保存Voip
     * @return
     */
    public void putVoip(String voip) {
        if (voip != null && !TextUtils.isEmpty(voip))
            SharePrefUtil.putString(context, "Voip", voip);
        else
            SharePrefUtil.putString(context, "Voip", "");
    }

    /**
     * 获取Voip
     * @return
     */
    public String getVoip() {
        String str = SharePrefUtil.getString(context, "Voip", "");
        return str;
    }




    /**
     * 保存RoleName
     * @return
     */
    public void putRoleName(String roleName) {
        if (roleName != null && !TextUtils.isEmpty(roleName))
            SharePrefUtil.putString(context, "RoleName", roleName);
        else
            SharePrefUtil.putString(context, "RoleName", "");
    }

    /**
     * 获取RoleName
     * @return
     */
    public String getRoleName() {
        String str = SharePrefUtil.getString(context, "RoleName", "");
        return str;
    }



    /**
     * 保存Account
     * @return
     */
    public void putAccount(String account) {
        if (account != null && !TextUtils.isEmpty(account))
            SharePrefUtil.putString(context, "Account", account);
        else
            SharePrefUtil.putString(context, "Account", "");
    }

    /**
     * 获取Account
     * @return
     */
    public String getAccount() {
        String str = SharePrefUtil.getString(context, "Account", "");
        return str;
    }


    /**
     * 保存用户姓名
     * @return
     */
    public void putName(String name) {
        if (name != null && !TextUtils.isEmpty(name))
            SharePrefUtil.putString(context, "LName", name);
        else
            SharePrefUtil.putString(context, "LName", "");
    }

    /**
     * 获取用户姓名
     * @return
     */
    public String getName() {
        String str = SharePrefUtil.getString(context, "LName", "");
        return str;
    }



    /**
     * 保存记住密码状态
     * @return
     */
    public void putRememberPass(boolean remPass) {
        if (String.valueOf(remPass) != null){
            SharePrefUtil.putBoolean(context, "LRememberPass", remPass);
        }else {
            SharePrefUtil.putBoolean(context, "LRememberPass", false);
        }
    }

    /**
     * 获取记住密码状态
     * @return
     */
    public boolean getRememberPass() {
        boolean b = SharePrefUtil.getBoolean(context, "LRememberPass", false);
        return b;
    }


    /**
     * 保存记住密码状态
     * @return
     */
    public void putUserPassword(String password) {
        if (password != null && !TextUtils.isEmpty(password)){
            SharePrefUtil.putString(context, "LPassword", password);
        }else {
            SharePrefUtil.putString(context, "LPassword", "");
        }
    }

    /**
     * 获取记住密码状态
     * @return
     */
    public String getUserPassword() {
        String s = SharePrefUtil.getString(context, "LPassword", "");
        return s;
    }



    /**
     * 打卡时卡片对应岗点id
     *      保存
     * @return
     */
    public void savePointId(String NfcPointId) {
        SharePrefUtil.putString(context, "NfcPointId", NfcPointId);
    }

    /**
     * 打卡时卡片对应岗点id
     *        获取
     * @return
     */
    public String getPointId() {
        String str = SharePrefUtil.getString(context, "NfcPointId", "");
        return str;
    }

    /**
     * 打卡时卡片对应岗点name
     *    获取
     * @return
     */
    public String getPointName() {
        String str = SharePrefUtil.getString(context, "NfcPointName", "");
        return str;
    }

    /**
     * 打卡时卡片对应岗点name
     *    保存
     * @return
     */
    public void savePointName(String NfcPointName) {
        SharePrefUtil.putString(context, "NfcPointName", NfcPointName);
    }

    public String getInPlan() {
        String str = SharePrefUtil.getString(context, "NfcInPlan", "");
        return str;
    }

    public void saveInPlan(String NfcInPlan) {
        SharePrefUtil.putString(context, "NfcInPlan", NfcInPlan);
    }


    /**
     * 是否首次启动
     * @param isFirst 是否首次
     */
    public void saveGIIsFrist(int isFirst) {
        SharePrefUtil.putInt(context, "GIIsFirst", isFirst);
    }


    /**
     * 获取是否第一次
     * @return
     */
    public int getGIIsFrist() {
        int isFirst1 = SharePrefUtil.getInt(context, "GIIsFirst", 0);
        return isFirst1;
    }

}
