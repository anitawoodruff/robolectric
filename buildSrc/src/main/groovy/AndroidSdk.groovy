class AndroidSdk implements Comparable<AndroidSdk> {
    static final JELLY_BEAN = new AndroidSdk(16, "4.1.2_r1", 0, "1.6")
    static final JELLY_BEAN_MR1 = new AndroidSdk(17, "4.2.2_r1.2", 0, "1.6")
    static final JELLY_BEAN_MR2 = new AndroidSdk(18, "4.3_r2", 0, "1.6")
    static final KITKAT = new AndroidSdk(19, "4.4_r1", 1, "1.7")
    static final LOLLIPOP = new AndroidSdk(21, "5.0.0_r2", 1, "1.7")
    static final LOLLIPOP_MR1 = new AndroidSdk(22, "5.1.1_r9", 1, "1.7")
    static final M = new AndroidSdk(23, "6.0.0_r1", 0, "1.7")
    static final N = new AndroidSdk(24, "7.0.0_r1", 0, "1.8")

    private static final double jdkVersion = Double.parseDouble(System.getProperty("java.specification.version"));

    static final List<AndroidSdk> ALL_SDKS = [
            JELLY_BEAN, JELLY_BEAN_MR1, JELLY_BEAN_MR2, KITKAT,
            LOLLIPOP, LOLLIPOP_MR1, M, N
    ]
    static final SUPPORTED_SDKS = ALL_SDKS.findAll { it.isSupportedOnThisJdk() }
    static final MAX_SUPPORTED_SDK = Collections.max(SUPPORTED_SDKS)
    static final MAX_SDK = Collections.max(ALL_SDKS)

    static {
        println "jdkVersion is $jdkVersion, max is $MAX_SUPPORTED_SDK.apiLevel"
    }

    private final int apiLevel
    private final String androidVersion
    private final String frameworkSdkBuildVersion
    private final String minJdkVersion

    AndroidSdk(int apiLevel, String androidVersion, int frameworkSdkBuildVersion, String minJdkVersion) {
        this.minJdkVersion = minJdkVersion
        this.frameworkSdkBuildVersion = frameworkSdkBuildVersion
        this.androidVersion = androidVersion
        this.apiLevel = apiLevel
    }

    boolean isSupportedOnThisJdk() {
        return jdkVersion >= Double.parseDouble(minJdkVersion)
    }

    String dependencyString() {
        return "org.robolectric:android-all:${androidVersion}-robolectric-${frameworkSdkBuildVersion}"
    }

    @Override
    int compareTo(AndroidSdk other) {
        return apiLevel - other.apiLevel
    }
}