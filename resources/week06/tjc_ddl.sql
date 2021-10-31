create table t_comm_commodityinfo
(
    store_id int not null comment '店铺id',
    comm_no int not null comment '商品编号',
    comm_name varchar(64) null comment '商品名',
    price double null comment '价格',
    status tinyint null comment '状态',
    create_datetime datetime not null comment '创建时间',
    update_datetime datetime null comment '更新时间',
    primary key (store_id, comm_no)
)
    comment '商品信息表';

create table t_comm_inventoryinfo
(
    store_id int not null comment '店铺id',
    comm_no int not null comment '商品编号',
    comm_name varchar(64) null comment '商品名',
    size varchar(4) null comment '尺码',
    color varchar(4) null comment '颜色',
    num int null comment '数量',
    primary key (store_id, comm_no)
)
    comment '库存信息表';

create table t_merch_merchinfo
(
    acct varchar(32) not null comment '商家账号'
        primary key,
    password varchar(32) not null comment '密码',
    create_datetime datetime not null comment '创建时间',
    update_datetime datetime null comment '更新时间'
)
    comment '商家信息表';

create table t_order_orderinfo
(
    order_id int not null comment '订单id'
        primary key,
    acct varchar(32) null comment '用户账号',
    addr_id int null comment '地址id',
    total_price double null comment '总金额',
    create_datetime datetime not null comment '创建时间',
    update_datetime datetime null comment '更新时间'
)
    comment '订单信息表';

create table t_order_orderlistinfo
(
    order_id int not null comment '订单id',
    order_list_id int not null comment '子订单id',
    acct varchar(32) null comment '商家id',
    comm_id int null comment '商品id',
    price double null comment '单价',
    num int null comment '数量',
    total_price double null comment '单价*数量',
    status tinyint(1) null comment '状态',
    sent_datetime datetime null comment '发货时间',
    get_datetime datetime null comment '收货时间',
    primary key (order_id, order_list_id)
)
    comment '子订单信息表';

create table t_pay_payinfo
(
    acct varchar(32) not null comment '用户/商家账号',
    cardno varchar(32) not null comment '银行卡号',
    primary key (acct, cardno)
)
    comment '支付账号信息表';

create table t_store_classifyinfo
(
    store_id int not null comment '店铺id',
    classify_id int not null comment '分类id',
    name varchar(16) not null comment '分类名称',
    primary key (store_id, classify_id)
)
    comment '店铺商品分类信息';

create table t_store_storeinfo
(
    store_id int not null comment '店铺id'
        primary key,
    acct varchar(32) null comment '商家账号',
    storename varchar(32) null comment '店铺名',
    avatar varchar(32) null comment '店铺头像',
    bground varchar(32) null comment '店铺背景图',
    create_datetime datetime not null comment '创建时间',
    update_datetime datetime null comment '更新时间'
)
    comment '店铺信息表';

create table t_user_addrinfo
(
    acct varchar(32) not null comment '用户账号',
    addr_id int not null comment '地址id',
    address varchar(128) not null comment '收件地址',
    telephone varchar(16) not null comment '收件人电话',
    primary key (acct, addr_id)
)
    comment '地址信息表';

create table t_user_userinfo
(
    acct varchar(32) not null comment '用户账号'
        primary key,
    password varchar(32) not null comment '密码',
    petname varchar(32) null comment '昵称',
    gender tinyint(1) null comment '性别',
    realname varchar(32) null comment '真实姓名',
    idno varchar(18) null comment '身份证号',
    avatar varchar(32) null comment '头像',
    create_datetime datetime not null comment '创建时间',
    update_datetime datetime null comment '更新时间'
)
    comment '用户信息表';

