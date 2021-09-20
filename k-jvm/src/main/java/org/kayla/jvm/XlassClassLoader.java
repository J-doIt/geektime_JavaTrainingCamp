package org.kayla.jvm;

import com.google.common.io.ByteStreams;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * .xlass 类加载器
 *
 * @author Kayla(J - doIt)
 * @date 2021/09/19 18:42
 **/
public class XlassClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class<?> xlass = new XlassClassLoader().loadClass("Hello");
            Object instance = xlass.getConstructor().newInstance();
            xlass.getMethod("hello").invoke(instance);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] decodeBytes = decode(getEncryptClassFile(name));
        return defineClass(name, decodeBytes, 0, decodeBytes.length);
    }

    /**
     * 获取加密过的 class 文件
     * @param className
     * @return
     */
    private byte[] getEncryptClassFile(String className) {
        byte[] encrytyBytes = null;
        try {
            InputStream inputStream = XlassClassLoader.class.getResourceAsStream("/" + className + ".xlass");
            assert inputStream != null;
            encrytyBytes = ByteStreams.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encrytyBytes;
    }

    /**
     * byte[] 取反
     * @param bytes
     * @return
     */
    private byte[] decode(byte[] bytes) {
        byte[] decodeBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            decodeBytes[i] = (byte) (255 - bytes[i]);
        }
        return decodeBytes;
    }
}
