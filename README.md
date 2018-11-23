# study
# 工作中学习的小案例

### 简单实现一个中间数据的传输层
- 基于ArrayBlockingQueue
- 通过自定义的格式进行数据的写入和写出

### mysql load 方式导入大批量的数据
- 创建测试表
- 实现自定义的inputStream
- 通过load方式导入
- 借助中间传输层实现一个小demo

### netty实现im案例
- 客户端服务端启动类
- 定义通信协议
- 实现序列化和编解码
- 拆包粘包的实现
- 互聊
- 群聊
- 心跳


### 线程池的使用
- 自定义线程池


### 算法
- 排序算法
- 二分查找


### kafka 学习 版本 0.9.0.1
- 生产者
- 三种语义的消费模型
- 参数 max.partition.fetch.bytes,batch.size

### es的使用  新闻搜索案列
- 索引mapping 建立
- api的使用
- 修改了结构后，重新索引数据
- 别名的使用
````
创建别名
PUT /comet_v1/_alias/comet

查看信息
GET /*/_alias/comet

重新索引别名
POST /_aliases
{
    "actions": [
        { "remove": { "index": "comet_v1", "alias": "comet" }},
        { "add":    { "index": "comet_v2", "alias": "comet" }}
    ]
} 
````

















