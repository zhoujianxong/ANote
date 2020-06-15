package com.example.commlib.base;

import android.content.Context;

public interface IBaseApplication {
    //moudles 的application 继承这个接口  通过  app组件Application 的onCreat()去初始化moudles 的application
    //直接调用 BaseApplication 的modulesApplicationInit（） 方法
    void init(Context ct);
}
