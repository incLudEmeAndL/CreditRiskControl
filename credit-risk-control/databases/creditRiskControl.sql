CREATE TABLE `original_data_columns`
(
    `Id`  int(11) NOT NULL AUTO_INCREMENT,
    `Name`   varchar(50) NOT NULL COMMENT '列名',
    `Describe`   varchar(500) NOT NULL COMMENT '列信息',
    PRIMARY KEY (`Id`) USING BTREE
) COMMENT='原始数据列信息表';