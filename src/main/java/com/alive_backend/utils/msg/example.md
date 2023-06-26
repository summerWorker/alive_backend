```java

import com.alive_backend.utils.msg.MsgUtil;
class Example{
    Msg function(){
        return MsgUtil.makeMsg(MsgCode.SUCCESS, MsgUtil.RELEASE_SUCCESS_MSG);
    }
}
        
```
`MsgUtil`: 包含了一些常用的返回信息常量

`MsgCode`: 包含了一些常用的返回状态`status`常量

`Msg`: 返回信息的实体类

`MsgUtil.makeMsg`: 生成返回信息的方法