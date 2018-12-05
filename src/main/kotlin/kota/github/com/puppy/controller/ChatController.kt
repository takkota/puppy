package kota.github.com.puppy.controller

import kota.github.com.puppy.DialogFlow
import kota.github.com.puppy.model.QueryMessage
import kota.github.com.puppy.model.ResponseMessage
import kota.github.com.puppy.properties.PropertiesReader
import kota.github.com.puppy.utils.LogUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["chat"])
class ChatController {

    @Autowired
    lateinit var dialogFlow: DialogFlow

    //@RequestMapping(value = ["/query"], method = [RequestMethod.POST])
    //fun queryResponse(@RequestBody query: QueryMessage): ResponseMessage {
    //    val response = dialogFlow.detectIntentTexts(query.queryMessage, query.session_id, query.eventName)
    //    return ResponseMessage(response, query.session_id)
    //}

    @GetMapping
    fun queryResponse(@RequestParam("query") queryMessage: String, @RequestParam("session_id") sessionId: String, @RequestParam("event_name") eventName: String?): ResponseMessage {
        val response = dialogFlow.detectIntentTexts(queryMessage, sessionId, eventName)
        return ResponseMessage(response, sessionId)
    }

}
