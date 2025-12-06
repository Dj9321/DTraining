package com.main.imp;

public class CallerMainDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("CallerMainDemo starting\n");

        System.out.println("1) Direct Java call to UnicodeDemo.main:");
        UnicodeDemo.main(new String[]{"direct-call"});

        System.out.println("\n2) Reflection call to UnicodeDemo.main:");
        java.lang.reflect.Method m = Class.forName("com.main.imp.UnicodeDemo").getMethod("main", String[].class);
        m.invoke(null, (Object) new String[]{"reflect-call"});

        System.out.println("\n3) Running UnicodeDemo in a separate JVM (ProcessBuilder):");
        String javaBin = System.getProperty("java.home") + "/bin/java";
        ProcessBuilder pb = new ProcessBuilder(javaBin, "-cp", "target/classes", "com.main.imp.UnicodeDemo", "separate-jvm");
        pb.redirectErrorStream(true);
        Process p = pb.start();
        try (java.io.InputStream is = p.getInputStream(); java.util.Scanner sc = new java.util.Scanner(is).useDelimiter("\\A")) {
            String out = sc.hasNext() ? sc.next() : "";
            System.out.println(out);
        }
        int exit = p.waitFor();
        System.out.println("Separate JVM exit code: " + exit);

        System.out.println("\nCallerMainDemo done.");
    }
}
