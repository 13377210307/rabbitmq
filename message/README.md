## 短信demo
#### 1：需求：
用户注册后，需要发注册邮件和注册短信，使用rabbitmq让两者同时进行
#### 2：实现：
使用topic模式，创建一个信息生产者、一个交换机、两个队列以及两个消费者，两个队列分别对应不同的
route-key用于完成不同的业务。
#### 3：作用
减少请求时间

