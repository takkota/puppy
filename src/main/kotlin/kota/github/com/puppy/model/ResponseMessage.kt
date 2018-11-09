package kota.github.com.puppy.model

import com.google.cloud.dialogflow.v2.QueryResult
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kota.github.com.puppy.utils.LogUtil

class ResponseMessage {
    var responseText: String
    var sessionId: String
    var expectResponse: Boolean = false

    constructor(queryResult: QueryResult, sessionId: String) {
        this.responseText = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["text"]?.stringValue!!
        this.sessionId = sessionId
        this.expectResponse = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["expect_response"]?.boolValue!!
    }
}