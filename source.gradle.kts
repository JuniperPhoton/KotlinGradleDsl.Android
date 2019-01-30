val file = rootProject.file("local.properties")
file?.useLines { lines ->
    lines.forEach {
        val lineString = it
        println("==========line: $lineString")

        val split = lineString.split('=')
        if (split.size != 2) return@useLines

        val name = split[0]
        val value = split[1]
        rootProject.extra[name] = value
    }

    println(rootProject.extra.properties)
}