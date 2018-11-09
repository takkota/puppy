package kota.github.com.puppy.utils

import org.slf4j.LoggerFactory

object LogUtil {
    private val logger = LoggerFactory.getLogger(LogUtil::class.java)

    fun info(msg: String) {
        logger.info(msg)
    }

    fun warn(msg: String) {
        logger.warn(msg)
    }

    fun error(msg: String) {
        logger.error(msg)
    }

    fun debug(msg: String) {
        logger.debug(msg)
    }

    fun trace(msg: String) {
        logger.trace(msg)
    }
}