package com.ejoy.tool.common.db.engine;
//  ┏┓　　　┏┓
//┏┛┻━━━┛┻┓
//┃　　　　　　　┃
//┃　　　━　　　┃
//┃　┳┛　┗┳　┃
//┃　　　　　　　┃
//┃　　　┻　　　┃
//┃　　　　　　　┃
//┗━┓　　　┏━┛
//    ┃　　　┃                  神兽保佑
//    ┃　　　┃                  永无BUG！
//    ┃　　　┗━━━┓
//    ┃　　　　　　　┣┓
//    ┃　　　　　　　┏┛
//    ┗┓┓┏━┳┓┏┛
//      ┃┫┫　┃┫┫
//      ┗┻┛　┗┻┛


import com.ejoy.tool.app.App;
import com.ejoy.tool.common.db.CitysBean;
import com.ejoy.tool.greendao.gen.CitysBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * CN:      LocalProblemCombinationDaoHelper
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/12/11
 * Des:    城市省市区数据库帮助类
 */
public class CitiesDaoHelper {
    /**
     * Created by JSYL-DINGCL on 2019/12/11
     * E-Mail：dingcl@jsyl.com.cn
     * 使用GreenDao 实现简单的增删改查，下面是基本方法
     * 增加单个数据
     * getPatrolPlaycardCacheBeanDao().insert(patrolPlaycardCacheBean);
     * getPatrolPlaycardCacheBeanDao().insertOrReplace(patrolPlaycardCacheBean);
     * 增加多个数据
     * getPatrolPlaycardCacheBeanDao().insertInTx(patrolPlaycardCacheBeanList);
     * getPatrolPlaycardCacheBeanDao().insertOrReplaceInTx(patrolPlaycardCacheBeanList);
     * 查询全部
     * List<PatrolPlaycardCacheBean> list = getPatrolPlaycardCacheBeanDao().loadAll();
     * List<PatrolPlaycardCacheBean> list = getPatrolPlaycardCacheBeanDao().queryBuilder().list();
     * 查询附加单个条件
     * .where()
     * .whereOr()
     * 查询附加多个条件
     * .where(, , ,)
     * .whereOr(, , ,)
     * 查询附加排序
     * .orderDesc()
     * .orderAsc()
     * 查询限制当页个数
     * .limit()
     * 查询总个数
     * .count()
     * 修改单个数据
     * getPatrolPlaycardCacheBeanDao().update(patrolPlaycardCacheBeanList);
     * 修改多个数据
     * getPatrolPlaycardCacheBeanDao().updateInTx(patrolPlaycardCacheBeanListList);
     * 删除单个数据
     * getTABUserDao().delete(user);
     * 删除多个数据
     * getUserDao().deleteInTx(userList);
     * 删除数据ByKey
     * getTABUserDao().deleteByKey();
     */


    /**
     * 添加数据，如果有重复则覆盖
     *
     * @param bean
     */
    public static void insertPatrolPCombination(CitysBean bean) {
        App.getDaoSession().getCitysBeanDao().insertOrReplace(bean);
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public static void deletPCombinationCacheById(long id) {
        App.getDaoSession().getCitysBeanDao().deleteByKey(id);
    }

    /**
     * 删除所有数据
     *
     * @param
     */
    public static void deleteAllPCombinationCache() {
        App.getDaoSession().getCitysBeanDao().deleteAll();
    }

    /**
     * 更新数据
     */
    public static void updatePCombinationCache(CitysBean bean) {
        App.getDaoSession().getCitysBeanDao().update(bean);
    }

    /**
     * 查询userid为参数的所有数据
     * @param filterUserIdParam 筛选的字段（userid）
     * @return
     */
    public static List<CitysBean> queryFilterWithId(String filterUserIdParam) {
        return App.getDaoSession().getCitysBeanDao().queryBuilder().where(CitysBeanDao.Properties.C_name.eq(filterUserIdParam)).list();

    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<CitysBean> queryAll() {
        CitysBeanDao citysBeanDao = App.getDaoSession().getCitysBeanDao();
        //清除缓存：不从缓存入手，从数据库拿数据
        citysBeanDao.detachAll();
        List<CitysBean> citysBeans = citysBeanDao.loadAll();
        Collections.sort(citysBeans, new CityComparator());
        return  citysBeans;
    }


    /**
     * 查询所有数据
     *
     * @return
     */
    public static List<CitysBean> queryLevel1() {
        CitysBeanDao localProblemCombinationCacheBeanDao = App.getDaoSession().getCitysBeanDao();
        //清除缓存：不从缓存入手，从数据库拿数据
        localProblemCombinationCacheBeanDao.detachAll();
        return  localProblemCombinationCacheBeanDao.loadAll();
    }




    /**
     * 查询Cname所有数据
     * @param filterMenu1 筛选的字段（menu1）
     * @param filterMenu2 筛选的字段（menu2）
     * @return
     */
//    public static List<CitysBean> queryLevel3FilterWithMenuId(int filterMenu1,int filterMenu2) {
//        CitysBeanDao localProblemCombinationCacheBeanDao = App.getDaoInstant().getCitysBeanDao();
//        localProblemCombinationCacheBeanDao.detachAll();
//        return localProblemCombinationCacheBeanDao
//                .queryBuilder()
//                .where(CitysBeanDao.Properties.Menu1.eq(filterMenu1), CitysBeanDao.Properties.Menu2.eq(filterMenu2))
//                .list();
//
//    }


    /**
     * 查询筛选所有数据
     * @param filterMenu1 筛选的字段（menu1）
     * @param filterMenu2 筛选的字段（menu2）
     * @param filterMenu3 筛选的字段（menu3）
     * @return
     */
    public static List<CitysBean> queryCnameWithId(int filterMenu1, int filterMenu2, int filterMenu3) {
        CitysBeanDao dao = App.getDaoSession().getCitysBeanDao();
        dao.detachAll();
        QueryBuilder<CitysBean> queryBuilder = dao.queryBuilder();
        //一级、二级、三级的id并集查询具体内容
//        WhereCondition Menux = queryBuilder.and(
//                 CitysBeanDao.Properties.C_name.eq(filterMenu1)
//                ,CitysBeanDao.Properties.Menu2.eq(filterMenu2)
//                ,CitysBeanDao.Properties.Menu3.eq(filterMenu3)
//            );
        return queryBuilder.where(CitysBeanDao.Properties.C_name.eq(filterMenu1))
                .list();

    }


    /**
     * 查询搜索框输入内容所有模糊数据
     * @param keyword 筛选的字段（keyword）
     * @return
     */
    public static List<CitysBean> queryCnameWithLike(String keyword) {
        CitysBeanDao dao = App.getDaoSession().getCitysBeanDao();
        dao.detachAll();
        QueryBuilder<CitysBean> queryBuilder = dao.queryBuilder();
        //一级、二级、三级的id并集查询具体内容
        WhereCondition Menux = queryBuilder.or(
                 CitysBeanDao.Properties.C_name.like("%" + keyword + "%")
                ,CitysBeanDao.Properties.C_pinyin.like("%" + keyword + "%")
            );
        List<CitysBean> list = queryBuilder.where(Menux)
                .list();
        Collections.sort(list, new CityComparator());
        return list;

    }

    /**
     * sort by a-z
     */
    private static class CityComparator implements Comparator<CitysBean> {
        @Override
        public int compare(CitysBean lhs, CitysBean rhs) {
            String a = lhs.getC_pinyin().substring(0, 1);
            String b = rhs.getC_pinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }





}
