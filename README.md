# GreenDAO
GreenDAO是一个将对象映射到SQLite数据库中的轻量且快速的ORM解决方案

<p/>技术要点：
/**
 * 1.引入greenDAO,project的gradle文件和module的gradle文件一共修改四个地方
 * 2.在module的gradle文件中配置数据库版本号、生成代码的位置等参数
 * 3.创建实体类
 * 4.增删改查
 */


<p/>project的build.gradle文件添加

<p/>buildscript {
 <p/>   repositories {
    <p/>    mavenCentral()
  <p/>  }
 <p/>   dependencies {
 <p/>       classpath 'org.greenrobot:greendao-gradle-plugin:3.2.1'
<p/>    }
<p/>}

<p/>app的buil.gradle文件添加

<p/>apply plugin: 'org.greenrobot.greendao'

<p/>dependencies {
  <p/>  compile 'org.greenrobot:greendao:3.2.0'
<p/>}

<p/>同时添加：
<p/>greendao{
 <p/>  /* 属性介绍：
  <p/>  schemaVersion--> 指定数据库schema版本号，迁移等操作会用到;
  <p/>  daoPackage --> dao的包名，包名默认是entity所在的包；
  <p/>  targetGenDir --> 生成数据库文件的目录;*/
  <p/>  //配置数据库版本号
 <p/>   schemaVersion 1
  <p/> //位置
  <p/>  targetGenDir 'src/main/java'
   <p/> daoPackage 'com.example.shisjin.greendao.db'
<p/>}

<p/>greendao中的注解
<p/>(一) @Entity 定义实体
<p/>@nameInDb 在数据库中的名字，如不写则为实体中类名
<p/>@indexes 索引
<p/>@createInDb 是否创建表，默认为true,false时不创建
<p/>@schema 指定架构名称为实体
<p/>@active 无论是更新生成都刷新
<p/>(二) @Id
<p/>(三) @NotNull 不为null
<p/>(四) @Unique 唯一约束
<p/>(五) @ToMany 一对多
<p/>(六) @OrderBy 排序
<p/>(七) @ToOne 一对一
<p/>(八) @Transient 不存储在数据库中
<p/>(九) @generated 由greendao产生的构造函数或方法

<p/>具体步骤
<p/>创建列表（实体类）:

<p/>@Entity
<p/>public class UserEntity {
    <p/>@Id
    <p/>private  Long id;
    <p/>@Property
    <p/>private String username;
    <p/>@Property(nameInDb = "passwd")
    <p/>private String password;

<p/>然后点击build
<p/>![image](https://github.com/shisjin/GreenDAO/blob/master/imgs/clipboard.png)

<p/>就根据greendao写的内容在对应位置生成相关类，就这么任性！
<p/>![image](https://github.com/shisjin/GreenDAO/blob/master/imgs/clipboard2.png)

