package org.springframework.boot.loader.agent;

import org.springframework.boot.loader.MainMethodRunner;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

public class PreMainMethodRunner {

    private final String premainClassName;

    private final Object[] args;

    /**
     * Create a new {@link PreMainMethodRunner} instance.
     *
     * @param preMainClass the main class
     * @param args         incoming arguments
     */
    public PreMainMethodRunner(String preMainClass, Object[] args) {
        this.premainClassName = preMainClass;
        this.args = (args == null ? null : args.clone());
    }

    public void run() throws Exception {
        Class<?> mainClass = Thread.currentThread().getContextClassLoader()
                .loadClass(this.premainClassName);
        Method premainMethod = mainClass.getDeclaredMethod("premain", String.class, Instrumentation.class);
        premainMethod.invoke(null, this.args[0], this.args[1]);
    }

}
