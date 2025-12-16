package com.spacecompany.game;

public class TestMain {
    public static void main(String[] args) {
        System.out.println("Starting JNI Test...");
        
        if (args.length < 1) {
            System.out.println("Usage: java TestMain <path_to_so>");
            return;
        }

        String libPath = args[0];
        System.out.println("Loading library from: " + libPath);
        
        try {
            System.load(libPath);
            System.out.println("Library loaded successfully!");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load library: " + e.getMessage());
            // 如果是因为缺依赖（如 liblog.so），这里会报错
            return;
        }

        JNIBridge bridge = new JNIBridge();
        
        // 模拟一个空的初始 JSON，就像新游戏一样
        String initialJson = "{}";
        System.out.println("Input JSON: " + initialJson);

        try {
            // 调用 tick，模拟 1 秒过去了
            System.out.println("Calling tick()...");
            String resultJson = bridge.tick(initialJson, 1.0);
            System.out.println("Output JSON: " + resultJson);
            
            // 检查金属是否被解锁
            // C代码应该返回类似 "resources": { "METAL": { ..., "unlocked": 1 } }
            if (resultJson.contains("METAL") && (resultJson.contains("\"unlocked\":1") || resultJson.contains("\"unlocked\":true"))) {
                 System.out.println("VERIFICATION PASSED: Metal seems unlocked.");
            } else {
                 System.out.println("VERIFICATION FAILED: Metal NOT found or NOT unlocked.");
            }

        } catch (Exception e) {
            System.err.println("JAVA EXCEPTION during tick: " + e.getMessage());
            e.printStackTrace();
        } catch (Error e) {
            // 捕获可能的 JNI 严重错误
            System.err.println("NATIVE ERROR during tick: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
