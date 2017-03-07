package org.sang.a9_3greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.greendao.database.Database;
import org.sang.a9_3greendao.db.DaoMaster;
import org.sang.a9_3greendao.db.DaoSession;
import org.sang.a9_3greendao.db.UserEntityDao;

import java.util.List;

/**
 * 1.引入greenDAO,project的gradle文件和module的gradle文件一共修改四个地方
 * 2.在module的gradle文件中配置数据库版本号、生成代码的位置等参数
 * 3.创建实体类
 * 4.增删改查
 */
public class MainActivity extends AppCompatActivity {

    private UserEntityDao userEntityDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper dbHelper = new DaoMaster.DevOpenHelper(this, "my.db");
        Database db = dbHelper.getWritableDb();
        DaoSession daoSession = new DaoMaster(db).newSession();
        userEntityDao = daoSession.getUserEntityDao();
    }

    public void addData(View view) {
        userEntityDao.save(new UserEntity(null, "zs", "123456"));
        userEntityDao.save(new UserEntity(null, "lisi", "1230456"));
    }

    public void queryData(View view) {
        List<UserEntity> list = userEntityDao.queryBuilder().where(UserEntityDao.Properties.Password.like("%0%")).list();
        if (list != null && list.size() > 0) {
            for (UserEntity userEntity : list) {
                Log.d("google.sang", "queryData: " + userEntity.toString());
            }
        }
    }

    public void deleteData(View view) {
        //删除所有数据
//        userEntityDao.deleteAll();
        List<UserEntity> list = userEntityDao.queryBuilder().where(UserEntityDao.Properties.Username.eq("zs")).list();
        if (list != null && list.size() > 0) {
            for (UserEntity userEntity : list) {
                userEntityDao.delete(userEntity);
            }
        }
    }

    public void updateData(View view) {
        List<UserEntity> list = userEntityDao.queryBuilder().list();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                UserEntity userEntity = list.get(i);
                userEntity.setUsername("王五");
                userEntityDao.update(userEntity);
            }
        }
    }
}
