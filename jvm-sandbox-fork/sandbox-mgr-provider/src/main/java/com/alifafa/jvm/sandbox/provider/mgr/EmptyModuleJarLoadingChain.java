package com.alifafa.jvm.sandbox.provider.mgr;

import com.alifafa.jvm.sandbox.provider.api.ModuleJarLoadingChain;

import java.io.File;

/**
 * 空实现
 *
 * @author luanjia@taobao.com
 */
public class EmptyModuleJarLoadingChain implements ModuleJarLoadingChain {

    @Override
    public void loading(final File moduleJarFile) throws Throwable {

    }

}
