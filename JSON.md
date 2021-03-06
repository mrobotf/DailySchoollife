# 1. 值的类型和格式
1. 复合类型的值只能是**数组或对象**，不能是函数、正则表达式对象、日期对象。
2. 原始类型的值只有四种：字符串、数值（必须以十进制表示）、布尔值和 null （不能使用NaN, Infinity, -Infinity和undefined）。
3. 字符串必须使用**双引号**表示，不能使用单引号。
4. 对象的键名必须放在双引号里面。

# 2. JSON 对象
有两个静态方法 `JSON.stringify()` `JSON.parse()`

## 2.1 JSON.stringify()
* 将一个值转为 JSON 字符串。该字符串符合 JSON 格式，并且可以被 `JSON.parse()` 方法还原。

### 2.1.1 第一个参数
* 对于原始类型的字符串，转换结果会带双引号
* * `JSON.stringify('foo') === "foo" // false`
* * `JSON.stringify('foo') === "\"foo\"" // true`
* * 如果不是内层的双引号，将来还原的时候，引擎就无法知道原始值是布尔值还是字符串。
* 如果对象(var)的属性是 undefined、函数或 XML 对象，该属性会被 `JSON.stringify()` 过滤(结果“{}”)。
* 如果数组的成员是 undefined、函数或 XML 对象，则这些值被转成 null。
* 正则对象会转成空对象 “{}”

### 2.1.2 第二个参数
* 还可以接受一个数组，作为第二个参数，指定参数对象的哪些属性需要转成字符串。第二个参数说明要转换的对象
* * 第二个参数为 0 时(['0'])，只对对象({})有效，说明第一个对象转换，对数组（[]）无效
* * * `JSON.stringify({0: 'a', 1: 'b'}, ['0'])  // "{"0":"a"}"`
* * 第二个参数还可以是一个函数，用来更改JSON.stringify()的返回值。

### 2.1.3 第三个参数
用于增加返回的 JSON 字符串的可读性，由于默认返回的是单行字符串，对于大型的 JSON 对象，可读性非常差

第三个参数使得每个属性单独占据一行，并且将每个属性前面添加指定的前缀（不超过10个字符）。

默认输出
`JSON.stringify({ p1: 1, p2: 2 })
// JSON.stringify({ p1: 1, p2: 2 })`

分行输出
`JSON.stringify({ p1: 1, p2: 2 }, null, '\t')
// {
// 	"p1": 1,
// 	"p2": 2
// }`

## 2.2 参数对象的 `toJSON()` 方法
如果参数对象有自定义的 `toJSON()` 方法，那么 `JSON.stringify()` 会使用这个方法的返回值作为参数，而忽略原对象的其他属性。(会只返回 `toJSON()` 内要求的返回值)

# 3. `JSON.parse()`
用于将 JSON 字符串转换成对应的值。

`JSON.parse('{}') // {}`

`JSON.parse('"foo"') // "foo"`

如果传入的字符串不是有效的 JSON 格式，将报错，为了处理解析错误，可以将 `JSON.parse()` 方法放在 `try...catch` 代码块中。

`JSON.parse()` 方法可以接受一个处理函数，作为第二个参数，用法与 `JSON.stringify()` 方法类似。