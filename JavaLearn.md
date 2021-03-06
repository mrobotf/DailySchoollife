# Chap 2
## 方法的定义和调用
可以将反复执行的多条语句抽象成一个方法，即方法可用来组织和定义重用的代码块。
> 一个方法的定义由方法名，形式参数，返回值类型以及方法体构成

## 方法的递归
需要满足三个条件
+ 有递归公式的描述
+ 每次递归调用必须使进程接近递归的终止条件
+ 有递归终止条件

## 数组 --> 对象
数组是一个对象，有 length 属性

### foreach结构
+ 针对数组，不用再创建一个整型的计数变量来访问每一个元素，foreach 会自动提供
+ `System.out.println(Arrays.toString(arr))` 也可以直接打印

## 命令行参数
+ Java 可以从命令行中接受任意数量的参数，是从操作系统传入的
+ 每个参数被视为字符串分别存储在 main 函数的参数数组中
+ 可以使用双引号将多个字符串作为一个整体显示

`public static void main(String[] args)`, `args` 即用来存储命令行参数。命令行参数类型为字符，转换为 `int` 型利用 `Double.parseDouble()` (工具类)，如 `x = Double.parseDouble(args[2]);`. 若利用输入的字符，`op = args[1].charAt(0)`, 取出 `args[0]` 的第一个字符

## 注意事项
+ 如果定义的类是 `public`，文件名必须和类名相同。
+ 一个文件可以用很多 `class`, 但只有一个被 `public` 修饰
+ 编译后，一个类对应生成一个 .class 文件
+ 一个 Java 应用程序应包含一个 main() 方法，且写法固定(`public static void main()`)
+ 可用来生成两类程序：小应用程序(Applet)(主要嵌入在浏览器中)，应用程序
+ 主要面向网络编程，内核支持多线程编程

# Chap 03 面向对象基本原理

+ 类设计方法：类体由成员变量、构造方法、成员方法组成
+ 消息：实现对象之间的交互功能，本质即方法调用。由接受消息对象的名称，要执行方法的名称，方法需要的参数<br>
    `Student stu = new Student("10001", "zhangsan");`<br>
    `stu.moveto("lanzhou"); //给 stu 发消息`

## 构造方法
需要给对象分配空间并进行初始化, 即要进行一系列的构造工作. 是类中的一个特殊的方法

默认构造方法：没有任何参数的构造方法
+ 如果没有提供构造方法, Java 编译器会自动提供一个缺省构造方法, 将成员变量初始化为缺省值
+ 要和类名同名, 方法不能有返回类型
+ 但可以有返回值, 要返回对象在内存中的开始地址
+ 可以重载

### 方法重载
同一个类中多个方法有相同的名字, 不同的参数列表, 这种情况称为方法重载. 根据传入参数类型不同, 调用不同方法

#### 创建对象
使用 new, 分配对象内存, 并将该内存初始化为缺省值(保证在使用该内存前, 该内存的值已经是可预知的), 一旦 new 完成分配和初始化内存, 将调用构造方法来执行对象初始化. 创建对象后没有引用变量指向它, 则为匿名对象

### this 关键字
默认情况下，this指向的是这个关键字所处的方法所属的类的实例，同时这个实例即是你调用this时所处的实例

### getter / setter 方法
对私有化的成员变量, 读取/修改对象内部的成员数据, 通过这些方法可以过滤传入数据, 剔除非法数据, 即对象对外提供的交换接口, 如<br>  
`public String getName(){return name};`<br>
`public void setName(String n){name = n};`

+ getter 方法保护敏感数据不会泄露
+ setter 方法过滤非法数据

### equals() & hashcode() 方法
+ 都用于比较两个对象是否相等, 相等返回 true
+ equals() 属于 Object 类, 判断两对象是否指向同一个内存区域(默认情况)，比较内容是否相等，和 == 不同(基本数据类型比较值，引用类型比较内存地址)
+ hashcode() 是对对象生成哈希码进行比较

### Object.toString()
对任何表达式转换为字符串，其他的类中也有 `toString()` 方法存在，返回字符串( Object 是所有类的父类)
+ 用 '+' 将对象同一个字符串连接
+ **自动调用情况：如 `System.out.print()` 自动调用转化为 String 类型输出**

## 面向对象设计基本原理
### 抽象原理
从普遍抽象出属性和行为形成类，从整体看对象，忽略其内部细节
### 封装原理
内部不可见，只留下必需接口和外界进行交流就是封装

`private` 实现封装，`public` 外部可访问。
`getter` `setter` (对需要访问的属性)进行读取修改操作，并需要提供约束和过滤代码。**如果没有 `setter` 方法，封装类中的数据就不能修改**
### 继承原理
对父类相应部分做出修改(覆盖父类中对应的方法)，其余部分不变。`extends` 继承，只继承非 private 的成员变量和方法，不继承构造方法
### 多态原理
多个方法使用同一个接口，因此在不同的上下文中，执行的代码可能不一样。(不同的个体对同一刺激的反应不同)。Java 从两个突出方面支持多态性，一是每个方法都可以被子类覆写，二是 `interface` 关键字

父类可以指向不同的子类对象，在之后调用时，会自动执行由该子类覆写后的方法。方法调用相同，但执行代码不同
### 组合原理
用联合和聚合两种，区别在于如何组成整体，聚合通常只看到整体，如手机，联合可以看到组成整体的部分

+ 聚合通常是一个整体的封装对象，内部分为许多标准小对象，是具有特定功能和标准接口的封装体

### Java 的访问控制
局部变量只能在方法内部访问。
1. 公开级别 `public`, 对外完全公开
2. 受保护级别 `protected`, 对子类及同一个包的类公开
3. 默认级别，对同一个包的类和对象公开
4. 私有级别，`private`, 只有本类对象可以访问

类分为顶层类(只可以处于公开或者默认级别)和内部类(可有各种访问权限)

### Java 的垃圾回收机制
内存管理技术，当对象不再被程序中任何引用变量引用时，内存才可能被回收。`System.gc()` 显式调用

# Chap04 Java 特殊关键字和面向对象原理进阶
## static 关键字
有三种用法：
1. static 修饰成员变量，为类成员变量
2. static 修饰成员方法，为类成员方法
3. static 代码块，来初始化类成员变量，当虚拟机加载类时自动执行此代码
### 静态变量
修饰的变量称为静态成员变量(类层次的成员),(没有 static 修饰的称为实例变量，每个对象都有一份内存)，无论多少实例，在内存中只有一份，是所有对象共享的，可通过类名直接访问，不需要先创建对象再访问，不能通过 this 访问。

作用：类变量用来在各个对象之间共享数据或传递信号，实例变量描述对象特有属性或数据
### 静态方法
修饰的方法称为静态方法(类方法)，不能使用 this，只能访问其他的静态成员，不能被覆盖为非静态方法

在类刚开始加载时还没来得及创建对象，而静态方法可不通过对象调用，因此 main 必须是静态的

**总结**
1. 静态变量不能用 this 访问，所有对象共享，可通过类名直接访问(this 必须通过创建对象访问)
2. 静态方法只能访问静态变量，不能被覆盖为非静态方法

### 静态块
+ 如果需要通过计算来初始化静态变量，可声明一个静态块
+ 只在该类被加载时执行一次
+ 只能初始化类的静态数据成员

### 单态设计模式
只能存在一个对象，封装的进阶

设计方法
1. 构造方法私有化
2. 定义一个该类的静态成员变量，在类加载时自动构造一个对象，指向这个对象
3. 定义一个静态成员方法，在需要时返回已经创建好的实例引用

## super 关键字
类只能有一个父类，super 代表父类对象，调用父类的构造方法(如果在子类的方法中调用，必须写在第一条)或访问父类被覆盖的方法或属性
### 方法覆盖
哪个类创建就执行哪个类的方法

和方法重载的区别是在不同类中，方法和父类都相同。方法重载是在同一个类中方法名相同，参数类型，代码不同

## abstract 关键字
抽象类，主要用来生成派生类，不能实例化对象。只要一个类包含了抽象方法，自动变成抽象类。其中的抽象方法必须有 abstract, 抽象类中可以有非抽象方法

抽象方法，代表某种接口标准，在子类中实现具体标准。不能有代码(不能有{})。没有抽象的构造方法，没有抽象的静态方法

## final 关键字
代表进化终止，final 类不能被继承。子类不能覆盖final 方法，final 类中所有方法为隐式的 final 方法。final 变量，内容不能被修改，只能初始化一次

## instanceof 关键字
测试一个对象是否属于某个类或某个接口
## interface 关键字
继承是单一的，但可实现多个接口。公有抽象方法和公有静态常量，接口之间可以继承。一个类通过 implements 声明实现的一或多个接口。

若一个类实现了某个接口，继承接口中的常量和抽象方法。

## 多态进阶
方法重载或方法覆盖

java.awt.Graphics 是一个用来绘制2D图像必须导入的java包，提供对图形图像的像素，颜色的绘制。

`appletviewer *.html` 运行小程序

抽象类和接口两种方式都可以实现

## 枚举和自动装箱拆箱
枚举：给一个变量或方法创建一系列的有效值。使用 `enum` 限制程序只能采用一个有效值

**枚举类**
+ 不能实例化(因为没有公开的构造器)
+ 隐式为 static
+ 每个枚举常量只是一个实例对象，加载类时自动创建
+ 可以调用枚举类的方法

枚举类enum的values()方法
value()方法可以将枚举类转变为一个枚举类型的数组，因为枚举中没有下标，我们没有办法通过下标来快速找到需要的枚举类，这时候，转变为数组之后，我们就可以通过数组的下标，来找到我们需要的枚举类。

自动装箱拆箱：基本数据类型和其封装对象之间进行转换

`@` 注解 `@Override` 检查该方法是否是重写方法。如果的确重写了，则编译听过，如果没有构成重写，则编译错误

## 内部类和匿名类
匿名类不能定义任何静态成员、方法和类

## package
方便地重复利用已经创建的类

`javac -d 路径 -classpath 路径列表 Java 源程序文件 `  一个包的文件都在时，编译之后会自己生成文件夹，不要进入这个文件夹，直接运行

`java [-cp] 路径列表 包名.类`   源文件在 test 文件夹中，包名为 test，启动文件为 Run.java。


# Chap05 异常处理
## 概念
异常: 程序运行时发生的不可控的错误。
发生异常后此前分配的所有资源还没有被归还给操作系统，OS 认为这些还是被占用的，这将导致资源漏洞

try: 尝试执行的代码段，抛出异常对象

catch: 捕获异常对象代码段

finally: 不管是否异常都要执行的片段。善后工作

throw: 明确地抛出异常对象

throws: 声明方法可能出现的异常

异常处理模块尽量不出现 `return` `System.exit()`, 会使流程变得更加复杂。所有异常类型都是 Throwable 的子类

异常可分为：
1. 内部错误(重量级异常)，由 Error 类的子类产生，错误发生程序无法继续执行。
2. 轻量级异常，程序在绝大部分情况下可以解决
   + 由 RuntimeException 及其子类产生，非检查异常，编译器一般不检查
   + 其他剩余情况的异常，检查异常，编译时会检查

## 异常处理
getMessage();       printStackTrace();
### 自行处理
1. try-catch
 
   可以提供多个 catch 分别处理各种异常类型，父类异常要放在后面
   
   嵌套 try-catch 模块，先寻找内部块的 catch 模块，后找外部

2. final
   
   释放清理有关资源和善后工作，和 try 一起处理。没有异常直接执行 final，有异常先 catch 后 final

   return 不影响 final 的执行。有 break continue 可能会出现逻辑混乱(不会立即执行，还是会把 final 执行完))

   try 块最多只一个 final 即可
### 回避异常
异常交给调用者处理

### 自定义异常
定义的应为 Exception 或其子类的子类，都可获得 Throwable 类定义的方法

## 类设计指导原则
+ 内聚：一个类应该抽象一个单独的实体类型
+ 一致：遵循标准 Java 编程风格
+ 封装：隐藏数据，使得容易恢复
+ 清晰：类有一个清晰的契约，看到容易理解该类的作用
+ 完整：抽象的类应该自我独立和完整，因为是为很多不同的用户设计，应该能通过属性和方法实现功能，基本不用二次编码去实现二次功能
+ 合理区分实例和静态：实例：依赖于对象的具体属性的变量或方法    静态：一个类的所有实例对象共享的变量
+ 继承和聚合：is-a 和 has-a 的关系
+ 接口和抽象类：强的 is-a 关系用抽象类和继承，弱 is-a, 表明一个对象具有某种属性，接口


# chap06
1. 字节流----二进制文件
- byte
- InputStream & OutputStream

2. 字符流----文本文件
- 字符
- Reader & Writer

其他 File RandomAccessFile

java.nio 面向缓存, java.io 面向流
+ 流的分类
+ + 按照读写单位，分为字节流(InputStream/OutputStream)和字符流(Reader/Writer); 
+ + 按照流的来源，分为节点流(表示流的起点或终点 如 FileInputStream)和过滤流(必须以其他流对象为参数，来进行处理)

**I**

## 二进制文件
InputStream 和 OutputStream 父类，byte，都是抽象类(有方法需要具体实现)
### OutputStream & InputStream
定义用于写入字节或字符数组的方法，子类必须提供一个写入字节的方法

### FileOutputStream & FileInputStream
对文件内容按照字节读写
没有文件创建文件，有文件可以选择叠加到末尾

### 数据流 DataInputStream/DataInputStream
提供读取写入基本数据的能力，不能直接读取文件，因此需要和 FileInputStream 配合使用

### RandomAccessFile
文件任何位置查找和写入，随机访问文件

## 对象流
只有实现了 Serializable 接口的类才可以被串行化(序列化)，Serializable 接口中没有任何方法，一个类声明实现 Serializable 接口时，只是表明该类加入对象串行化协议。串行化一个对象，要通过对象输出流将对象状态保存下来(保存到文件或通过网络传输到其他地方)，再通过对象输入流将对象状态恢复
### ObjectInputStream & ObjectOutputStream
读取存储对象  存储对象，父类是 InputStream 和 OutputStream

在主方法中可以重写 readObject() 和 writeObject(), 定义为 private, 自定义输入输出

+ 流链：数据从节点流开始到输入进程，有时会经过好几道流程，形成流链

## 文本文件
Reader 和 Writer 父类，字符，都是抽象类
### FileReader & FileWriter
字符方式读写，在之前使用 File 通常是为了在读写文件之前获取文件的属性
### BufferedReader
从字符流中读取写入文本，缓冲各个字符，和其他流配合使用
### Scanner
### PrintWriter
以文本格式写入各种类型数据，和其他流配合使用

## File
只对文件进行管理，不对内容进行访问



# Chap 11 数据库编程基础
## JDBC
* 是一个软件层，把应用程序和数据库分开
* 支持两种模型 
1. 二层模型
   Java applet/应用程序直接与数据库交互   C/S 结构
2. 三层模型
   使用中间层，和 B/S 结构一致
* JDBC 的三个组件，应用程序(需要设计)、驱动程序管理器(JDK 提供)、驱动程序(不同数据库由不同厂商提供)

## JDBC 编程步骤
1. * 注册  `DriverManager.registerDriver(driver)` 告知这是一个驱动程序
   * 加载  `Class.forName("sun.jdbc.odbc.JdbcOdbcDriver")`
   * 新的 JDBC 版本不需要单独注册，用 Class.forName 加载的同时完成注册
2. 建立连接
   * JDBC 连接由数据库 URL 标识`jdbc:<subprotocol>:<subname>`
   * 对 MySQL, url = "jdbc:mysql://服务器ip/数据库名"
   * 建立连接 `Connection conn = DriverManager.getConnection(URL, login_name, login_password);`
3. 构造 SQL 语句
   * 一旦建立了连接，就可以构造 Statement 对象将 SQL 语句发送到 DBMS。 SELECT 语句使用 executeQuery() 方法；创建或修改表的 SQL 语句，使用 executeUpdate(). `Statement stmt = con.createStatement();` 创建对象 stmt, 其中 con 为连接对象，代码为创建 Statement 对象的方式。
 * `stmt.executeUpdate(query);`  `ResultSet re = stmt.executeQuery(query);`(执行 executeQuery() 方法后返回 ResultSet)   `stmt.execute();`(execute() 返回布尔值，可以执行任何 SQL 语句)
4. 提交查询(数据库主要有两类命令 DDL 和 DML)
   * DDL，主要创建、修改、删除表 (create, alter, drop) `stat.executeUpdate("create table Customer (CustId(3), CustName(15))");`
   * DML 对数据类型进行操作(select, insert, update, delete，, 后三种通常用 executeUpdate())
5. 显示结果
   * 一旦通过 ResultSet 拿到了 SQL 执行结果，可以使用 getXXX() 方法检索数据
6. 关闭 Statement 和 Connection `stmt.close();` 释放资源