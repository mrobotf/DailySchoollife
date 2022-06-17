# 基于 TCP 的聊天小程序

## 客户端向服务器发送数据
字节流按字节传输，更节省空间，因此采用字节流传输(InputStream, OutputStream)。

`OutputStream osw = socket.getOutputStream();`

传输完成后，我们不能具体控制流中的数据，因此转成字符流进行控制。并保证编码一致

`OutputStreamWriter osw = new OutputStreamWriter(os, "utf8");`

刷新流，也就相当于发送信息给服务器

`osw.flush()`

## 服务器
ServerSocket 用于服务端，`accept()` 用来监听客服端是否有请求，返回客户端的 socket，拿到之后，就可以用这个 socket 读取信息，用输入流读取(InputStream, OutputStream)。

## 问题
不能同时发送多条信息，必须客户端发一条，服务器发一条，循环。十分简陋
