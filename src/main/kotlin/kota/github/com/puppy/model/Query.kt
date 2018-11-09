package kota.github.com.puppy.model

import java.util.*

class QueryMessage(
        var queryMessage: String = "",
        var sessionId: String = UUID.randomUUID().toString(),
        var eventName: String?
)
