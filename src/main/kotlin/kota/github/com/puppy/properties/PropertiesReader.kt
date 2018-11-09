package kota.github.com.puppy.properties

import kota.github.com.puppy.properties.MessageProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class PropertiesReader {
    @Autowired
    private lateinit var dialogFlowProperties: DialogFlowProperties

    fun getDialogFlowProjectId(): String {
        return dialogFlowProperties.projectId
    }
}