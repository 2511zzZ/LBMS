# 直播数据可视化系统
这是一个基于Spring Boot + Vue的直播数据可视化系统，实现了直播数据的可视化展示、分级权限管理、警报数据流处理、报表的统计与文件导出、实时事件通知等功能。

此部分为后端项目。前端地址：[LBMS-Web](https://github.com/2511zzZ/LBMS-web)

主要使用的技术：MyBatis、Shiro、WebSocket、Quartz

### 项目简介

1. RESTful风格的前后端分离式开发
2. 使用Shiro完成分级权限管理
3. 直播实时数据与历史数据的可视化展示
4. 对主播举报次数的实时检测，相关警报的发起、处理与传递
5. 基于Apache POI与iText实现直播数据的报表统计与导出
6. 主播管理、用户管理等基础功能模块

### 预览

更多预览图请查看前端项目：[LBMS-Web](https://github.com/2511zzZ/LBMS-web)，或项目展示博客：[LBMS直播数据可视化系统展示](https://www.cnblogs.com/2511zzZ/p/13173545.html)

**实时总览数据：**

![online/total](https://images.cnblogs.com/cnblogs_com/2511zzZ/1791329/o_200621100049image-20200530134220601.png)

**分区历史数据：**

![alarm/my-alarm](https://images.cnblogs.com/cnblogs_com/2511zzZ/1791329/o_200621100118image-20200530134310817.png)

**警报处理页面**

![alarm/my-alarm](https://images.cnblogs.com/cnblogs_com/2511zzZ/1791329/o_200621100251image-20200530135131336.png)

### API
本项目集成了Swagger2，**/api** 页面列出了本项目所有接口

### 权限管理
1. 本项目拥有四种权限:team、group、branch、total
	每种权限的角色只能访问自己管辖范围内的数据(该范围通过struc_manage表控制)
2. 在application.yml文件中可以配置能够跳过数据库验证的admin账户,通过该方法配置的账户拥有最高权限

### 实时数据生成
项目启动后，Quartz将会开启一个线程（默认关闭，通过**quartz.ApplicationStartQuartzJobListener**配置）用于执行模拟生成实时数据任务，该任务每分钟向anchor_online表中插入数据，并以此为基础计算其他数据