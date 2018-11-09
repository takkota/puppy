package kota.github.com.puppy

import com.google.cloud.dialogflow.v2.*
import kota.github.com.puppy.properties.DialogFlowProperties
import kota.github.com.puppy.properties.PropertiesReader
import kota.github.com.puppy.utils.LogUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

// [END dialogflow_import_libraries]


/**
 * DialogFlow API Detect Intent sample with text inputs.
 */
@Component
class DialogFlow {
    @Autowired
    private lateinit var propertiesReader: PropertiesReader

    // [START dialogflow_detect_intent_text]
    /**
     * Returns the result of detect intent with texts as inputs.
     *
     * Using the same `session_id` between requests allows continuation of the conversation.
     * @param projectId Project/Agent Id.
     * @param texts The text intents to be detected based on what a user says.
     * @param sessionId Identifier of the DetectIntent session.
     * @param languageCode Language code of the query.
     */
    fun detectIntentTexts(text: String, sessionId: String, eventName: String?): QueryResult {
        // Instantiates a client
        val projectId = propertiesReader.getDialogFlowProjectId()

        lateinit var queryResult: QueryResult
        SessionsClient.create().use { sessionsClient ->
            // Set the session name using the sessionId (UUID) and projectID (my-project-id)
            val session = SessionName.of(projectId, sessionId)

            // Detect intents for each text input
            // Set the text (hello) and language code (en-US) for the query
            val textInput = TextInput.newBuilder().setText(text).setLanguageCode("en-US")

            // Build the query with the TextInput
            val queryInput = QueryInput.newBuilder().apply {
                setText(textInput)
                if (!eventName.isNullOrEmpty()) {
                    val event = EventInput.newBuilder().apply {
                        languageCode = "en-US"
                        name = eventName
                    }
                    setEvent(event)
                }
            }.build()

            // Performs the detect intent request
            val response = sessionsClient.detectIntent(session, queryInput)

            // Display the query result
            queryResult = response.queryResult
        }
        return queryResult
    }
}