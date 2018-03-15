```
由于本应用使用了google analytics，
在开始构建前你需要一个关于google analytics的google-services.json，
以及你需要在res/xml/tracker_app.xml内输入你的Tracking ID
```


优达学城Android开发进阶
# 毕业设计

## 界面展示
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/1.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/2.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/3.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/4.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/5.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/6.jpg)
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/7.jpg)

原设计:https://github.com/YGLLL/Capstone-Project
## 与原设计的不同
- 取消了Video栏目
```
原因：斗鱼暂未开放视频点播API
```
- 去掉首页顶部的分类栏以及使Live栏仅展示分类，不展示房间
```
原因：为了各个页面分工更明确
```
```
ps：目前直播观看5分钟后会自动停止，但刷新后可以继续观看。
这是因为播放链接抓取自斗鱼移动版网页，该链接仅能播放5分钟，这是目前唯一能实现的播放方式
```

## 项目结构
- 网络组件结构大意
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/Retrofit2.jpg)
- UI结构
![](https://github.com/YGLLL/FunLive/blob/master/screenshot/MainActivity.jpg)

## 项目要求实现点
### 小部件
- .view.widget.FunLiveWidget
### google admob
- .application.FLApplication
- .layout.fragmnt_user_room_list.xml
### google analytics
- .application.FLApplication
- .view.CateActivity
- .view.RecommendFragment
- .view.ClassifyFragment
- .view.UserFragment
### 应用主题扩展AppCompat
- .view.MainActivity
### 签名
- build.gradle(app)
### ContentProvider
- .db.FunLiveProvide
### 使用AsyncTask
- .view.RecommendFragment
### Loader
- .view.UserRoomListFragment
```
ps:虽然这里使用了Loader载入数据，
但我发现在删除数据库数据后onLoadFinished方法得不到调用（见.view.adapter.user.UserRoomListAdapter），
找了很久找不到原因，
所以不得不同时使用另一种载入数据的方法，
但这里的Loader仍然是有效的
```

**最后一个ps：渣渣代码，感谢老师耐心审阅！**