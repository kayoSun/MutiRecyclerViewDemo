# MutiRecyclerViewDemo
MutiRecycler 演示工程

#####MutiRecyclerView 是一个简化 RecyclerView的使用可实现单样式和复杂样式的使用

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/E01A5DB2-008C-473B-AC18-BD96900A28E5.png)

###使用方法：

#####添加依赖，在app目录下的build.gradle中添加如下：



#####1 可以 作为普通的RecyclerView使用

#####2 使用`MutiRecycler`的数据 适配器 `MutiAdapter`

（1）继承MutiData

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/data.png)

（2）继承MutiHolder

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/holder.png)

（3）继承MutiAdapter

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/sim_adapter.png)

（4）展示数据

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/sim.png)

#####3 使用`AdapterRuleManager`进行数据展示（推荐使用，简便）

展示简单列表：

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/manager_sim.png)

展示复杂列表(需要添加规则，直接`new Rule()`，重写里面的方法，或者自定义`XRule extends Rule` 即可)：

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/manager_muti.png)

在展示复杂列表的时候，我们的数据需要进行UI绑定设置

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/manager_muti_data.png)

如果需要上拉加载更多的功能 只需要添加脚规则`SimpleLoadMoreRule`，或者自定义脚布局规则，需继承`LoadMoreRule`，重写`loadmore()`方法即可

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/add_footer.png)

注意：

![](https://github.com/kayoSun/MutiRecyclerViewDemo/blob/master/pics/recycler_layoutmanager.png)

这里是这是条目的占位显示 规则，即 哪个样式占用几列（如果是纵向的）



####最后，欢迎各位大神指导

####共同进步
####这是一个长期支持版本


