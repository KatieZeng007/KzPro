# Kafka配置项说明
# 生产者 producer
bootstrap-servers：连接kafka的地址，多个地址用逗号分隔
batch-size：当将多个记录被发送到同一个分区时， Producer 将尝试将记录组合到更少的请求中。这有助于提升客户端和服务器端的性能。这个配置控制一个批次的默认大小（以字节为单位）。16384是缺省的配置
retries：若设置大于0的值，客户端会将发送失败的记录重新发送
buffer-memory：Producer 用来缓冲等待被发送到服务器的记录的总字节数，33554432是缺省配置
key-serializer：密钥的序列化类
value-serializer：值的序列化类

# 消费者 consumer
group-id: test-consumer-group 用于标识此使用者所属的使用者组的唯一字符串
auto-offset-reset: earliest 从头开始消费  / latest 实时生产，实时消费，不会从头开始消费 /none
enable-auto-commit: 是否自动提交 
auto-commit-interval: 100 如果'enable.auto.commit'为true，则消费者偏移自动提交给Kafka的频率（以毫秒为单位），默认值为5000
key-serializer: org.apache.kafka.common.serialization.StringDeserializer 密钥的反序列化器类
value-serializer: org.apache.kafka.common.serialization.StringDeserializer 值的序列化类