package kota.github.com.puppy.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "messages")
class MessageProperties {
    lateinit var greetings: Map<Int, String>
}

@Component
@ConfigurationProperties(prefix = "dialogflow")
class DialogFlowProperties {
    lateinit var projectId: String
}
