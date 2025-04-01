package jetbrains.kotlin.course.alias.util

import java.io.File

fun File.ensureParentDir() {
    parentFile?.mkdirs()
}
