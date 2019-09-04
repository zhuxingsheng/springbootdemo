package com.jack.remote.service;

/**
 * 远程service，具体实现在其它进程
 */
public interface RemoteService {

    public void remoteMethod();

    public void remoteMethod(String param);
}
