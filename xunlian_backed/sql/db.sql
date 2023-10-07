use chiapi;
-- interface_info
create table if not exists interface_info
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `username` varchar(256) not null comment '用户名',
    `description` varchar(256) null comment '用户名',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态(0-关闭，1-开启）',
    `method` varchar(256) not null comment '请求类型',
    `userid` bigint not null comment '创建人',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment 'interface_info';

insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('贺旭尧', '马鹤轩', 'www.carmine-torp.org', '田子骞', '高子默', 0, '谢靖琪', 4, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('黄博超', '孔子涵', 'www.letha-beahan.io', '陶嘉懿', '高智渊', 0, '周晋鹏', 62, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('郝晓博', '廖天磊', 'www.dora-johnson.io', '唐文昊', '韩烨华', 0, '方志强', 77819306, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('史子轩', '万钰轩', 'www.herminia-miller.info', '黄展鹏', '曹立轩', 0, '何智辉', 54347, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('胡鹭洋', '梁泽洋', 'www.tamera-tremblay.org', '莫正豪', '莫金鑫', 0, '袁烨伟', 912607, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('段天磊', '卢炫明', 'www.rudolph-quitzon.biz', '汪博涛', '卢擎苍', 0, '于伟宸', 333482, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('姜聪健', '罗明哲', 'www.janet-wehner.io', '方智渊', '韦天翊', 0, '郝天宇', 8, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('曾立诚', '冯鸿涛', 'www.vada-orn.net', '吕擎苍', '何天磊', 0, '于子涵', 84392149, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('任皓轩', '武鸿涛', 'www.cedrick-legros.com', '钱伟宸', '莫鹏', 0, '邓昊强', 3756, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('夏弘文', '龚修洁', 'www.xavier-zulauf.org', '程浩轩', '朱越泽', 0, '莫文昊', 51486, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('姜擎宇', '汪博文', 'www.deon-hintz.com', '廖潇然', '邵胤祥', 0, '段炫明', 6344365286, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('郑烨霖', '江乐驹', 'www.anibal-boehm.org', '蔡俊驰', '龚立诚', 0, '韦楷瑞', 214, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('薛鹤轩', '石晟睿', 'www.humberto-harris.biz', '夏瑾瑜', '余正豪', 0, '何志泽', 8479158335, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('余明杰', '赖琪', 'www.ezra-dietrich.io', '邱擎苍', '薛烨霖', 0, '郝伟诚', 6, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('曹楷瑞', '钱鹭洋', 'www.divina-huel.net', '杨乐驹', '武天翊', 0, '赖胤祥', 289138835, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('顾建辉', '史志泽', 'www.sherice-reynolds.name', '吴文昊', '邓耀杰', 0, '毛昊天', 3717050, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('贾晋鹏', '谢鸿煊', 'www.tod-donnelly.io', '邓懿轩', '袁文昊', 0, '白明杰', 758746979, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('江钰轩', '覃明', 'www.bess-sawayn.name', '陆正豪', '莫博涛', 0, '邓鹭洋', 62314, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('史远航', '蒋航', 'www.shala-lesch.info', '郝擎宇', '曾志强', 0, '朱煜城', 7218, 0);
insert into interface_info (`username`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userid`, `isDelete`) values ('黎聪健', '韩文博', 'www.kristine-schmeler.net', '吕鸿煊', '邵熠彤', 0, '毛立诚', 33750613, 0);
