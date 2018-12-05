package kota.github.com.puppy

import com.google.api.gax.core.CredentialsProvider
import com.google.api.gax.core.FixedCredentialsProvider
import com.google.auth.Credentials
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.ServiceAccountCredentials
import com.google.cloud.dialogflow.v2.*
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kota.github.com.puppy.model.GoogleApplicationCredentials
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
        val jsonStr = System.getenv("GOOGLE_APPLICATION_CREDENTIALS")

        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        //val credentials = gson.fromJson<GoogleApplicationCredentials>(jsonStr, GoogleApplicationCredentials::class.java)
        val client = SessionsClient.create(
                SessionsSettings.newBuilder().setCredentialsProvider(
                        FixedCredentialsProvider.create(
                                ServiceAccountCredentials.fromStream(jsonStr.byteInputStream())
                        )
                ).build()
        )
        client.use { sessionsClient ->
            // Set the session name using the session_id (UUID) and projectID (my-project-id)
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