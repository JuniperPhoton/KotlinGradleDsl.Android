mapOf(
    "appVersionName" to "1.0.0",
    "appVersionCode" to 100,
    "kotlinVersion" to "1.3.20",
    "compileSDKVersion" to 28,
    "minSDKVersion" to 21,
    "targetSDKVersion" to 28,
    "constraintLayoutVersion" to "2.0.0-alpha3",
    "androidKtxVersion" to "1.1.0-alpha03",
    "androidAppCompatVersion" to "1.0.0-beta01"
).forEach {
    project.extra.set(it.key, it.value)
}