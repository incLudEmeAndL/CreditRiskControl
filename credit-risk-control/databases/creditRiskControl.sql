CREATE TABLE `original_data_columns`
(
    `Id`  int(11) NOT NULL AUTO_INCREMENT,
    `Name`   varchar(50) NOT NULL COMMENT '列名',
    `Describe`   varchar(500) NOT NULL COMMENT '列信息',
    PRIMARY KEY (`Id`) USING BTREE
) COMMENT='原始数据列信息表';

CREATE TABLE `model_info`
(
    `Id`  int(11) NOT NULL AUTO_INCREMENT,
    `Name`   varchar(500) NOT NULL COMMENT '模型名',
    `Path`   varchar(500) NOT NULL COMMENT '模型名',
    `Type`   varchar(500) NOT NULL COMMENT '模型类型',
    `Accuracy`   double NOT NULL COMMENT '模型精度',
    `MSE` double NOT NULL COMMENT '计算均方误差 (MSE)',
    `MAE` double NOT NULL COMMENT '平均绝对误差',
    `RMSESquared` double NOT NULL COMMENT '均方根误差',
    `CreatedAt` datetime(6) NOT NULL COMMENT '模型创建时间',
    PRIMARY KEY (`Id`) USING BTREE
) COMMENT='模型表';