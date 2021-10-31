# JTC购物平台

## 需求分析

### 基础分析



- 商家：
    - 商家通过邮箱，设置密码，进行商家注册；
    - 完成商家注册后，可根据注册的邮箱和密码登录购物平台；
    - 登录后，进入“我的店铺”，进行店铺的信息编辑（店铺名、店铺ID、店铺头像、店铺背景图、商品分类管理...）；
    - 完成店铺信息后，添加商品信息（商品ID、商品编号、商品名、商品分类、价格、商品详情、图片）；
    - 完成商品信息后，完善商品的库存信息（颜色、尺码、库存数量...）；
    - 完成商品的库存信息后，上架商品，登录的用户即可购买；
    - 用户下单后，商家可进入“订单管理”，进行订单的查询管理等的操作（发货(绑定物流单号)，修改订单价格....）

- 用户：
    - 用户通过邮箱，设置密码，进行用户注册；
    - 完成用户注册后，可根据注册的邮箱和密码登录购物平台；
    - 登录后，进入“个人中心”完善个人信息（昵称、性别、真实姓名、身份证号码）；
    - 完善了个人信息之后，添加“银行卡信息”（银行卡号）
    - 添加收货地址（收件人名称、收件人电话、收件地址）

- 商品浏览， 选中某件商品，选择尺码、颜色；
- 加入购物车/立即购买进入购物车，选择商品进行结算、选择收货地址、提交订单、选择支付方式；
- 支持完成后、可在“我的订单”找到该笔订单的记录；



### 划分职责（涉及的功能点）



- 用户：
    - 注册、登录、用户中心、地址管理、收支账号（银行卡） 管理、订单管理
- 商家：
    - 注册、登录、店铺管理、商品管理、库存管理、店铺活动、优惠券、收支账号管理、订单管理
- 跨店活动：
- 购物车：
    - 商品加入购物车、下单
- 订单：
    - 选择地址、支付方式、提交订单
- 订单管理：
    - （待付款、待发货、待收款、待评价、退款/售后）（待发货、待退款确认/售后）
- 支付：
    - （第三方）
- 物流：（第三方）
- 评论：
- 搜索：商品、店铺、跨店活动、排序方式



### 定义类、属性、方法



- 用户

    - 用户账号（邮箱），密码、昵称，性别，真实姓名、身份证号

- 支付账号

    - 用户账号，银行卡号

- 地址

    - id，用户账号，收货地址，电话

- 商家

    - id商家账号（邮箱），密码

- 店铺

    - 店铺id，商家账号，店铺编号，店铺名、店铺头像、店铺背景图

- 店铺商品分类管理

    - 分类id，店铺id，分类名称

- 商品

    - id、商品编号、*店铺id*，商品名、价格、

- 库存

    - 商品编号、*店铺id*、商品名、尺码、颜色、数量

- 订单

    - 订单id，用户账号，地址id，总金额

- 子订单

    - 订单id，子订单id，商家id，商品id，单价、数量、单价*数量、状态



### 定义类与类之间的交互关系

### 将类组装起来并提供执行入口

### 优化分析



### 数据库分析设计

- 用户（T_User_UserInfo）
    - id，用户账号（acct），密码（password）、昵称（petname），性别（gender），真实姓名（realname）、身份证号（idno）、头像（avatar）、创建时间（create_datetime）、更新时间（update_datetime）
- 地址（T_User_AddrInfo）
    - id（addr_id），用户账号（acct），收货地址（address），电话（telephone）
- 支付账号（T_Pay_PayInfo）
    - 用户账号（acct），银行卡号（cardno）
- 商家（T_Merch_MerchInfo）
    - id（id）、商家账号（acct），密码（password）、创建时间（create_datetime）、更新时间（update_datetime）
- 店铺（T_Store_StoreInfo）
    - 店铺id（store_id），商家账号（merch_id），店铺编号（store_no），店铺名（storename）、店铺头像（avatar）、店铺背景图（bground image）、创建时间（create_datetime）、更新时间（update_datetime）
- 店铺商品分类管理（T_Store_ClassifyInfo）
    - 分类id（classify_id），店铺id（store_id），分类名称（name）
- 商品（T_Comm_CommodityI）
    - id（comm_id）、商品编号（comm_no）、*店铺id*（store_id），商品名（comm_name）、价格（price）、状态（status）、创建时间（create_datetime）、更新时间（update_datetime）
- 库存（T_Comm_InventoryInfo）
    - 商品编号（comm_no）、*店铺id*（store_id）、商品名（comm_name）、尺码（size）、颜色（color）、数量（num）
- 订单（T_Order_OrderInfo）
    - 订单id（order_id），用户账号（acct），地址id（addr_id），总金额（total_price）、创建时间（create_datetime）、更新时间（update_datetime）
- 子订单（T_Order_OrderListInfo）
    - 订单id（order_id），子订单id（order_list_id），商家id（merch_id），商品id（comm_id），单价（price）、数量（num）、单价*数量（total_price）、状态（ ）、发货时间（sent_datetime）、收货时间（get_datetime）


[JTC 数据库 DDL](https://github.com/J-doIt/geektime_java_tc/blob/main/resources/week06/tjc_ddl.sql)

