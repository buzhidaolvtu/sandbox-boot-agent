package net.opensource.sandbox.example;

import com.alifafa.jvm.sandbox.api.Module;
import com.alifafa.jvm.sandbox.api.ModuleLifecycle;
import org.kohsuke.MetaInfServices;

@MetaInfServices(Module.class)
public class SandboxModule implements Module, ModuleLifecycle {

    @Override
    public void loadCompleted() {
        System.out.println("SandboxModule is loaded");
    }

    @Override
    public void onLoad() throws Throwable {

    }

    @Override
    public void onUnload() throws Throwable {

    }

    @Override
    public void onActive() throws Throwable {

    }

    @Override
    public void onFrozen() throws Throwable {

    }
}
