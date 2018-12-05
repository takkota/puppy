package kota.github.com.puppy.model

import com.google.cloud.dialogflow.v2.QueryResult
import com.google.protobuf.Struct

class ResponseMessage {
    var response_text: String
    var session_id: String
    var expect_response: Boolean? = false
    var consecutive: Boolean? = false
    var save_data_key: String? = ""
    var save_data_value: String? = ""

    constructor(queryResult: QueryResult, sessionId: String) {
        this.response_text = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["response_text"]?.stringValue!!
        this.session_id = sessionId
        this.expect_response = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["expect_response"]?.boolValue
        this.consecutive = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["consecutive"]?.boolValue
        this.save_data_key = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["save_data_key"]?.stringValue
        this.save_data_value = queryResult.fulfillmentMessagesList[0].payload.fieldsMap["save_data_value"]?.stringValue
    }
}